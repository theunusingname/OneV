package OneV.app.GUI;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by kkuznetsov on 21.03.2016.
 */
public class MainWindow  {

    private ToolbarPanel toolbarPanel1;
    private JFrame frame;

    public MainWindow()
    {
        frame=new JFrame("OneV");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ToolbarPanel());
        frame.setVisible(true);
    }



}
