package backend.sets;

import backend.functions.DefaultFunction;
import lombok.Getter;

@Getter
public class CrispSet {
    private final DefaultFunction function;

    public CrispSet(DefaultFunction function) {
        this.function = function;
    }
}
