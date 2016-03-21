package OneV.app.GUI;

import OneV.app.CutLoader;
import OneV.app.CutLoaderImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by kkuznetsov on 21.03.2016.
 */
public class LoadingToolbarElement extends JPanel {

    private CutLoaderImpl loader;
    private JButton loadButton;

    LoadingToolbarElement(CutLoader loader)
    {
        super();
        loader = loader;
        setLayout(new FlowLayout());
        loadButton=new JButton("load");
        add(loadButton);
        setVisible(true);
        loadButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });


    }


}
