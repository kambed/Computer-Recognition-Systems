package backend.reader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import backend.model.Root;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class SgmReader implements FileReader {
    @Override
    public Optional<Root> read(String path) {
        try {
            String xmlString = Files.readString(Path.of(path))
                    .replace("<!DOCTYPE lewis SYSTEM \"lewis.dtd\">",
                            "<?xml version=\"1.1\"?><ROOT>")
                    .concat("</ROOT>");
            return Optional.of((Root) JAXBContext.newInstance(Root.class)
                    .createUnmarshaller()
                    .unmarshal(new StringReader(xmlString)));
        } catch (JAXBException | IOException exception) {
            return Optional.empty();
        }
    }
}
