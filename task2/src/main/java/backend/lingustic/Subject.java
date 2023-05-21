package backend.lingustic;

import backend.model.Stats;

import java.util.List;

public class Subject {
    private String name;
    private List<Stats> elements;

    public Subject(String name, List<Stats> elements) {
        this.name = name;
        this.elements = elements;
    }
}
