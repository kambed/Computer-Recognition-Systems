package backend.model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextContent {
    @XmlElement(name = "TITLE", defaultValue = "")
    private String title;
    @XmlElement(name = "DATELINE", defaultValue = "")
    private String dateline;
    @XmlElement(name = "BODY", defaultValue = "")
    private String text;

    public String getText() {
        return Optional.ofNullable(text).orElse("");
    }

    public String getTitle() {
        return Optional.ofNullable(title).orElse("");
    }

    public String getPreprocessedText() {
        return getText().trim().replaceAll("\\p{P}+(?=\\s|$)", "");
    }
}
