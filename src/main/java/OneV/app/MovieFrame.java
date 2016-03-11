package OneV.app;

import java.awt.*;
import java.io.File;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface MovieFrame {
    void setFrame(Image img);
    void setLink(File file);
    Image getFrame();
    File getFile();
}
