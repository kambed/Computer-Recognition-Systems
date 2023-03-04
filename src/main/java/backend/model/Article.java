package backend.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Getter;

import java.util.List;

@Getter
public class Article {
    @XmlElement(name = "DATE")
    private String date;
    @XmlElementWrapper(name = "TOPICS")
    @XmlElement(name = "D")
    private List<String> topics;
    @XmlElementWrapper(name = "PLACES")
    @XmlElement(name = "D")
    private List<String> places;
    @XmlElementWrapper(name = "PEOPLE")
    @XmlElement(name = "D")
    private List<String> people;
    @XmlElementWrapper(name = "ORGS")
    @XmlElement(name = "D")
    private List<String> orgs;
    @XmlElementWrapper(name = "EXCHANGES")
    @XmlElement(name = "D")
    private List<String> exchanges;
    @XmlElementWrapper(name = "COMPANIES")
    @XmlElement(name = "D")
    private List<String> companies;
    @XmlElement(name = "TEXT")
    private TextContent text;
}
