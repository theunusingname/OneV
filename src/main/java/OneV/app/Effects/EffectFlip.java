package OneV.app.Effects;

import OneV.app.CutLoaderImpl;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by theun on 17.05.2016.
 */


public class EffectFlip implements Effect, Serializable {

    double rotationRequired = Math.toRadians (180);

    @Override
    public Image performEffect(Image img) {
        return createRotated((BufferedImage) img);
    }

    private static BufferedImage createRotated(BufferedImage image)
    {
        AffineTransform at = AffineTransform.getRotateInstance(
                Math.PI, image.getWidth()/2, image.getHeight()/2.0);
        return createTransformed(image, at);
    }

    private static BufferedImage createTransformed(
            BufferedImage image, AffineTransform at)
    {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}
