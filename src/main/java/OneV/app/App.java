package OneV.app;

import java.awt.*;
import java.io.File;
import java.nio.*;
import java.nio.file.Paths;


public class App
{
    public static void main( String[] args )
    {
        DefaultLoader loader=new DefaultLoader();
        DefaultRawContainer testContainer= (DefaultRawContainer) loader.getContainerWithDialog();
        if (testContainer!=null) {
            System.out.println("Images in test container: " + testContainer.size());

            TimeLine testTimeLine = new TimeLine();
            testTimeLine.addContainer(testContainer);
            System.out.println("Containers in timeline: " + testTimeLine.getContainersSize());
            testTimeLine.setPosition(new PositionInTimeLine(0,0));

            DefaultMovieView view =new DefaultMovieView();
            DefaultTimeLineDriver driver= new DefaultTimeLineDriver(testTimeLine,view);
            driver.play(1);

        }

    }
}
