package OneV.app.GUI;

import OneV.app.CutLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 18.03.2016.
 */
public class ToolbarPanel extends JPanel {

    LoadingToolbarElement loadingPart;
    ActionListener parent;

    ToolbarPanel(ActionListener parent)
    {
        super();
        this.parent=parent;
        setLayout(new FlowLayout());
        loadingPart=new LoadingToolbarElement(parent);
        add(loadingPart);
        add(new JPopupMenu.Separator());
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }
}
