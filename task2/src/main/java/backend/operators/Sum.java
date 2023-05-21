package backend.operators;

import backend.functions.BaseFunction;
import backend.sets.CrispSet;

public class Sum extends AbstractOperator {

    public BaseFunction operation(CrispSet s1, CrispSet s2) {
        if (s2 == null) {
            throw new IllegalArgumentException("Second set cannot be null");
        }
        return new BaseFunction(s1.getFunction().getDomain(),
                x -> Math.max(s1.getFunction().getValue(x), s2.getFunction().getValue(x)));
    }
}
