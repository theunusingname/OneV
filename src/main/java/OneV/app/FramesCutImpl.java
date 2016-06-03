package OneV.app;

import OneV.app.Effects.Effect;
import OneV.app.Effects.EffectQue;
import OneV.app.Effects.EffectQueImpl;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public class FramesCutImpl implements FramesCut, Serializable {
    private volatile ArrayList<MovieFrame> frames;
    private int lastGeted;
    private EffectQue effectQue=new EffectQueImpl();

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

    Stream<File> getFileStream()
    {
        if(frames.size()==0)
        {
            return null;
        }
        else
        {
           return frames.stream().map((a)-> a.getFile());
        }
    }

    @Override
    public void addFrame(MovieFrame frame) {
        frames.add(frame);
    }

    @Override
    public Image getFrame(int pos) {
        lastGeted=pos;
        return frames.get(pos).getFrame();
    }

    @Override
    public File getFrameFile(int pos) {
        lastGeted=pos;
        return frames.get(pos).getFile();
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

    @Override
    public Collection<File> getFiles() {
       return frames.stream().map((a)->a.getFile()).collect(Collectors.toList());
    }

    @Override
    public Collection<Image> getImages() {
        return frames.stream().map((a)->a.getFrame()).collect(Collectors.toList());
    }

    @Override
    public void addEffect(Effect effect)
    {
        effectQue.addEffect(effect);
        for (MovieFrame frame: frames)
        {
            frame.setFrame(effect.performEffect(frame.getFrame()));
        }
        System.out.println("effect is added");
    }

    @Override
    public void removeEffect(int index)
    {
        effectQue.removeEffect(index);
    }

    @Override
    public EffectQue getEffectQue() {
        return effectQue;
    }
}
