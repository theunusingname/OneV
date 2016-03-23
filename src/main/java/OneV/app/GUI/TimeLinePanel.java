package OneV.app.GUI;

import OneV.app.CutsTimeline;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class TimeLinePanel extends JPanel{
    MainWindow parent;
    JSlider timeLineSlider;
    CutsTimeline timeline;

    TimeLinePanel(MainWindow parent)
    {
        super();
        this.parent=parent;
        timeLineSlider=parent.driver.getSlider();
        setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        timeLineSlider.setBounds(0,0,getWidth(),getHeight());
        add(timeLineSlider);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        //g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
    }

}
