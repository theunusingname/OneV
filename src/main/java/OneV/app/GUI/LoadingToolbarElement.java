package OneV.app.GUI;

import OneV.app.CutLoader;
import OneV.app.CutLoaderImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by kkuznetsov on 21.03.2016.
 */
public class LoadingToolbarElement extends JPanel {

    private JButton loadButton;
    public ActionListener parent;



    LoadingToolbarElement( ActionListener parent)
    {
        super();
        this.parent=parent;
        setLayout(new FlowLayout());
        loadButton=new JButton("load");
        add(loadButton);
        setVisible(true);
        loadButton.addActionListener(parent);

    }


}
