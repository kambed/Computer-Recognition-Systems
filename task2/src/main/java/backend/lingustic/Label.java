package backend.lingustic;

import backend.functions.BaseFunction;
import backend.sets.FuzzySet;
import lombok.Getter;

@Getter
public class Label extends FuzzySet {

    private final String name;

    public Label(String name, BaseFunction function) {
        super(function);
        this.name = name;
    }
}
