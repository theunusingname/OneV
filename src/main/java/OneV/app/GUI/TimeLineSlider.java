package OneV.app.GUI;

import OneV.app.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class TimeLineSlider extends JSlider implements ChangeListener, MouseMotionListener {

    CutsTimeline timeline;
    Frame previewFrame;
    MovieView preview;
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
        addMouseMotionListener(this);
        preview=new DefaultMovieView();
        previewFrame=new Frame();
        previewFrame.setUndecorated(true);
        previewFrame.setSize(160,120);
        previewFrame.add((Component) preview);
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
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
        float ratio=0;
        ratio = ((float) getWidth()) / timeline.getOverallSize();
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
            absoluteMarkerX.add((int)(i*ratio));
        }
        System.out.println("Markers updated "+absoluteMarkerX.size()+" markers");
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(absoluteMarkerX.size()!=0)
        {

            for (int x=0;x<absoluteMarkerX.size();x++)
            {
                if(Math.abs(e.getX()-absoluteMarkerX.get(x))<10) {
                    previewFrame.setLocation(new Point(e.getXOnScreen(),e.getYOnScreen()+10));
                    preview.showFrame(timeline.getContainerOnPosition(TimeLineDriverImpl.intToPosition(timeline, startCutPosition.get(x))).getFrame(0));
                    previewFrame.setVisible(true);
                    break;
                } else if(Math.abs(e.getX()-absoluteMarkerX.get(x))>=10)
                {
                    previewFrame.setVisible(false);
                }
            }
        }

    }

}

