package OneV.app.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by kkuznetsov on 22.03.2016.
 */
public class EditToolbarElement extends JPanel {

    private ActionListener parent;
    protected JButton cutButton;
    protected JButton deleteButton;
    protected JButton copyButton;

    EditToolbarElement(ActionListener parent)
    {
        super();
        cutButton=new JButton("Cut");
        deleteButton=new JButton("Delete");
        copyButton=new JButton("Copy");
        add(cutButton);
        add(deleteButton);
        add(copyButton);
        cutButton.addActionListener(parent);
        deleteButton.addActionListener(parent);
        copyButton.addActionListener(parent);
        this.parent=parent;
    }
}
