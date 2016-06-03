package OneV.app.Effects;

import OneV.app.CutLoader;
import OneV.app.CutLoaderImpl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by theun on 18.05.2016.
 */
public class EffectQueImpl implements EffectQue , Serializable{
    private List<Effect> effectList;

    public EffectQueImpl()
    {
        effectList=new LinkedList<>();
    }

    @Override
    public void addEffect(Effect effect) {
        if (effect!=null)
        {
            effectList.add(effect);
        }else {
            System.out.println("null effect");
            return;
        }
    }

    @Override
    public void removeEffect(int index) {
        effectList.remove(index);
    }

    @Override
    public List<Effect> getEffectList() {
        return effectList;
    }

    @Override
    public Image performEffects(Image img) {
        for (Effect effect: effectList
             )
        {
           img= effect.performEffect(img);
        }
     return img;
    }
}
