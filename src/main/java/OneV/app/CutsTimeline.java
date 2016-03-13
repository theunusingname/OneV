package OneV.app;

import java.awt.*;
import java.io.File;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface CutsTimeline {
    PositionInTimeLine getCurrentPosition();
    void addContainer(FramesCut cut);
    void addBefore(FramesCut cut, int cutCount);
    void addAfter(FramesCut cut, int cutCount);
    void setPosition(PositionInTimeLine pos);
    boolean cut(PositionInTimeLine pos);
    FramesCut getCurrentContainer();
    int getCurrentContainerIndex();
    FramesCut getContainerOnPosition(PositionInTimeLine pos);
    Image getCurrentFrame();
    File getCurrentFile();
    int getCurrentFrameIndex();
    int getContainersSize();
}
