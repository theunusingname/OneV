package OneV.app.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Константин on 18.03.2016.
 */
public class ToolbarPanel extends JPanel {

    LoadingToolbarElement loadingPart;

    ToolbarPanel()
    {
        super();
        setLayout(new FlowLayout());
        loadingPart=new LoadingToolbarElement();
        add(loadingPart);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }
}
