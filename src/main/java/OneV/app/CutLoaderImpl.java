package OneV.app;

import OneV.app.GUI.ProgressWidget;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
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
    Thread tr;



    public CutLoaderImpl(int width, int height)
    {
        needAwaitLoading=false;
        this.width=width;
        this.height=height;
        scaleHint=Image.SCALE_SMOOTH;
    }

    @Override
    @Nullable
    public FramesCut getCut(File[] files)  {
        ArrayList<MovieFrame> frames=new ArrayList<>();

        inLoading=true;
        for (File file : files) {
            if(file.isFile()&&(
                    file.toString().toLowerCase().endsWith(".jpeg")||
                    file.toString().toLowerCase().endsWith(".jpg")))
            {
            frames.add(new MovieFrameImpl(null, file));
            }
        }
        if(frames.size()==0)
            return null;

        ProgressWidget progressBar=new ProgressWidget(0,frames.size(),"Loading images");
        tr=new Thread(()-> {
            Stream<MovieFrame> frameStream = frames.stream();
            System.out.println(Thread.currentThread().getName());
            Date date = new Date();
            //start loading
            long timer = date.getTime();
            frameStream.parallel().forEach(frame -> {
                try {
                    progressBar.setTextInfo("Loading: " + frame.getFile().getName());
                    frame.setFrame(toBufferedImage(ImageIO.read(frame.getFile()).getScaledInstance(width, height, scaleHint)));
                    progressBar.incrementProgress(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            //sorting by date
            frames.stream().parallel().sorted((a, b) -> {
                long la = a.getFile().lastModified();
                long lb = b.getFile().lastModified();
                if (la >= lb)
                    return 1;
                else
                    return -1;
            });
            System.out.println("Loading finished " + frames.size() + " files, in " + (new Date().getTime() - timer) + " mseconds");
            progressBar.dispose();
            inLoading = false;
        });
        tr.start();

        if(needAwaitLoading) {
            try {
                tr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new FramesCutImpl(frames);
    }

    @Override
    @Nullable
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

