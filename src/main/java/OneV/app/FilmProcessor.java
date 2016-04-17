package OneV.app;

import java.io.IOException;

/**
 * Created by Константин on 19.02.2016.
 */
public interface FilmProcessor {

    public boolean saveGif() throws IOException;
    public boolean saveMovie() throws IOException;
}
