package OneV.app.GUI;

import OneV.app.CutsTimeline;
import OneV.app.PositionInTimeLine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class TimeLineSlider extends JSlider implements ChangeListener {

    CutsTimeline timeline;
    ArrayList<Integer> startCutPosition=new ArrayList<>();
    ArrayList<Integer> absoluteMarkerX=new ArrayList<>();

    public TimeLineSlider()
    {
        super();
    }

    public TimeLineSlider(CutsTimeline timeline, int min, int max)
    {
        super(min,max);
        this.timeline=timeline;
        addChangeListener(this);
    }


    public void paintComponent(Graphics g)
    {
//        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (int i=0;i<absoluteMarkerX.size();i++)
        {
//            g.drawImage(timeline.getContainerOnPosition(new PositionInTimeLine(i,0)).getFrame(0), absoluteMarkerX.get(i),0,null);
            g.drawLine(absoluteMarkerX.get(i),0,absoluteMarkerX.get(i),getHeight());
        }
    }

    public void update()
    {
        if(timeline==null)
        {
            System.out.println("Timeline is empty. Can't update");
            return;
        }
        int ratio=0;
        ratio = getWidth() / timeline.getOverallSize();
        startCutPosition.clear();
        int collector=0;
        for (int i= 0;i<timeline.getCutsSize();i++)
        {
            collector+=timeline.getContainerOnPosition(new PositionInTimeLine(i,0)).size();
            startCutPosition.add(collector);
        }
        absoluteMarkerX.clear();
        for (Integer i: startCutPosition)
        {
            absoluteMarkerX.add(i*ratio);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}

