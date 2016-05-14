package OneV.app.Effects;

import java.awt.*;
import java.util.List;

/**
 * Created by theun on 14.05.2016.
 */
public interface EffectQue {
    public void addEffect(Effect effect);
    public void removeEffect(int index);
    public List<Effect> getEffectList();
    public void performEffects(Image img);
}
