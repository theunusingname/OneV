package OneV.app.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 18.03.2016.
 */
public class ToolbarPanel extends JToolBar {

    LoadingToolbarElement loadingToolbarElement;
    EditToolbarElement editToolbarElement;
    ActionListener parent;

    ToolbarPanel(ActionListener parent)
    {
        super();
        this.parent=parent;
        setLayout(new FlowLayout());
        loadingToolbarElement =new LoadingToolbarElement(parent);
        editToolbarElement= new EditToolbarElement(parent);
        add(loadingToolbarElement,FlowLayout.LEFT);
        addSeparator(new Dimension(2,10));
        add(new JButton("button"));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }
}
