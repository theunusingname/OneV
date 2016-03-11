package OneV.app.old;

import OneV.app.PositionInTimeLine;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Константин on 21.02.2016.
 */
public class DefaultTimeLine implements TimeLine {

    private PositionInTimeLine currentPosition=new PositionInTimeLine(0,0);
    private Vector<RawContainer> containers =new Vector<RawContainer>();
//    private TimeLine previewTL= new TimeLine();


    public DefaultTimeLine()
    {
        ;
    } //// TODO: 09.03.2016 impement resize and save all img in added container for preview play  !!!

    public PositionInTimeLine getCurentPosition() {
        return currentPosition;
    }

    public void addContainer(RawContainer container) {
        containers.add(container);
    }

    public void addBefore(RawContainer container, int containerCount) {

        if(containerCount>0) {
            this.setPosition(new PositionInTimeLine(containerCount - 1, 0));
        }else if(containerCount==0) {
            this.setPosition(new PositionInTimeLine(0,0));
        }else {
            return;
        }
        this.addBeforeCurrent(container);
        }

    public void addAfter(RawContainer container, int containerCount) {
        this.addBefore(container,containerCount+1);
    }

    @Override
    public void setPosition(PositionInTimeLine pos) {
        if (pos.currentContainer<=this.getContainersSize() && pos.currentFrameCount<= getContainerOnPosition(pos).size())
            currentPosition=pos;
    }

    public void addBeforeCurrent(RawContainer container) {
        if (this.getCurrentContainerIndex()==0)
        {
            this.addContainer(container);
        }
        else
        {
            containers.insertElementAt(container,this.getCurrentContainerIndex());
        }
    }

    public boolean cut(PositionInTimeLine pos)
    {
        RawContainer currentCont=this.getContainerOnPosition(pos);
        if(currentCont==null)
        {
            return false;
        }
        RawContainer frontHalf= this.getCurrentContainer().cut(this.currentPosition.currentFrameCount);
        this.addAfter(frontHalf,this.getCurrentContainerIndex());
        return true;
    }

    public RawContainer getCurrentContainer() {

        return containers.get(this.currentPosition.currentContainer);   // do not change, to avoid circuit call
    }

    public int getCurrentContainerIndex() {
        if (containers.size()==0)
        {
            return -1;
        }

        return containers.indexOf(getCurrentContainer());
    }

    public RawContainer getContainerOnPosition(PositionInTimeLine pos) {
        if (containers.size()==0 || containers.size()<pos.currentContainer)
        {
            return null;
        }
        else
        {
            return containers.get(pos.currentContainer);
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

    public int getCurrentFrameIndex()
    {
        //// TODO: 26.02.2016  
        return 0;
    }

    public int getContainersSize() {
        return containers.size();
    }

    private void addPreview(){}
}
