package OneV.app.old;

import OneV.app.PositionInTimeLine;

import java.awt.*;

/**
 * Created by Константин on 19.02.2016.
 */
public interface TimeLine {

    PositionInTimeLine getCurentPosition();
    void addContainer(RawContainer container);
    void addBefore(RawContainer container, int containerCount);
    void addAfter(RawContainer container, int containerCount);
    void setPosition(PositionInTimeLine pos);
    boolean cut(PositionInTimeLine pos);
    RawContainer getCurrentContainer();
    int getCurrentContainerIndex();
    RawContainer getContainerOnPosition(PositionInTimeLine pos);
    Image getCurrentFrame();
    int getCurrentFrameIndex();
    int getContainersSize();

}
