package OneV.app;

import java.awt.*;

/**
 * Created by Константин on 21.02.2016.
 */
public class TimeLine implements AbstractTimeLine {

    private PositionInTimeLine currentPosition;


    TimeLine()
    {
        currentPosition=new PositionInTimeLine();
    }

    public void addContainer(AbstractRawContainer container) {
        containers.add(container);
    }

    public void addBefore(AbstractRawContainer container, int containerCount) {
// TODO: 26.02.2016  
    }

    public void addAfter(AbstractRawContainer container, int containerCount) {
//// TODO: 26.02.2016  
    }

    public void addBeforeCurrent(AbstractRawContainer container) {
        if (this.getCurrentContainerCount()==0)
        {
            this.addContainer(container);
        }
        else
        {
            containers.insertElementAt(container,this.getCurrentContainerCount());
        }
    }

    public AbstractRawContainer cut(AbstractRawContainer container, PositionInTimeLine pos) {
        return null;
        //// TODO: 26.02.2016  
    }

    public boolean cut(PositionInTimeLine pos)
    {
        AbstractRawContainer currentCont=this.getContainerOnPosition(pos);
        if(currentCont==null)
        {
            return false;
        }
        AbstractRawContainer frontHalf= this.getCurrentContainer().cut(this.currentPosition.currentFrameCount);
        this.addAfter(frontHalf,this.getCurrentContainerCount());
        return true;
    }

    public AbstractRawContainer getCurrentContainer() {

        return containers.get(this.currentPosition.currentContainer);   // do not change, to avoid circuit relevance
    }

    public int getCurrentContainerCount() {
        if (containers.size()==0)
        {
            return -1;
        }

        return containers.indexOf(getCurrentContainer());
    }

    public AbstractRawContainer getContainerOnPosition(PositionInTimeLine pos) {
        if (containers.size()==0)
        {
            return null;
        }
        else
        {
            return containers.get(currentPosition.currentContainer);
        }
    }

    public Image getCurrentFrame() {
        if (containers.size()==0)
        {
            return null;
        }
        else
        {
            return containers.get(this.currentPosition.currentContainer).getFrame(this.currentPosition.currentFrameCount);
        }
    }

    public int getCurrentFrameCount()
    {
        //// TODO: 26.02.2016  
        return 0;
    }
}
