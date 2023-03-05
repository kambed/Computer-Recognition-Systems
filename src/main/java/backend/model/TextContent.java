package backend.model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Character> sentenceEnds = List.of('?','.','!',',');
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Arrays.stream(text.trim()
                .split("\\s+"))
                .map(word -> {
                    String changed = word;
                    while (sentenceEnds.contains(changed.charAt(changed.length() - 1))) {
                        changed = changed.substring(0, changed.length() - 1);
                    }
                    return changed;
                }).collect(Collectors.joining(" "));
    }
}
