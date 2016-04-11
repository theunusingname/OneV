package OneV.app;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public class CutsTimelineImpl implements CutsTimeline, Externalizable {
    private PositionInTimeLine currentPosition;
    private ArrayList<FramesCut> containers = new ArrayList<>();
    private TimeLineDriver currentDriver;

    public CutsTimelineImpl()
    {
        currentPosition=new PositionInTimeLine(0,0);
    }

    @Override
    public PositionInTimeLine getCurrentPosition() {
        return currentPosition;
    }

    public ArrayList<FramesCut> getCutsList()
    {
        return new ArrayList<FramesCut>(containers);
    }

    @Override
    public void addCuts(Collection<FramesCut> cuts) {
        for (FramesCut cut: cuts) {
            this.addCut(cut);
        }
    }

    @Override
    public void addCut(FramesCut cut) {
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
        if (this.getCurrentCutIndex()==0)
        {
            this.addCut(cut);
        }
        else
        {
            containers.add(this.getCurrentCutIndex(),cut);
        }
        if(currentDriver!=null) currentDriver.updateSlider();    }

    @Override
    public void addAfter(FramesCut cut, int cutCount) {
        this.addBefore(cut, cutCount +1);
       if(currentDriver!=null) currentDriver.updateSlider();
    }

    @Override
    public void deleteCut(PositionInTimeLine pos) {
        if(containers.size()==1)
        {
            System.out.println("can't delete last cut");
            return;
        }
        containers.remove(pos.currentContainer);
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
        FramesCut frontHalf= this.getCurrentCut().cut(this.currentPosition.currentFrameCount);
        this.addAfter(frontHalf,this.getCurrentCutIndex());
        return true;
    }

    @Override
    public FramesCut getCurrentCut() {
        return containers.get(currentPosition.currentContainer);
    }

    @Override
    public int getCurrentCutIndex() {
        if (containers.size()==0)
        {
            return -1;
        }

        return containers.indexOf(getCurrentCut());
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
    public int getCutsSize() {
        return containers.size();
    }

    @Override
    public int getOverallSize() {
        int result=0;
        for (FramesCut cut: containers) {
            result+=cut.size();
        }
        return result;
    }

    @Override
    public void setDriver(TimeLineDriver currentDriver) {
        this.currentDriver = currentDriver;
    }

    @Override
    public Stream<File> getFileStream() {
       if(containers.size()==0)
           return null;

        ArrayList<File> listForResult=new ArrayList<>();
        for (FramesCut cut: containers) {
            listForResult.addAll(cut.getFiles());
        }
        return listForResult.stream();
    }

    @Override
    public Stream<Image> getImageStream() {
        if(containers.size()==0)
            return null;

        ArrayList<Image> listForResult=new ArrayList<>();
        for (FramesCut cut: containers) {
            listForResult.addAll(cut.getImages());
        }
        return listForResult.stream();
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(containers);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        containers=(ArrayList<FramesCut>) in.readObject();
    }
}
