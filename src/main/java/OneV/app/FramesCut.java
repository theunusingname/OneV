package OneV.app;

import java.awt.*;
import java.io.File;

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
}
