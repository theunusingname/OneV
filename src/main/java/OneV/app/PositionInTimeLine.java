package OneV.app;

/**
 * Created by Константин on 21.02.2016.
 */
public class PositionInTimeLine {
    public int currentContainer;
    public int currentFrameCount;

    public PositionInTimeLine(int containerCount, int currentFrameCount)
    {
        this.currentFrameCount = currentFrameCount;
        this.currentContainer=containerCount;
    }
    PositionInTimeLine()
    {
        currentContainer=-1;
        currentFrameCount =-1;
    }

}
