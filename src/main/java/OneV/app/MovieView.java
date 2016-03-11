package OneV.app;

import OneV.app.old.RawContainer;

import java.awt.*;


/**
 * Created by Константин on 27.02.2016.
 */
public interface MovieView {

    void  showFrame(Image img);
    void showFrame(RawContainer cont, int pos);

}
