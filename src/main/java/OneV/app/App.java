package OneV.app;


import OneV.app.old.DefaultLoader;
import OneV.app.old.DefaultRawContainer;
import OneV.app.old.DefaultTimeLine;
import OneV.app.old.DefaultTimeLineDriver;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        CutLoaderImpl loader= new CutLoaderImpl(640,480);
        FramesCut cut=loader.getCutWithDialog();

        //       System.out.println("frames in cut "+cut.size());
        CutsTimeline timeline=new CutsTimelineImpl();
        timeline.addContainer(cut);
        System.out.println("cuts in timeline " +timeline.getContainersSize());
        DefaultMovieView view=new DefaultMovieView();
        TimeLineDriver driver=new TimeLineDriverImpl(timeline,view);
        driver.play(10);

    }
}
