package OneV.app;

import java.awt.*;
import java.io.File;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface CutsTimeline {
    PositionInTimeLine getCurrentPosition();
    void addCuts(Collection<FramesCut> cuts);
    void addCut(FramesCut cut);
    void addBefore(FramesCut cut, int cutCount);
    void addAfter(FramesCut cut, int cutCount);
    void setPosition(PositionInTimeLine pos);
    boolean cut(PositionInTimeLine pos);
    FramesCut getCurrentCut();
    int getCurrentCutIndex();
    FramesCut getContainerOnPosition(PositionInTimeLine pos);
    Image getCurrentFrame();
    File getCurrentFile();
    int getCurrentFrameIndex();
    int getCutsSize();
    int getOverallSize();
    void setDriver(TimeLineDriver currentDriver);
    Stream<File> getFileStream();
    Stream<Image> getImageStream();
}
