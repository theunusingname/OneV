package OneV.app;


import OneV.app.GUI.MainWindow;
//import nu.pattern.OpenCV;

import javax.swing.*;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println(System.getProperty("java.library.path"));
        final MainWindow[] mainWindow = new MainWindow[1];

        SwingUtilities.invokeLater(()->{
            mainWindow[0] = new MainWindow();


        });





    }
}
