package OneV.app;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public class FramesCutImpl implements FramesCut {
    private ArrayList<MovieFrame> frames;
    private int lastGeted;

    FramesCutImpl()
    {
        frames=new ArrayList<>();
        lastGeted=-1;
    }

    FramesCutImpl(ArrayList<MovieFrame> initList)
    {
        frames= new ArrayList<>(initList);
        lastGeted=-1;
    }

    @Override
    public void addFrame(MovieFrame frame) {
        frames.add(frame);
    }

    @Override
    public MovieFrame getFrame(int pos) {
        lastGeted=pos;
        return frames.get(pos);
    }

    @Override
    public boolean hasNext(int pos) {

        return frames.size()>pos;
    }

    @Override
    public int size() {
        return frames.size();
    }

    @Override
    public int frameIndex(MovieFrame img) {
        return frames.indexOf(img);
    }

    @Override
    public FramesCut cut(int pos) {
        ArrayList<MovieFrame> rightPart = new ArrayList<>(frames.subList(pos,frames.size()));
        frames.removeAll(rightPart);
        return new FramesCutImpl(rightPart);
    }
}
