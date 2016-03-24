package OneV.app;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public class MovieFrameImpl implements MovieFrame, Serializable {
    private Image img;
    private File file;

    MovieFrameImpl(Image img, File file)
    {
        this.img=img;
        this.file=file;
    }

    @Override
    public void setFrame(Image img) {
        this.img=img;
    }

    @Override
    public void setLink(File file) {
        this.file=file;
    }

    @Override
    public Image getFrame() {
        return img;
    }

    @Override
    public File getFile() {
        return file;
    }
}
