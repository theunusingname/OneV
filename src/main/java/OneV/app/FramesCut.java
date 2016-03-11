package OneV.app;

/**
 * Created by kkuznetsov on 11.03.2016.
 */
public interface FramesCut  {
    void addFrame(MovieFrame frame);
    MovieFrame getFrame(int pos);
    boolean hasNext(int pos);
    int size();
    int frameIndex(MovieFrame img);
    FramesCut cut(int pos);
}
