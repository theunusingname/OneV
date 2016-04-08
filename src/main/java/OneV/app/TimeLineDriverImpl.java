package OneV.app;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Константин on 28.02.2016.
 */

public class TimeLineDriverImpl implements TimeLineDriver, ChangeListener{

    private CutsTimeline timeLine;
    private MovieView View;
    private Thread tr;
    private JSlider slider;
    volatile boolean stopFlag;
    volatile boolean pauseFlag;
    volatile int currentSliderPos;
    int maxSlider;
    int fps=30;

    public TimeLineDriverImpl(CutsTimeline timeline, MovieView view)
    {
        this.timeLine =timeline;
        this.View=view;
        timeline.setDriver(this);
        currentSliderPos=timeLinePositionToInt(timeline,timeline.getCurrentPosition());
        slider=new JSlider();
        slider.setName("init");
        stopFlag=true;
    }


    public  void play(int fps) {
        this.fps=fps;
        tr=new Thread(()->{
        synchronized (View)
        {
            PositionInTimeLine position= timeLine.getCurrentPosition();
            int cuts= timeLine.getCutsSize();
            for(int i=position.currentContainer; i<cuts; i++)
            {
                FramesCut currentCont= timeLine.getContainerOnPosition(position);
                for(int j=position.currentFrameCount;j<currentCont.size();j++)
                {
                    currentSliderPos=timeLinePositionToInt(timeLine,new PositionInTimeLine(i,j));
                    slider.setValue(currentSliderPos);
                    View.showFrame(currentCont.getFrame(j));
                    timeLine.setPosition(intToPosition(timeLine,currentSliderPos));

                    try {
                        Thread.sleep(1000/this.fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(stopFlag)
                        break;

                    while (pauseFlag)
                        try {
                            wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
        timeLine.setPosition(new PositionInTimeLine(0,0));
    }

    @Override
    public void pause() {

    }

    static public int timeLinePositionToInt(CutsTimeline timeline,PositionInTimeLine pos)
    {
        if(timeline==null || pos==null)
            return 0;

        int result=0;
        for(int i=0;i<pos.currentContainer;i++)
        {
            result+=timeline.getContainerOnPosition(new PositionInTimeLine(i,0)).size();
        }
        result+=pos.currentFrameCount;
        return result;
    }

    @Nullable
    static public PositionInTimeLine intToPosition(CutsTimeline timeLine, int pos)
    {
        PositionInTimeLine result;
        if (timeLine==null||timeLine.getCutsSize()==0)
        {
            System.out.println("cant convert, invalid timeline");
            return null;
        }
        int cut=-1;
        int frame;
        try {
            do {
                cut++;
                pos -= timeLine.getContainerOnPosition(new PositionInTimeLine(cut, 0)).size();
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
        maxSlider=0;
        for(int i = 0; i<timeLine.getCutsSize(); i++)
        {
            FramesCut currentCont= timeLine.getContainerOnPosition(new PositionInTimeLine(i,0));
            maxSlider+=currentCont.size()-1;
        }

        if(slider.getName()!="validSlider") {
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

    public void setFps(int fps) {
        this.fps = fps;
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        Component component=(Component) e.getSource();
        String name=component.getName();
       if( e.getSource()==slider&&stopFlag==true)
       {
           stop();
           timeLine.setPosition(intToPosition(timeLine,slider.getValue()));
           View.showFrame(timeLine.getCurrentFrame());

       };
    }


}
