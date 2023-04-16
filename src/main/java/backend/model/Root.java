package backend.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.List;

@XmlRootElement(name = "ROOT")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class Root {
    @XmlElement(name = "REUTERS")
    private List<Article> articles;
}
