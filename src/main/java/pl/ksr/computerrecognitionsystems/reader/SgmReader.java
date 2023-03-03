package pl.ksr.computerrecognitionsystems.reader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import pl.ksr.computerrecognitionsystems.model.Root;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class SgmReader implements FileReader {
    @Override
    public Root read(String path) {
        try {
            String xmlString = Files.readString(Path.of(path))
                    .replace("<!DOCTYPE lewis SYSTEM \"lewis.dtd\">",
                            "<?xml version=\"1.1\"?><ROOT>")
                    .concat("</ROOT>");
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Root) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        } catch (JAXBException | IOException exception) {
            return null;
        }
    }
}
