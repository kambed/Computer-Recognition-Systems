package backend.reader;

import backend.model.Root;

import java.util.Optional;

public interface FileReader {
    Optional<Root> read(String path);
}
