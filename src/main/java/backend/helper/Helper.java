package backend.helper;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Helper {
    private Helper() {
    }
    public static String getFilePath(Object object, String fileName) throws URISyntaxException {
        return Paths.get(
                Objects.requireNonNull(
                        object.getClass()
                                .getResource(fileName)
                ).toURI()
        ).toString();
    }
}
