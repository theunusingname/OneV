package OneV.app;

/**
 * Created by Константин on 27.02.2016.
 */
public interface AbstractTimeLineDriver {
    void play(int fps);
    void stop();
    boolean gotoPosition(PositionInTimeLine pos);
}
