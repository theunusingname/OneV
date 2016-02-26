package OneV.app;

import java.awt.*;
import java.util.AbstractCollection;
import java.util.LinkedList;

/**
 * Created by Константин on 19.02.2016.
 */
public interface AbstractRawContainer {

    void addImage(Image img);
    Image getFrame(int pos);
    boolean hasNext(int pos);
    int size();
    int frameCount(Image img);
    AbstractRawContainer cut(int pos);
    

}
