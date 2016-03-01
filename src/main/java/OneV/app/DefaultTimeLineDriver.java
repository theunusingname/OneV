package OneV.app;

/**
 * Created by Константин on 28.02.2016.
 */
public class DefaultTimeLineDriver implements AbstractTimeLineDriver   {

    private  AbstractTimeLine TimeLine;
    private  AbstractMovieView View;
    Thread tr;


    DefaultTimeLineDriver(AbstractTimeLine TimeLine, AbstractMovieView view)
    {
        this.TimeLine=TimeLine;
        this.View=view;
    }

    public DefaultTimeLineDriver getDriver(AbstractTimeLine TimeLine,AbstractMovieView view )
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
                AbstractRawContainer currentCont=TimeLine.getContainerOnPosition(position);
                for(int j=position.currentFrameCount;j<currentCont.size();j++)
                {
                    View.showFrame(currentCont.getFrame(j));
                    try {
                        Thread.sleep(100/fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                position.currentFrameCount=0;
            }
        }
        });
        tr.start();
    }

    public void stop() {

        tr.interrupt();
    }

    public boolean gotoPosition(PositionInTimeLine pos) {
        return false;
    }



}
