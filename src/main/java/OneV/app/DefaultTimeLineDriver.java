package OneV.app;

/**
 * Created by Константин on 28.02.2016.
 */
public class DefaultTimeLineDriver implements AbstractTimeLineDriver   {

    private  AbstractTimeLine TimeLine;
    private  AbstractMovieView View;


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
        // TODO: 29.02.2016 запилить отдельный тред
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
                }
                position.currentFrameCount=0;
            }
        }
    }

    public void stop() {

    }

    public boolean gotoPosition(PositionInTimeLine pos) {
        return false;
    }



}
