package OneV.app;

import java.awt.*;
import java.io.*;

/**
 * Created by Константин on 26.03.2016.
 */
public class SaveLoadTimeLine {
    public static void save(CutsTimelineImpl timeline) throws IOException
    {
        Frame dialogFrame=new Frame();
        FileDialog dialog =new FileDialog(dialogFrame,"Save",FileDialog.SAVE);
        dialog.setVisible(true);
        File output=new File( dialog.getFile());
        FileOutputStream fos=new FileOutputStream(output);
        ObjectOutputStream oos =new ObjectOutputStream(fos);
        oos.writeObject(timeline);
    }
}
