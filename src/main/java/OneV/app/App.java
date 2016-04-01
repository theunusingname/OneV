package OneV.app;


import OneV.app.GUI.MainWindow;


import javax.swing.*;

public class App
{
    public static void main( String[] args ) throws InterruptedException {

        final MainWindow[] mainWindow = new MainWindow[1];

        SwingUtilities.invokeLater(()->{
            mainWindow[0] = new MainWindow();
        });





    }
}
