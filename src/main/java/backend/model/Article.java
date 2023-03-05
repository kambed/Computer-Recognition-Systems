package backend.model;

import backend.model.adapter.DateAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @XmlElement(name = "DATE")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;
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
