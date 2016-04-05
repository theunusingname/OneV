package OneV.app;

import com.sun.imageio.plugins.gif.GIFImageWriter;
import com.sun.imageio.plugins.gif.GIFImageWriterSpi;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by kkuznetsov on 04.04.2016.
 */
public class FilmProcessorImpl implements FilmProcessor {
    CutsTimeline timeline;

    public FilmProcessorImpl(CutsTimeline tl) {
        timeline = tl;
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

        ImageOutputStream output = new FileImageOutputStream(saveFileDialog.getFiles()[0]);
        BufferedImage firstImage = (BufferedImage) timeline.getContainerOnPosition(new PositionInTimeLine(0, 0)).getFrame(0);
        GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 1, false);
        writer.writeToSequence(firstImage);

        PositionInTimeLine position = timeline.getCurrentPosition();
        int cuts = timeline.getContainersSize();
        for (int i = position.currentContainer; i < cuts; i++) {
            FramesCut currentCont = timeline.getContainerOnPosition(position);
            for (int j = position.currentFrameCount; j < currentCont.size(); j++) {

                writer.writeToSequence((BufferedImage)currentCont.getFrame(j));
            }
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

