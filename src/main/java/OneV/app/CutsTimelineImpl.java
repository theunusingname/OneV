package OneV.app;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public class CutsTimelineImpl implements CutsTimeline, Serializable{
    private PositionInTimeLine currentPosition=new PositionInTimeLine(0,0);
    private ArrayList<FramesCut> containers = new ArrayList<>();
    private TimeLineDriverImpl currentDriver;

    @Override
    public PositionInTimeLine getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void addContainer(FramesCut cut) {
        containers.add(cut);
        if(currentDriver!=null) currentDriver.updateSlider();
    }

    @Override
    public void addBefore(FramesCut cut, int cutCount) {
        if(cutCount >0) {
            this.setPosition(new PositionInTimeLine(cutCount - 1, 0));
        }else if(cutCount ==0) {
            this.setPosition(new PositionInTimeLine(0,0));
        }else {
            return;
        }
        this.addBeforeCurrent(cut);
            }

    public void addBeforeCurrent(FramesCut cut)
    {
        if (this.getCurrentContainerIndex()==0)
        {
            this.addContainer(cut);
        }
        else
        {
            containers.add(this.getCurrentContainerIndex(),cut);
        }
        if(currentDriver!=null) currentDriver.updateSlider();    }

    @Override
    public void addAfter(FramesCut cut, int cutCount) {
        this.addBefore(cut, cutCount +1);
       if(currentDriver!=null) currentDriver.updateSlider();
    }

    @Override
    public void setPosition(PositionInTimeLine pos) {
       if(pos.currentContainer<=containers.size()) {
           if (pos.currentFrameCount <= containers.get(pos.currentContainer).size()) {
               currentPosition = pos;
           }
       }
        else System.out.println("cant set roller");
    }

    @Override
    public boolean cut(PositionInTimeLine pos) {

        FramesCut currentCont=this.getContainerOnPosition(pos);
        if(currentCont==null)
        {
            return false;
        }
        FramesCut frontHalf= this.getCurrentContainer().cut(this.currentPosition.currentFrameCount);
        this.addAfter(frontHalf,this.getCurrentContainerIndex());
        return true;
    }

    @Override
    public FramesCut getCurrentContainer() {
        return containers.get(currentPosition.currentContainer);
    }

    @Override
    public int getCurrentContainerIndex() {
        if (containers.size()==0)
        {
            return -1;
        }

        return containers.indexOf(getCurrentContainer());
    }

    @Override
    public FramesCut getContainerOnPosition(PositionInTimeLine pos) {
        if (containers.size()==0 || containers.size()<pos.currentContainer)
        {
            return null;
        }
        else
        {
            return containers.get(pos.currentContainer);
        }
    }

    @Override
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

    @Override
    public File getCurrentFile() {
        if (containers.size()==0)
        {
            return null;
        }
        else
        {
            return containers.get(this.currentPosition.currentContainer).getFrameFile(this.currentPosition.currentFrameCount);
        }
    }

    @Override
    public int getCurrentFrameIndex() {
        return currentPosition.currentFrameCount;
    }

    @Override
    public int getContainersSize() {// TODO: 24.03.2016 need rename
        return containers.size();
    }

    @Override
    public void setDriver(TimeLineDriverImpl currentDriver) {
        this.currentDriver = currentDriver;
    }


}
