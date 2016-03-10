package OneV.app;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Hashtable;

/**
 * Created by Константин on 10.03.2016.
 */
public class PreviewImage extends BufferedImage {
    private File file;
    boolean complete;
    public PreviewImage(int width, int height, int imageType) {
        super(width, height, imageType);
        complete=false;
    }

    public PreviewImage(int width, int height, int imageType, IndexColorModel cm) {
        super(width, height, imageType, cm);
        complete=false;
    }

    public PreviewImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
        super(cm, raster, isRasterPremultiplied, properties);
        complete=false;
    }

    public boolean addFile(File file)
    {
       if (file==null)
       {
           return complete;
       }
        return complete=true;
    }

    public boolean itsComplete()
    {
        return complete;
    }
}
