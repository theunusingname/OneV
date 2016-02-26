package OneV.app;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Константин on 19.02.2016.
 */
public interface AbstractRawContainer {

    Image getFrame(int pos);
    int lastGated();
    boolean hasNext(int pos);
    int size();
    int frameCount(Image img);
    AbstractRawContainer cut(int pos);
    

}
