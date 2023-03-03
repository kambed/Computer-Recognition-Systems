package pl.ksr.computerrecognitionsystems.reader;

import pl.ksr.computerrecognitionsystems.model.Root;

public interface FileReader {
    Root read(String path);
}
