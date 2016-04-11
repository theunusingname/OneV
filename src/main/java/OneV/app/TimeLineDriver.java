package OneV.app;

import javax.swing.*;
import javax.swing.event.ChangeListener;

/**
 * Created by Константин on 27.02.2016.
 */
public interface TimeLineDriver extends ChangeListener {
    void play();
    void stop();
    void pause();
    void setFPS(int fps);
    int getFPS();
    boolean gotoPosition(PositionInTimeLine pos);
    JSlider getSlider();
    void updateSlider();

}
