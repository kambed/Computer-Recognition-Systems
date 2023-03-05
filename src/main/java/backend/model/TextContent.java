package backend.model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextContent {
    @XmlElement(name="TITLE")
    private String title;
    @XmlElement(name="DATELINE")
    private String dateline;
    @XmlElement(name="BODY")
    private String text;

    public String getPreprocessedText() {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.trim().replaceAll("\\p{P}+(?=\\s|$)", "");
    }
}
