package OneV.app;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface CutLoader {

    FramesCut getCut(File[] files);
    FramesCut getCutWithDialog();
    void addFrame(FramesCut container, Path path);
}
