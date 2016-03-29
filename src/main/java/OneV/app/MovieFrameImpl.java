package OneV.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public class MovieFrameImpl implements MovieFrame, Externalizable {
    private Image img;
    private File file;
    MovieFrameImpl()
    {
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(file);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
       file=(File) in.readObject();
        img= ImageIO.read(file);
    }
}
