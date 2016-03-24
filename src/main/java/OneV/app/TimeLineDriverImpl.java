package OneV.app;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Date;

/**
 * Created by Константин on 28.02.2016.
 */

public class TimeLineDriverImpl implements TimeLineDriver, ChangeListener{

    private CutsTimeline timeLine;
    private MovieView View;
    private Thread tr;
    private JSlider slider;
    volatile boolean stopFlag;
    volatile int currentSliderPos;
    int maxSlider;

    public TimeLineDriverImpl(CutsTimeline timeline, MovieView view)
    {
        this.timeLine =timeline;
        this.View=view;
        timeline.setDriver(this);
        currentSliderPos=timeLinePositionToInt(timeline,timeline.getCurrentPosition());
        slider=new JSlider();
        slider.setName("init");
    }


    public  void play(int fps) {

        tr=new Thread(()->{
        synchronized (View)
        {
            PositionInTimeLine position= timeLine.getCurrentPosition();
            int cuts= timeLine.getContainersSize();
            for(int i=position.currentContainer; i<cuts; i++)
            {
                FramesCut currentCont= timeLine.getContainerOnPosition(position);
                for(int j=position.currentFrameCount;j<currentCont.size();j++)
                {
                    currentSliderPos=timeLinePositionToInt(timeLine,new PositionInTimeLine(i,j));
                    slider.setValue(currentSliderPos);
                    System.out.println(currentSliderPos);
                    slider.repaint();
                    View.showFrame(currentCont.getFrame(j));

                    try {
                        Thread.sleep(1000/fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(stopFlag)
                        break;
                }
                if(stopFlag)
                    break;
                position.currentFrameCount=0;
            }
        }
        });
        stopFlag=false;
        tr.start();
    }

    public void stop() {

        stopFlag=true;
        tr=null;
    }

    static public int timeLinePositionToInt(CutsTimeline timeline,PositionInTimeLine pos)//// TODO: 23.03.2016  
    {
        if(timeline==null || pos==null)
            return 0;

        int result;
        for(int i=0;i<pos.currentContainer;i++)
        {
            result=+timeline.getContainerOnPosition(new PositionInTimeLine(i,0)).size();
        }
        result=+pos.currentFrameCount;
        return result;
    }

    @Nullable
    static public PositionInTimeLine intToPosition(CutsTimeline timeLine, int pos)
    {
        PositionInTimeLine result;
        if (timeLine==null||timeLine.getContainersSize()==0)
        {
            System.out.println("cant convert, invalid timeline");
            return null;
        }
        int cut=0;
        int frame;
        try {
            do {
                pos = -timeLine.getContainerOnPosition(new PositionInTimeLine(cut, 0)).size();
                cut++;
            } while (pos > 0);
        }catch (NullPointerException e)
        {
            System.out.println("time line is over");
            return null;
        }
        frame=timeLine.getContainerOnPosition(new PositionInTimeLine(cut,0)).size()-Math.abs(pos);
        return new PositionInTimeLine(cut,frame);

    }


    public boolean gotoPosition(PositionInTimeLine pos) {
        return false;
    }

    public JSlider getSlider()
    {
        if(timeLine==null)
        {
            return null;
        }
        for(int i=0;i<timeLine.getContainersSize();i++)
        {
            FramesCut currentCont= timeLine.getContainerOnPosition(new PositionInTimeLine(i,0));
            maxSlider=+currentCont.size();
        }

        if(slider==null) {
            slider = new JSlider(0, maxSlider);
            slider.addChangeListener(this);
            slider.setName("validSlider");
            slider.setExtent(1);
        }
        return slider;

    }

    public void updateSlider()
    {
        if (timeLine==null)
            return;
        else
        {
            getSlider();
            slider.setMaximum(maxSlider);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println(e.toString());
    }


}
