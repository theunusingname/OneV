package OneV.app;

import java.io.File;
import java.nio.file.Path;
import java.util.Vector;

/**
 * Created by kkuznetsov on 10.03.2016.
 */
public class FileLoader implements Loader {

    boolean needResize;
    int width,height;
    Vector<File> fileLinks =new Vector<>();
    DefaultLoader defaultLoader=new DefaultLoader();

    @Override
    public RawContainer getContainer(Path path) {
        return null;
    }

    @Override
    public void addImages(RawContainer container, Path path) {

    }
}
