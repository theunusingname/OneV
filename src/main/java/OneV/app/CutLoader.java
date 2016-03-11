package OneV.app;

import java.nio.file.Path;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface CutLoader {

    FramesCut getCut(Path path);
    FramesCut getCutWithDialog();
    void addFrame(FramesCut container, Path path);
}
