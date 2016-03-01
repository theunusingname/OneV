package OneV.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

/**
 * Created by Константин on 01.03.2016.
 */
public class DefaultLoader implements AbstractLoader {

    File[] imageFilesArray;


    @Override
    public AbstractRawContainer getContainer(Path path) {
        Vector<Image> imagesVectorForResult=new Vector<>();
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
    public void addImages(AbstractRawContainer container, Path path) {

    }
}
