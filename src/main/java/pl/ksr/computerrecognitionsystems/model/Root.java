package pl.ksr.computerrecognitionsystems.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "ROOT")
public class Root {
    @XmlElement(name = "REUTERS")
    private List<Article> articles;
}
