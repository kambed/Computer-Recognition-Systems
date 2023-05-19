package backend.sets;

import backend.functions.AbstractFunction;
import backend.functions.RectangularFunction;

public class CrispSet {
    private final AbstractFunction function;

    public CrispSet(RectangularFunction function) {
        this.function = function;
    }

    protected CrispSet(AbstractFunction function) {
        this.function = function;
    }
}
