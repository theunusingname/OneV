package OneV.app.old;

import java.nio.file.Path;

/**
 * Created by Константин on 19.02.2016.
 */
public interface Loader {

    RawContainer getContainer(Path path);

    void addImages(RawContainer container, Path path);
}
