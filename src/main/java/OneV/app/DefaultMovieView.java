package OneV.app;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by kkuznetsov on 03.03.2016.
 */
public class DefaultMovieView implements AbstractMovieView {
    final int INIT_WIDTH=640;
    final int INIT_HEIGHT=480;
    Frame mainFrame;
    Container imgContainer;

    DefaultMovieView()
    {
        mainFrame=new Frame("my movie");
        imgContainer=new Container();
        mainFrame.setSize(INIT_WIDTH,INIT_HEIGHT);
        mainFrame.add(imgContainer);
        mainFrame.setVisible(true);
    }

    @Override
    public void showFrame(Image img) {
        Image imgToShow= img.getScaledInstance(640,480,Image.SCALE_SMOOTH);
        imgContainer.paint(img.getGraphics());
        mainFrame.repaint();
        System.out.println(img.toString());
    }

    @Override
    public void showFrame(AbstractRawContainer cont, int pos) {

    }
}
