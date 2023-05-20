package backend.lingustic;

import lombok.Getter;

import java.util.List;

@Getter
public class Variable {
    private final String name;
    private final List<Label> labels;

    public Variable(String name, List<Label> labels) {
        this.name = name;
        this.labels = labels;
    }
}
