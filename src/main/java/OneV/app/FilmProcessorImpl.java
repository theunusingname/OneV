package OneV.app;

import OneV.app.GUI.ProgressWidget;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by kkuznetsov on 04.04.2016.
 */
public class FilmProcessorImpl implements FilmProcessor {
    CutsTimeline timeline;
    int width=320;
    int height=240;


    public FilmProcessorImpl(CutsTimeline tl) {
        timeline = tl;
    }

    public void setResultSize(int width,int height)
    {
        this.width=width;
        this.height=height;
    }

    @Override
    public boolean saveGif() throws IOException {
        Frame dialogFrame = new Frame();
        FileDialog saveFileDialog = new FileDialog(dialogFrame, "Save GIF", FileDialog.SAVE);
        saveFileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });
        saveFileDialog.setVisible(true);
        //init writer with first image
        ImageOutputStream output = new FileImageOutputStream(saveFileDialog.getFiles()[0]);
        BufferedImage firstImage =
                CutLoaderImpl.toBufferedImage (
                        (timeline.getContainerOnPosition(new PositionInTimeLine(0, 0)).getFrame(0).getScaledInstance(width,height,Image.SCALE_SMOOTH)));
        GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 1, false);
        writer.writeToSequence(firstImage);

        PositionInTimeLine position = new PositionInTimeLine(0,0);
        timeline.setPosition(position);
        int cuts = timeline.getCutsSize();
        int maxProgress=timeline.getOvervalSize();
        ProgressWidget progressWidget=new ProgressWidget(0,maxProgress,"Save");
        progressWidget.setVisible(true);

        System.out.println("start");
        for (int i = position.currentContainer; i < cuts; i++) {
            FramesCut currentCont = timeline.getContainerOnPosition(position);
            for (int j = position.currentFrameCount; j < currentCont.size(); j++) {
                BufferedImage nextImage=CutLoaderImpl.toBufferedImage (ImageIO.read(currentCont.getFrameFile(j)).getScaledInstance(width,height,Image.SCALE_SMOOTH));
                writer.writeToSequence(nextImage);
                progressWidget.incrementProgress(1);
            }
            progressWidget.setVisible(false);
            System.out.println("finish");

            writer.close();
            output.close();
        }


    return false;
}
    @Override
    public boolean saveMovie() {
        return false;
    }
}

