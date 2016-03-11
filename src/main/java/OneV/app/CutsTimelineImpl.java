package OneV.app;

import OneV.app.old.RawContainer;

import java.awt.*;
import java.util.Vector;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public class CutsTimelineImpl implements CutsTimeline {
    private PositionInTimeLine currentPosition=new PositionInTimeLine(0,0);
    private Vector<FramesCut> containers = new Vector<>();

    @Override
    public PositionInTimeLine getCurrentPosition() {
        return null;
    }

    @Override
    public void addContainer(FramesCut container) {

    }

    @Override
    public void addBefore(FramesCut container, int containerCount) {

    }

    @Override
    public void addAfter(FramesCut container, int containerCount) {

    }

    @Override
    public void setPosition(PositionInTimeLine pos) {

    }

    @Override
    public boolean cut(PositionInTimeLine pos) {
        return false;
    }

    @Override
    public FramesCut getCurrentContainer() {
        return null;
    }

    @Override
    public int getCurrentContainerIndex() {
        return 0;
    }

    @Override
    public FramesCut getContainerOnPosition(PositionInTimeLine pos) {
        return null;
    }

    @Override
    public Image getCurrentFrame() {
        return null;
    }

    @Override
    public int getCurrentFrameIndex() {
        return 0;
    }

    @Override
    public int getContainersSize() {
        return 0;
    }
}
