package OneV.app;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by kkuznetsov on 03.03.2016.
 */
public class DefaultMovieView extends Frame implements MovieView { //todo test class, refactor With Canvas extending
    final int INIT_WIDTH=640;
    final int INIT_HEIGHT=480;
    Image imgBuffer;

    DefaultMovieView()
    {
        super();
        addWindowListener(new MovieViewAdapter());
    }

    public void init()
    {

        this.setTitle("my movie");
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
        imgBuffer=img;//.getScaledInstance(INIT_WIDTH,INIT_HEIGHT,Image.SCALE_SMOOTH);

        this.repaint();
        System.out.println(img.toString());
    }

    @Override
    public void showFrame(RawContainer cont, int pos) {

    }
}

class MovieViewAdapter extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}