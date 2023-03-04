package pl.ksr.computerrecognitionsystems.model;

import jakarta.xml.bind.annotation.XmlElement;

public class TextContent {
    @XmlElement(name="TITLE")
    private String title;
    @XmlElement(name="DATELINE")
    private String dateline;
    @XmlElement(name="BODY")
    private String text;
}
