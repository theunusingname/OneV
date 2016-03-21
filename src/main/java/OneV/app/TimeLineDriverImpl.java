package OneV.app;

import OneV.app.old.RawContainer;

import java.util.Date;

/**
 * Created by Константин on 28.02.2016.
 */
public class TimeLineDriverImpl implements TimeLineDriver {

    private CutsTimeline timeLine;
    private MovieView View;
    private Thread tr;
    volatile boolean stopFlag;

    public TimeLineDriverImpl(CutsTimeline timeline, MovieView view)
    {
        this.timeLine =timeline;
        this.View=view;
    }

    public TimeLineDriverImpl getDriver(CutsTimeline timeLine, MovieView view )
    {
        if (timeLine!=null && view!=null)
        {
            return new TimeLineDriverImpl(timeLine,view);
        }
        else
        {
            System.out.println("timeLine or view doesn't exist");
            return null;
        }
    }

    public  void play(int fps) {

        tr=new Thread(()->{
        synchronized (View)
        {
            PositionInTimeLine position= timeLine.getCurrentPosition();
            int cuts= timeLine.getContainersSize();
            for(int i=position.currentContainer; i<cuts; i++)
            {
                FramesCut currentCont= timeLine.getContainerOnPosition(position);
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
