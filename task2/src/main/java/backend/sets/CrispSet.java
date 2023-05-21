package backend.sets;

import backend.functions.BaseFunction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrispSet {
    private BaseFunction function;

    public CrispSet(BaseFunction function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return function.toString() + "\n";
    }
}
