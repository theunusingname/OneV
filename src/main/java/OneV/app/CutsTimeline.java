package OneV.app;

import java.awt.*;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface CutsTimeline {
    PositionInTimeLine getCurrentPosition();
    void addContainer(FramesCut container);
    void addBefore(FramesCut container, int containerCount);
    void addAfter(FramesCut container, int containerCount);
    void setPosition(PositionInTimeLine pos);
    boolean cut(PositionInTimeLine pos);
    FramesCut getCurrentContainer();
    int getCurrentContainerIndex();
    FramesCut getContainerOnPosition(PositionInTimeLine pos);
    Image getCurrentFrame();
    int getCurrentFrameIndex();
    int getContainersSize();
}
