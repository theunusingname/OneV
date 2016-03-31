package OneV.app;

import com.sun.glass.ui.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RunnableFuture;
import java.util.stream.*;

/**
 * Created by Константин on 12.03.2016.
 */
public class CutLoaderImpl implements CutLoader {

    private String directory;
    private File[] imageFilesArray;
    private int width;
    private int height;
    private int scaleHint;
    private boolean inLoading;
    private boolean needAwaitLoading;
    private MediaTracker tracker;
    Thread tr;



    public CutLoaderImpl(int width, int height)
    {
        needAwaitLoading=true;
        this.width=width;
        this.height=height;
        scaleHint=Image.SCALE_SMOOTH;
    }

    @Override
    public FramesCut getCut(File[] files)  {
        ArrayList<MovieFrame> frames=new ArrayList<>();
        for (File file: files) {
//            if(file.isFile()&&
//                    file.toString().toLowerCase().endsWith(".jpeg")&&
//                    file.toString().toLowerCase().endsWith(".jpg"))
//            {
                frames.add(new MovieFrameImpl(null,file));

//            }
        }
        Stream<MovieFrame> frameStream=frames.stream();
        frameStream.parallel().forEachOrdered(frame ->{
            try {
                System.out.println("Loading:"+frame.getFile());
                frame.setFrame(ImageIO.read(frame.getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );
//        CountDownLatch latch=new CountDownLatch(files.length);
        
        
//        tr=new Thread(()-> {
//            synchronized (frames){
//            inLoading=true;
//            for (File file : imageFilesArray) {
//                try {
//                    Image img = ImageIO.read(file);
//                    if (img == null) {
//                        latch.countDown();
//                        continue;
//                    } else {
//                        System.out.println("Loading:" + file.toString());
//                        MovieFrame frame = new MovieFrameImpl(toBufferedImage(img.getScaledInstance(width, height, scaleHint)), file);
//                        frames.add(frame);
//                        latch.countDown();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//                System.out.println("loading finished");
//                inLoading=false;
//
//        }});
//        tr.start();
//
//        if(needAwaitLoading) {
//            try {
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        return new FramesCutImpl(frames);
    }

    @Override
    public FramesCut getCutWithDialog() {
        Frame dialogFrame=new Frame();
        FileDialog choseFilesDialog =new FileDialog(dialogFrame,"Chose Images",FileDialog.LOAD);
        choseFilesDialog.setMultipleMode(true);
        choseFilesDialog.setVisible(true);
        imageFilesArray= choseFilesDialog.getFiles();
        if (imageFilesArray.length==0)
        {
            System.out.println("no files selected");
            return null;
        }

        return this.getCut(imageFilesArray);
    }

    @Override
    public void addFrame(FramesCut container, Path path) {
        //// TODO: 12.03.2016  
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public boolean isInLoading()
    {
        return inLoading;
    }

    public boolean isNeedAwaitLoading() {
        return needAwaitLoading;
    }

    public void setNeedAwaitLoading(boolean needAwaitLoading) {
        this.needAwaitLoading = needAwaitLoading;
    }
}

