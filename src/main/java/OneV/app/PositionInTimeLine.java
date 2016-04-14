package OneV.app;

import java.util.Objects;

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

    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null || getClass()!=obj.getClass())
            return false;

        PositionInTimeLine pos = (PositionInTimeLine)obj;
        if(pos.currentContainer==currentContainer&&pos.currentFrameCount==currentFrameCount)
            return true;
            else return false;
    }

}
