package OneV.app.GUI;

import OneV.app.CutsTimelineImpl;
import OneV.app.DefaultMovieView;
import OneV.app.old.TimeLine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class MoviePreViewPanel extends JPanel {
    DefaultMovieView movieView;

    MoviePreViewPanel()
    {
        movieView=new DefaultMovieView();
        add(movieView);
        setSize(movieView.getSize());
        setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }
}
