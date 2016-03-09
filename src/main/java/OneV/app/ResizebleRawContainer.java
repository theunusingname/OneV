package OneV.app;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.io.File;
import java.util.Vector;

/**
 * Created by Константин on 09.03.2016.
 */
public class ResizebleRawContainer implements AbstractRawContainer {
    private DefaultRawContainer container;
    private Vector<File> linkedFiles;
    private int width, height;
    private int scaledHint=Image.SCALE_SMOOTH;

    ResizebleRawContainer(int w, int h)
    {
        width=w;
        height=h;
        container=new DefaultRawContainer();
    }


    @Override
    public void addImage(Image img) {
        container.addImage(img.getScaledInstance(width,height,scaledHint));

    }

    @Override
    public Image getFrame(int pos) {
        return container.getFrame(pos);
    }

    @Override
    public boolean hasNext(int pos) {
        return container.hasNext(pos);
    }

    @Override
    public int size() {
        return container.size();
    }

    @Override
    public int frameIndex(Image img) {
        return container.frameIndex(img);
    }

    @Override
    public AbstractRawContainer cut(int pos) {
        return container.cut(pos);
    }

}
