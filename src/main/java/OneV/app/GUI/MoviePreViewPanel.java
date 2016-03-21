package OneV.app.GUI;


import OneV.app.DefaultMovieView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class MoviePreViewPanel extends JPanel implements ActionListener {
    protected DefaultMovieView movieView;
    ActionListener parent;
    JButton start=new JButton("start");
    JButton stop=new JButton("stop");
    JPanel buttonPanel=new JPanel(new FlowLayout());

    MoviePreViewPanel(ActionListener parent)
    {
        super();
        setLayout(new BorderLayout());
        this.parent=parent;
        movieView=new DefaultMovieView();
        add(movieView,BorderLayout.CENTER);
        start.addActionListener(parent);
        stop.addActionListener(parent);
        buttonPanel.add(start);
        buttonPanel.add(stop);
        add(buttonPanel,BorderLayout.SOUTH);
        setSize(movieView.getSize());
        setBorder(BorderFactory.createLineBorder(Color.black,1));
        setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
