package OneV.app;

import java.nio.file.Path;

/**
 * Created by Константин on 19.02.2016.
 */
public interface AbstractLoader {

    AbstractRawContainer loadImages(Path path);

    AbstractRawContainer addImages(AbstractRawContainer container, Path path);
}
