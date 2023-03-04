package pl.ksr.computerrecognitionsystems.reader;

import pl.ksr.computerrecognitionsystems.model.Root;

import java.util.Optional;

public interface FileReader {
    Optional<Root> read(String path);
}
