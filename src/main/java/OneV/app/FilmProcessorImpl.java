package OneV.app;

import OneV.app.GUI.ProgressWidget;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by kkuznetsov on 04.04.2016.
 */
public class FilmProcessorImpl implements FilmProcessor {
    CutsTimeline timeline;
    int width=320;
    int height=240;
    ProgressWidget progressWidget;

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
        if (timeline==null||timeline.getOverallSize()==0)
        {
            System.out.println("cant save");
        }
        //init writer with first image
        ImageOutputStream output = new FileImageOutputStream(new File(saveFileDialog.getDirectory()+saveFileDialog.getFile()+".gif"));
        BufferedImage firstImage =
                CutLoaderImpl.toBufferedImage (
                        (timeline.getContainerOnPosition(new PositionInTimeLine(0, 0)).getFrame(0).getScaledInstance(width,height,Image.SCALE_SMOOTH)));
        GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 1, false);
        writer.writeToSequence(firstImage);

        PositionInTimeLine position = new PositionInTimeLine(0,0);
        timeline.setPosition(position);
        int cuts = timeline.getCutsSize();
        int maxProgress=timeline.getOverallSize();
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
    public boolean saveMovie() throws IOException {
        Frame dialogFrame = new Frame();
        FileDialog saveFileDialog = new FileDialog(dialogFrame, "Save Movie", FileDialog.SAVE);
        saveFileDialog.setVisible(true);
        if (timeline == null || timeline.getOverallSize() == 0) {
            System.out.println("cant save");
        }

        File ffmpeg_output_msg = new File("ffmpeg_output_msg.txt");
        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg.exe", "-i", "pipe:0", saveFileDialog.getDirectory() + saveFileDialog.getFile() + ".avi");
        pb.redirectErrorStream(true);
        pb.redirectOutput(ffmpeg_output_msg);
        pb.redirectInput(ProcessBuilder.Redirect.PIPE);
        Process p = null;
        p = pb.start();
        OutputStream ffmpegInput = p.getOutputStream();
        ProgressMonitor progressMonitor = new ProgressMonitor(new Frame(), null, "Creating movie...", 0, timeline.getOverallSize());

            Stream<File> fileStream = timeline.getFileStream();
            fileStream.forEach((file) -> {
                progressMonitor.setProgress(1);
                byte[] image;
                image = new byte[(int) file.length()];
                FileInputStream fileInputStream = null;
                try {

                    fileInputStream = new FileInputStream(file);

                    fileInputStream.read(image);

                    ImageInputStream iis = ImageIO.createImageInputStream(
                            new ByteArrayInputStream(image));
                    BufferedImage img = ImageIO.read(iis);

                    ImageIO.write(img, "JPEG", ffmpegInput);
                    fileInputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        p.destroy();

//        try {
//                ffmpegInput.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        return false;
    }

//    public static void main(String[] args) throws IOException {
//        File ffmpeg_output_msg = new File("ffmpeg_output_msg.txt");
//        ProcessBuilder pb = new ProcessBuilder(
//                "ffmpeg.exe","-i","pipe:0","out.avi");
//        pb.redirectErrorStream(true);
//        pb.redirectOutput(ffmpeg_output_msg);
//        pb.redirectInput(ProcessBuilder.Redirect.PIPE);
//        Process p = pb.start();
//        OutputStream ffmpegInput = p.getOutputStream();
//
//        byte[] image;
//        File file = new File("input.jpg");
//        image = new byte[(int)file.length()];
//
//        FileInputStream fileInputStream = new FileInputStream(file);
//        fileInputStream.read(image);
//
//        ImageInputStream iis = ImageIO.createImageInputStream(
//                new ByteArrayInputStream(image));
//        BufferedImage img = ImageIO.read(iis);
//
//        ImageIO.write(img, "JPEG", ffmpegInput);
//    }
}

