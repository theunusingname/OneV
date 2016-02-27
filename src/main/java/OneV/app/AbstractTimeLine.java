package OneV.app;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Константин on 19.02.2016.
 */
public interface AbstractTimeLine {
    Vector<AbstractRawContainer> containers =new Vector<AbstractRawContainer>(); //need rename
    PositionInTimeLine currentPos=new PositionInTimeLine();
    void addContainer(AbstractRawContainer container);
    void addBefore(AbstractRawContainer container, int containerCount);
    void addAfter(AbstractRawContainer container, int containerCount);
    AbstractRawContainer cut(AbstractRawContainer container,PositionInTimeLine pos);
    boolean cut(PositionInTimeLine pos);
    AbstractRawContainer getCurrentContainer();
    int getCurrentContainerCount();
    AbstractRawContainer getContainerOnPosition(PositionInTimeLine pos);
    Image getCurrentFrame();
    int getCurrentFrameCount();

}
