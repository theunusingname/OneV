package OneV.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

/**
 * Created by kkuznetsov on 10.03.2016.
 */
public class FileLoader implements Loader {

    boolean needResize;
    int width,height;

    DefaultLoader defaultLoader=new DefaultLoader();
    private File[] imageFilesArray;

    FileLoader()
    {}

    FileLoader(boolean needResize) {
        this(needResize,0 ,0 );
        needResize=false;
    }

    FileLoader(boolean needResize, int width, int height)
    {
        this.needResize=needResize;
        this.width=width;
        this.height=height;

    }

    @Override
    public RawContainer getContainer(Path path) {
        Vector<Image> imagesVectorForResult=new Vector<>();
        Vector<File> fileLinks =new Vector<>();
        File imageFolder=path.toFile();
        DefaultRawContainer result=null;
        try {
            // TODO: 01.03.2016 проверить вложенные папки
            if (imageFolder.isDirectory()) {

                imageFilesArray = imageFolder.listFiles();
                for (File imageFile : imageFilesArray) {

                    imagesVectorForResult.add(ImageIO.read(imageFile));

                }
                result = new DefaultRawContainer(imagesVectorForResult);
            } else if (imageFolder.isFile()) {
                imagesVectorForResult.add(ImageIO.read(imageFolder));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void addImages(RawContainer container, Path path) {

    }
}
