package OneV.app;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Константин on 26.03.2016.
 */
public class SaveLoadTimeLine {
    public static void save(CutsTimelineImpl timeline) throws IOException
    {
        Frame dialogFrame=new Frame();
        FileDialog dialog =new FileDialog(dialogFrame,"Save",FileDialog.SAVE);
        dialog.setVisible(true);
        File output=new File( dialog.getDirectory()+dialog.getFile()+".ovp");
        FileOutputStream fos=new FileOutputStream(output);
        ObjectOutputStream oos =new ObjectOutputStream(fos);
        oos.writeInt(timeline.getCutsSize());
        for (int i = timeline.getCutsSize(); i>0; i--)
        {
            oos.writeObject( timeline.getContainerOnPosition(new PositionInTimeLine(i-1,0)));
        }
        oos.close();
        fos.close();
    }

    public static ArrayList<FramesCut> load() throws IOException, ClassNotFoundException {
        ArrayList<FramesCut> result=new ArrayList<>();
        Frame dialogFrame=new Frame();
        FileDialog dialog =new FileDialog(dialogFrame,"Save",FileDialog.LOAD);
        dialog.setVisible(true);
        File input=new File(dialog.getDirectory()+ dialog.getFile());
        FileInputStream fis=new FileInputStream(input);
        ObjectInputStream ois=new ObjectInputStream(fis);
        int size=ois.readInt();
        for (;size>0;size--)
        {
            result.add((FramesCutImpl)ois.readObject());
        }
        ois.close();
        fis.close();
        return result;
    }
}
