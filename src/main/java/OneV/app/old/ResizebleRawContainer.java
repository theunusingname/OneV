package OneV.app.old;

import java.awt.*;
import java.io.File;
import java.util.Vector;

/**
 * Created by Константин on 09.03.2016.
 */
public class ResizebleRawContainer implements RawContainer {
    private DefaultRawContainer container;
    private int width, height;
    private int scaledHint=Image.SCALE_SMOOTH;

    public ResizebleRawContainer(int width, int height, int hint)
    {
        this.width=width;
        this.height=height;
        scaledHint=hint;
        container=new DefaultRawContainer();
    }

    public ResizebleRawContainer(int width, int height, int hint,Vector<Image> init)
    {
        this(width,height,hint);
        container=new DefaultRawContainer(init);

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
    public RawContainer cut(int pos) {
        return container.cut(pos);
    }

}
