package OneV.app;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Константин on 19.02.2016.
 */
public interface AbstractTimeLine {

    PositionInTimeLine getCurentPosition();
    void addContainer(AbstractRawContainer container);
    void addBefore(AbstractRawContainer container, int containerCount);
    void addAfter(AbstractRawContainer container, int containerCount);
    void setPosition(PositionInTimeLine pos);
    AbstractRawContainer cut(AbstractRawContainer container,PositionInTimeLine pos);
    boolean cut(PositionInTimeLine pos);
    AbstractRawContainer getCurrentContainer();
    int getCurrentContainerIndex();
    AbstractRawContainer getContainerOnPosition(PositionInTimeLine pos);
    Image getCurrentFrame();
    int getCurrentFrameIndex();
    int getContainersSize();

}
