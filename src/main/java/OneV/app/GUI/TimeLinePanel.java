package OneV.app.GUI;

import OneV.app.CutsTimeline;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class TimeLinePanel extends JPanel{
    EventListener parent;
    JSlider timeLineSlider;
    CutsTimeline timeline;

    TimeLinePanel(EventListener parent)
    {
        super();
        this.parent=parent;
        timeLineSlider=new JSlider(JSlider.HORIZONTAL,0,100,1);
        setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        timeLineSlider.setBounds(0,0,getWidth(),getHeight());
        timeLineSlider.setForeground(Color.black);
        add(timeLineSlider);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
    }

}
