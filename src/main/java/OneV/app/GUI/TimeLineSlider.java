package OneV.app.GUI;

import OneV.app.CutsTimeline;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class TimeLineSlider extends JSlider implements ChangeListener {
    MainWindow parent;

    CutsTimeline timeline;

    public TimeLineSlider(int min, int max)
    {
        super(min,max);
    }

    public TimeLineSlider()
    {
        super();
    }

    TimeLineSlider(MainWindow parent)
    {
        super();
        this.parent=parent;
        addChangeListener(this);
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        //g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}

