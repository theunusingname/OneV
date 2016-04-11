package OneV.app.GUI;

import OneV.app.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by kkuznetsov on 21.03.2016.
 */
public class MainWindow implements ActionListener,ChangeListener {
    private CutLoaderImpl loader;
    private ToolbarPanel toolbarPanel;
    private MoviePreViewPanel moviePreViewPanel;
    private TimeLineSlider timeLineSlider;
    private JFrame frame;
    private JMenuBar mainMenubar;
    protected CutsTimelineImpl mainTimeLine=new CutsTimelineImpl();
    protected TimeLineDriverImpl driver;

    public MainWindow()
    {
        moviePreViewPanel=new MoviePreViewPanel(this);
        driver=new TimeLineDriverImpl(mainTimeLine,moviePreViewPanel.movieView);
        loader=new CutLoaderImpl(320,240);
        timeLineSlider=driver.getSlider();
        toolbarPanel =new ToolbarPanel(this);
        mainMenubar=new MainMenu(this);
        frame=new JFrame("OneV");
        frame.setJMenuBar(mainMenubar);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(toolbarPanel,BorderLayout.NORTH);
        frame.add(moviePreViewPanel,BorderLayout.CENTER);
        frame.add(timeLineSlider,BorderLayout.SOUTH);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.repaint();
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "load":
                mainTimeLine.addCut( loader.getCutWithDialog());
                break;
            case "start": driver.play();
                break;
            case  "pause": driver.pause();
                break;
            case "stop": driver.stop();
                break;
            case "save":
                try {
                    if(mainTimeLine.getCutsSize()>0) {
                        SaveLoadTimeLine.save(mainTimeLine);
                        break;
                    }
                    else break;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;

            case "loadproject":
                try {
                    mainTimeLine.addCuts(SaveLoadTimeLine.load());

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                System.out.println("loading finished");
                break;
            case "export to gif":
            {
                SizeDialog sizeDialog= new SizeDialog(frame,"setSize");
                FilmProcessorImpl processor=new FilmProcessorImpl(mainTimeLine);
                processor.setResultSize(sizeDialog.getWidthValue(),sizeDialog.getHeightValue());
                try {
                    processor.saveGif();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            break;
            case "cut":
                mainTimeLine.cut(mainTimeLine.getCurrentPosition());
                break;
            case "delete":
                mainTimeLine.deleteCut(mainTimeLine.getCurrentPosition());
                break;
            default:
                System.out.println("Unknown command: " +e.getActionCommand());

        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Component component=(Component) e.getSource();
        String name=component.getName();
        if(name.equalsIgnoreCase("fps")&&driver!=null)
        {
            int fps=(Integer)((JSpinner) component).getValue();
            if(fps>0)
                driver.setFPS(fps);
        }
    }
}
