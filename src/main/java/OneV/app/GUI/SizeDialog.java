package OneV.app.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kkuznetsov on 06.04.2016.
 */
public class SizeDialog extends JDialog implements ActionListener{
    int widthVal = -1;
    int heightVal = -1;
    JPanel widthPanel;
    JPanel heightPanel;
    JLabel widthLable = new JLabel("Width: ");
    JLabel heightLable = new JLabel("Height: ");
    JTextField widthField = new JTextField();
    JTextField heightField = new JTextField();
    JButton okButton=new JButton("OK");


    SizeDialog(Frame owner, String text) {
        super(owner, text);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        widthField.setPreferredSize(new Dimension(200,15));
        heightField.setPreferredSize(new Dimension(200,15));

        widthPanel = new JPanel();
        widthPanel.setLayout(new BoxLayout(widthPanel, BoxLayout.LINE_AXIS));
        widthPanel.add(widthLable);
        widthPanel.add(widthField);
        heightPanel = new JPanel();
        heightPanel.setLayout(new BoxLayout(heightPanel, BoxLayout.LINE_AXIS));
        heightPanel.add(heightLable);
        heightPanel.add(heightField);
        add(widthPanel);
        add(heightPanel);
        add(okButton);
        setSize(300, 150);
        setVisible(true);
    }

    private void parseValues()
    {
        widthVal = Integer.parseInt(widthField.getText());
        heightVal=Integer.parseInt(heightField.getText());
    }

    public int getWidthValue() {

        return widthVal;
    }

    public int getHeightValue() {

        return heightVal;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "OK":
            {
                parseValues();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Frame frame=new Frame("fr");
            frame.setVisible(true);
            SizeDialog d=new SizeDialog(frame,"sds");
        });

    }
}