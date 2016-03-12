package OneV.app.old;

import java.awt.*;
import java.io.File;
import java.util.Vector;

/**
 * Created by Константин on 10.03.2016.
 */
public class ImageFilesContainer implements RawContainer {
    private Vector<Image> images;
    private Vector<File> files;
    @Override
    public void addImage(Image img) {

    }

    @Override
    public Image getFrame(int pos) {
        return null;
    }

    @Override
    public boolean hasNext(int pos) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int frameIndex(Image img) {
        return 0;
    }

    @Override
    public RawContainer cut(int pos) {
        return null;
    }
}
