package backend.model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TextContent {
    @XmlElement(name="TITLE")
    private String title;
    @XmlElement(name="DATELINE")
    private String dateline;
    @XmlElement(name="BODY")
    private String text;
}
