package OneV.app;

import java.io.IOException;

/**
 * Created by Константин on 19.02.2016.
 */
public interface FilmProcessor {

    boolean saveGif() throws IOException;
    boolean saveMovie();
}
