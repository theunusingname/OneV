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
    JMenu export= new JMenu("Export");
    JMenuItem exportToGIF=new JMenuItem("Export to GIF");
    JMenuItem exportToMovie=new JMenuItem("Export to Movie");


    MainMenu(ActionListener parent)
    {
        super();
        add(file);
        file.add(saveItem);
        file.add(loadItem);
        file.add(loadProjItem);
        add(export);
        export.add(exportToGIF);
        export.add(exportToMovie);
        this.parent=parent;
        saveItem.addActionListener(parent);
        loadItem.addActionListener(parent);
        loadProjItem.addActionListener(parent);
        exportToGIF.addActionListener(parent);
        exportToMovie.addActionListener(parent);
        this.setVisible(true);
    }
}
