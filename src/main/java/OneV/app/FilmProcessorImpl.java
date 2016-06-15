package OneV.app;

import OneV.app.Effects.EffectQue;
import OneV.app.GUI.ProgressWidget;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.stream.Stream;

/**
 * Created by kkuznetsov on 04.04.2016.
 */
public class FilmProcessorImpl implements FilmProcessor {
    CutsTimeline timeline;
    int width=320;
    int height=240;
    Thread tr;
    Process p;
    volatile int progress=0;
    ProgressWidget progressMonitor;


    public FilmProcessorImpl(CutsTimeline tl) {
        timeline = tl;
    }

    @Override
    public void setResultSize(int width,int height)
    {
        this.width=width;
        this.height=height;
    }


    @Override
    public void saveGif() throws IOException {
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



}

    @Override
    public void saveMovie() throws IOException
    {

        Frame dialogFrame = new Frame();
        FileDialog saveFileDialog = new FileDialog(dialogFrame, "Save Movie", FileDialog.SAVE);
        saveFileDialog.setVisible(true);
        if (timeline==null||timeline.getOverallSize()==0)
        {
            System.out.println("cant save");
        }

        File ffmpeg_output_msg = new File("ffmpeg_output_msg.txt");
        ProcessBuilder pb = new ProcessBuilder(   //Параметры и модификаторы отдельными сторками!!!
                "ffmpeg",
                "-f", "image2pipe",
                "-i","pipe:0",
                "-f", "mp4",
                "-vcodec","h264",
                "-b", "10m",
                "-r", "200",
                "-y",
                saveFileDialog.getDirectory()+saveFileDialog.getFile()+".mp4");
        pb.redirectErrorStream(true);
        pb.redirectOutput(ffmpeg_output_msg);
        pb.redirectInput(ProcessBuilder.Redirect.PIPE);
        p = null;
        try {
            p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        OutputStream ffmpegInput = p.getOutputStream();

        progressMonitor=new ProgressWidget(0,timeline.getOverallSize(),"saveMovie");
        DefaultMovieView littleMovieViev=new DefaultMovieView();
        littleMovieViev.setSize(320,240);
        progressMonitor.add(littleMovieViev);
        progress=0;
        Stream<File> fileStream = timeline.getFileStream();
        tr=new Thread(()->{
        fileStream.forEach((file) -> {
            progress++;
            progressMonitor.setProgress(progress);
            progressMonitor.setTextInfo(file.getName());
            byte[] image;
            image = new byte[(int) file.length()];
            FileInputStream fileInputStream = null;
            try {

                fileInputStream = new FileInputStream(file);
                fileInputStream.read(image);
                ImageInputStream iis = ImageIO.createImageInputStream(
                        new ByteArrayInputStream(image));
                BufferedImage img = ImageIO.read(iis);
                littleMovieViev.showFrame(CutLoaderImpl.toBufferedImage(img.getScaledInstance(320,240,Image.SCALE_FAST)));
                EffectQue effectQue= timeline.getContainerOnPosition(TimeLineDriverImpl.intToPosition(timeline,progress)).getEffectQue();
                ImageIO.write((RenderedImage) effectQue.performEffects(img), "JPEG", ffmpegInput);
                fileInputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                progressMonitor.dispose();
                }
            });

            progressMonitor.dispose();
            try {
                ffmpegInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tr.start();


    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}

