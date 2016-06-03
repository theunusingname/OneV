package OneV.app;

import OneV.app.Effects.Effect;
import OneV.app.Effects.EffectQue;

import java.awt.*;
import java.io.File;
import java.util.Collection;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface FramesCut  {
    void addFrame(MovieFrame frame);
    Image getFrame(int pos);
    File getFrameFile(int pos);
    boolean hasNext(int pos);
    int size();
    int frameIndex(MovieFrame img);
    FramesCut cut(int pos);
    Collection<File> getFiles();
    Collection<Image> getImages();
    void addEffect(Effect effect);
    void removeEffect(int index);
    EffectQue getEffectQue();
}
