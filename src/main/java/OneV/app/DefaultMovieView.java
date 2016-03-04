package OneV.app;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Created by kkuznetsov on 03.03.2016.
 */
public class DefaultMovieView extends Frame implements AbstractMovieView {
    final int INIT_WIDTH=640;
    final int INIT_HEIGHT=480;
    Container imgContainer;
    Image imgBuffer;

    DefaultMovieView()
    {
    }

    public void init()
    {
        imgContainer=new Container();
        this.setTitle("my movie");
        this.setSize(INIT_WIDTH,INIT_HEIGHT);
        this.add(imgContainer);
    }

    public void paint(Graphics g)
    {
        imgContainer.paint(g);
        //imgContainer.setVisible(true);
    }

    @Override
    public void showFrame(Image img) {
        imgBuffer=img;
        this.repaint();
        System.out.println(img.toString());
    }

    @Override
    public void showFrame(AbstractRawContainer cont, int pos) {

    }
}
