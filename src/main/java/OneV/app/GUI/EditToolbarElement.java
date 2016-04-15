package OneV.app.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kkuznetsov on 22.03.2016.
 */
public class EditToolbarElement extends JPanel {

    private ActionListener parent;
    protected JButton cutButton;
    protected JButton deleteButton;
    protected JButton copyButton;
    protected JButton pasteButton;

    EditToolbarElement(ActionListener parent)
    {
        super();
        setLayout(new GridLayout(2,3,2,2));
        cutButton=new JButton("Cut");
        deleteButton=new JButton("Delete");
        copyButton=new JButton("Copy");
        pasteButton=new JButton("Paste");
        add(cutButton);
        add(deleteButton);
        add(copyButton);
        add(pasteButton);
        cutButton.addActionListener(parent);
        deleteButton.addActionListener(parent);
        copyButton.addActionListener(parent);
        pasteButton.addActionListener(parent);
        this.parent=parent;
    }
}
