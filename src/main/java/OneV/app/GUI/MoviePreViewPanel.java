package OneV.app.GUI;


import OneV.app.DefaultMovieView;


import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kkuznetsov on 18.03.2016.
 */
public class MoviePreViewPanel extends JPanel implements ActionListener {
    protected DefaultMovieView movieView;
    MainWindow parent;
    JButton start=new JButton("start");
    JButton stop=new JButton("stop");
    JButton pause=new JButton("pause");
    JSpinner fps=new JSpinner();
    JPanel buttonPanel=new JPanel(new FlowLayout());

    MoviePreViewPanel(MainWindow parent)
    {
        super();
        setLayout(new BorderLayout());
        this.parent=parent;
        movieView=new DefaultMovieView();
        add(movieView,BorderLayout.CENTER);
        start.addActionListener(parent);
        pause.addActionListener(parent);
        stop.addActionListener(parent);
        fps.addChangeListener(parent);
        fps.setName("fps");
        fps.setValue(24);
        buttonPanel.add(start);
        buttonPanel.add(pause);
        buttonPanel.add(stop);
        buttonPanel.add(fps);
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
