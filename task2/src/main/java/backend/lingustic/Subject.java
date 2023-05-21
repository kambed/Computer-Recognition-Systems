package backend.lingustic;

import backend.model.Stats;
import lombok.Getter;

import java.util.List;

@Getter
public class Subject {
    private String name;
    private List<Stats> elements;

    public Subject(String name, List<Stats> elements) {
        this.name = name;
        this.elements = elements;
    }
}
