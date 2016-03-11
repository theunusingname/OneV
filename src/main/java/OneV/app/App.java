package OneV.app;


import OneV.app.old.DefaultLoader;
import OneV.app.old.DefaultRawContainer;
import OneV.app.old.DefaultTimeLine;
import OneV.app.old.DefaultTimeLineDriver;

public class App
{
    public static void main( String[] args )
    {
        DefaultLoader loader=new DefaultLoader();
        DefaultRawContainer testContainer= (DefaultRawContainer) loader.getContainerWithDialog();
        if (testContainer!=null) {
            System.out.println("Images in test container: " + testContainer.size());

            DefaultTimeLine testDefaultTimeLine = new DefaultTimeLine();
            testDefaultTimeLine.addContainer(testContainer);
            System.out.println("Containers in timeline: " + testDefaultTimeLine.getContainersSize());
            testDefaultTimeLine.setPosition(new PositionInTimeLine(0,0));

            DefaultMovieView view =new DefaultMovieView();
            view.init();
            view.setVisible(true);
            DefaultTimeLineDriver driver= new DefaultTimeLineDriver(testDefaultTimeLine,view);
            driver.play(2);
        }

    }
}
