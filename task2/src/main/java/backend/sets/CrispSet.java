package backend.sets;

import backend.functions.BaseFunction;
import lombok.Getter;

@Getter
public class CrispSet {
    private final BaseFunction function;

    public CrispSet(BaseFunction function) {
        this.function = function;
    }
}
