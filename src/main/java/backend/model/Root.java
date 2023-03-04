package backend.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.List;

@XmlRootElement(name = "ROOT")
@Getter
public class Root {
    @XmlElement(name = "REUTERS")
    private List<Article> articles;
}
