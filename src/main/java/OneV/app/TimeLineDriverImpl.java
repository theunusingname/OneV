package OneV.app;

import OneV.app.GUI.TimeLineSlider;
import org.jetbrains.annotations.Nullable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by Константин on 28.02.2016.
 */

public class TimeLineDriverImpl implements TimeLineDriver, ChangeListener{

    private CutsTimeline timeLine;
    private MovieView View;
    private Thread tr;
    private TimeLineSlider slider;
    volatile boolean stopFlag;
    volatile boolean pauseFlag;
    volatile boolean inPlayingFlag;
    volatile int currentSliderPos;
    int maxSlider;
    volatile int fps=30;

    public TimeLineDriverImpl(CutsTimeline timeline, MovieView view)
    {
        this.timeLine =timeline;
        this.View=view;
        timeline.setDriver(this);
        currentSliderPos=timeLinePositionToInt(timeline,timeline.getCurrentPosition());
        slider=new TimeLineSlider();
        slider.setName("init");
        slider.disable();
        stopFlag=true;
    }


    public  void play() {
        tr=new Thread(()->{
        synchronized (View)
        {
            inPlayingFlag=true;
            PositionInTimeLine position= timeLine.getCurrentPosition();
            Stream<Image> imageStream= timeLine.getImageStream();
            Iterator<Image> it= imageStream.iterator();
            currentSliderPos=0;
            for (int i=0;i<timeLinePositionToInt(timeLine,position);i++)
            {
                it.next();
                currentSliderPos++;
            }
            it.forEachRemaining(image ->
            {
                while (pauseFlag)
                try {
                    Thread.sleep(100);
                    if(stopFlag) break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(stopFlag)
                    tr.interrupt();

                View.showFrame(image);
                currentSliderPos++;
                slider.setValue(currentSliderPos);

                try {
                        Thread.sleep(1000/this.fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            });
            inPlayingFlag=false;
            stop();
        }
        });

        pauseFlag=false;
        stopFlag=false;
        if(!inPlayingFlag&&timeLine.getCutsSize()!=0)
            tr.start();
    }

    public void stop() {
        stopFlag=true;
        tr=null;
        if(timeLine.getCutsSize()!=0)
        {
            timeLine.setPosition(new PositionInTimeLine(0, 0));
            slider.setValue(timeLinePositionToInt(timeLine, timeLine.getCurrentPosition()));
        }
    }

    @Override
    public void pause() {
        pauseFlag=true;
    }

    static public int timeLinePositionToInt(CutsTimeline timeline,PositionInTimeLine pos)
    {
        if(timeline==null || pos==null)
            return 0;

        int result=0;
        for(int i=0;i<pos.currentContainer;i++)
        {
            result+=timeline.getContainerOnPosition(new PositionInTimeLine(i,0)).size()-1;
        }
        result+=pos.currentFrameCount;
        return result;
    }

    @Nullable
    static public PositionInTimeLine intToPosition(CutsTimeline timeLine, int pos) // FIXME: 09.04.2016 всё уезжает на едениуц вперёд
    {
        PositionInTimeLine result;
        if (timeLine==null||timeLine.getCutsSize()==0)
        {
            System.out.println("cant convert, invalid timeline");
            return null;
        }
        int cut=0;
        int frame;
            do {
                pos -= timeLine.getContainerOnPosition(new PositionInTimeLine(cut, 0)).size();
                cut++;
            } while (pos > 0);
            if (pos<0)
                cut--;
        frame=timeLine.getContainerOnPosition(new PositionInTimeLine(cut,0)).size()-Math.abs(pos)-1;
        return new PositionInTimeLine(cut,frame);

    }


    public boolean gotoPosition(PositionInTimeLine pos) {
        return false;
    }

    public TimeLineSlider getSlider()
    {
        if(timeLine==null||timeLine.getCutsSize()==0)
        {
            return slider=new TimeLineSlider(timeLine,1,2);
        }
        maxSlider=0;
        PositionInTimeLine pos=new PositionInTimeLine(timeLine.getCutsSize()-1,
                timeLine.getContainerOnPosition(new PositionInTimeLine(timeLine.getCutsSize()-1,0)).size()-1);
        maxSlider=timeLinePositionToInt(timeLine,pos);

        if(slider.getName()!="validSlider") {
            slider.setMaximum(maxSlider);
            slider.addChangeListener(this);
            slider.setName("validSlider");
            slider.setExtent(1);
        }
        slider.update();
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

    public void setFPS(int fps) {
        this.fps = fps;
    }

    @Override
    public int getFPS() {
        return fps;
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        Component component=(Component) e.getSource();
        String name=component.getName();
       if(slider.getName()=="validSlider"&& stopFlag)
       {
           pauseFlag=false;
           stopFlag=true;
           timeLine.setPosition(intToPosition(timeLine,slider.getValue()));
           View.showFrame(timeLine.getCurrentFrame());

       };
    }
}
