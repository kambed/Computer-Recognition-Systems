package backend.reader;

import java.util.Map;

public interface FileReader {
    public Map<String, String> read(String path);
}
