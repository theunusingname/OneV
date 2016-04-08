package OneV.app;

import javax.swing.*;
import javax.swing.event.ChangeListener;

/**
 * Created by Константин on 27.02.2016.
 */
public interface TimeLineDriver extends ChangeListener {
    void play(int fps);
    void stop();
    void pause();
    boolean gotoPosition(PositionInTimeLine pos);
    public JSlider getSlider();
    public void updateSlider();

}
