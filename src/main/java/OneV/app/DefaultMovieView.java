package OneV.app;

import OneV.app.old.RawContainer;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by kkuznetsov on 03.03.2016.
 */
public class DefaultMovieView extends Canvas implements MovieView {
    final int INIT_WIDTH=640;
    final int INIT_HEIGHT=480;
    Image imgBuffer;

    public DefaultMovieView()
    {
        super();
        this.setVisible(true);
        setSize(640,480);

    }

    public void init()
    {
        this.setSize(INIT_WIDTH,INIT_HEIGHT);
    }

    public void paint(Graphics g)
    {
        Graphics screengc = null;
        screengc=g;
        g=imgBuffer.getGraphics();

        screengc.drawImage(imgBuffer,0,0,null);
    }

    @Override
    public void showFrame(Image img) {
        imgBuffer=img;

        this.repaint();
        System.out.println(img.toString());
    }

    @Override
    public void showFrame(RawContainer cont, int pos) {

    }
}

