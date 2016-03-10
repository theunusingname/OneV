package OneV.app;

import java.util.Date;

/**
 * Created by Константин on 28.02.2016.
 */
public class DefaultTimeLineDriver implements TimeLineDriver {

    private OneV.app.TimeLine TimeLine;
    private MovieView View;
    private Thread tr;
    volatile boolean stopFlag;

    DefaultTimeLineDriver(OneV.app.TimeLine TimeLine, MovieView view)
    {
        this.TimeLine=TimeLine;
        this.View=view;
    }

    public DefaultTimeLineDriver getDriver(OneV.app.TimeLine TimeLine, MovieView view )
    {
        if (TimeLine!=null && view!=null)
        {
            return new DefaultTimeLineDriver(TimeLine,view);
        }
        else
        {
            System.out.println("TimeLine or view doesn't exist");
            return null;
        }
    }

    public  void play(int fps) {

        tr=new Thread(()->{
        synchronized (View)
        {
            PositionInTimeLine position= TimeLine.getCurentPosition();
            int cuts=TimeLine.getContainersSize();
            for(int i=position.currentContainer; i<cuts; i++)
            {
                RawContainer currentCont=TimeLine.getContainerOnPosition(position);
                for(int j=position.currentFrameCount;j<currentCont.size();j++)
                {
                    Date date=new Date();
                    long Ltime=date.getTime();
                    View.showFrame(currentCont.getFrame(j));
                    System.out.println("Timer "+(Ltime-date.getTime()));
                    try {
                        Thread.sleep(1000/fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(stopFlag)
                        break;
                }
                if(stopFlag)
                    break;
                position.currentFrameCount=0;
            }
        }
        });
        stopFlag=false;
        tr.start();
    }

    public void stop() {

        stopFlag=true;
        tr=null;
    }

    public boolean gotoPosition(PositionInTimeLine pos) {
        return false;
    }



}
