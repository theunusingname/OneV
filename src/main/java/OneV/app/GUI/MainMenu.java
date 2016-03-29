package OneV.app.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by kkuznetsov on 24.03.2016.
 */
public class MainMenu extends JMenuBar {

    ActionListener parent;
    JMenu file=new JMenu("File");
    JMenuItem saveItem=new JMenuItem("Save");
    JMenuItem loadItem=new JMenuItem("Load");
    JMenuItem loadProjItem=new JMenuItem("LoadProject");

    MainMenu(ActionListener parent)
    {
        super();
        add(file);
        file.add(saveItem);
        file.add(loadItem);
        file.add(loadProjItem);
        this.parent=parent;
        saveItem.addActionListener(parent);
        loadItem.addActionListener(parent);
        loadProjItem.addActionListener(parent);
        this.setVisible(true);
    }
}
