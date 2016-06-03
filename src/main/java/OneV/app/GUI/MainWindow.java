package OneV.app.GUI;

import OneV.app.*;
import OneV.app.Effects.EffectFlip;
import OneV.app.Effects.EffectQue;
import OneV.app.Effects.EffectQueImpl;

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
    private JFrame mainFrame;
    private JMenuBar mainMenubar;
    protected CutsTimelineImpl mainTimeLine=new CutsTimelineImpl();
    protected TimeLineDriverImpl driver;
    protected FilmProcessor processor;

    public MainWindow()
    {
        processor=new FilmProcessorImpl(mainTimeLine);
        moviePreViewPanel=new MoviePreViewPanel(this);
        driver=new TimeLineDriverImpl(mainTimeLine,moviePreViewPanel.movieView);
        loader=new CutLoaderImpl(320,240);
        timeLineSlider=driver.getSlider();
        toolbarPanel =new ToolbarPanel(this);
        mainMenubar=new MainMenu(this);
        mainFrame =new JFrame("OneV");
        mainFrame.setJMenuBar(mainMenubar);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(toolbarPanel,BorderLayout.NORTH);
        mainFrame.add(moviePreViewPanel,BorderLayout.CENTER);
        mainFrame.add(timeLineSlider,BorderLayout.SOUTH);
        mainFrame.setSize(800,600);
        mainFrame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "load":
                FramesCut cutToAdd=loader.getCutWithDialog();
                if (cutToAdd!=null)
                    mainTimeLine.addCut(cutToAdd);
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
                SizeDialog sizeDialog= new SizeDialog(mainFrame,"setSize");
                processor.setResultSize(sizeDialog.getWidthValue(),sizeDialog.getHeightValue());
                try {
                    processor.saveGif();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            break;
            case "export to movie":

                try {
                    processor.saveMovie();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            case "cut":
                mainTimeLine.cut(mainTimeLine.getCurrentPosition());
                break;
            case "delete":
                mainTimeLine.deleteCut(mainTimeLine.getCurrentPosition());
                break;
            case  "flip":
                mainTimeLine.getCurrentCut().addEffect(new EffectFlip());
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
