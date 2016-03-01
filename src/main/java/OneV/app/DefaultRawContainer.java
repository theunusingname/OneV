package OneV.app;

import java.awt.*;
import java.util.AbstractCollection;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by Константин on 26.02.2016.
 */
public class DefaultRawContainer implements AbstractRawContainer {

    public DefaultRawContainer(){this.container=new Vector<>();}
    public DefaultRawContainer(Vector<Image> cont)
    {
        this.container=new Vector<Image>(cont);
    }

    private Vector<Image> container;
    private int lastImg;
    public void addImage(Image img)
    {
        container.add(img);
    }

    public Image getFrame(int pos) {
        lastImg =pos;
        return container.get(pos);
    }

    public int lastGated() {
        return lastImg;
    }

    public boolean hasNext(int pos) {
        ListIterator it= container.listIterator(pos);
        return it.hasNext();
    }

    public int size() {
        return container.size();
    }

    public int frameIndex(Image img) {
        return container.indexOf(img);
    }

    public AbstractRawContainer cut(int pos) {
        AbstractRawContainer result=new DefaultRawContainer(new Vector<Image> (container.subList(pos, container.size() - 1)));
        container.setSize(pos);
        return result;
    }
}
