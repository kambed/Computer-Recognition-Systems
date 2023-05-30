package backend.operators;

import backend.domain.ContinuousDomain;
import backend.functions.BaseFunction;
import backend.sets.CrispSet;

public class Intersection extends AbstractOperator {

    public BaseFunction operation(CrispSet s1, CrispSet s2) {
        if (s2 == null) {
            throw new IllegalArgumentException("Second set cannot be null");
        }
        return new BaseFunction(new ContinuousDomain(Math.min(s1.getFunction().getDomain().getMin(), s2.getFunction().getDomain().getMin()), Math.max(s1.getFunction().getDomain().getMax(), s2.getFunction().getDomain().getMax())),
                x -> Math.min(s1.getFunction().getValue(x), s2.getFunction().getValue(x)));
    }
}
