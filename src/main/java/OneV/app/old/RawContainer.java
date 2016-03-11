package OneV.app.old;

import java.awt.*;

/**
 * Created by Константин on 19.02.2016.
 */
public interface RawContainer {

    void addImage(Image img);
    Image getFrame(int pos);
    boolean hasNext(int pos);
    int size();
    int frameIndex(Image img);
    RawContainer cut(int pos);
    

}
