package OneV.app;

import java.awt.*;

/**
 * Created by kkuznetsov on 03.03.2016.
 */
public class DefaultMovieView implements AbstractMovieView {


    @Override
    public void showFrame(Image img) {
        System.out.println(img.toString());
    }

    @Override
    public void showFrame(AbstractRawContainer cont, int pos) {

    }
}
