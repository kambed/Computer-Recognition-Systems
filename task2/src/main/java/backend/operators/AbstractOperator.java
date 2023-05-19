package backend.operators;

import backend.functions.DefaultFunction;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;

public abstract class AbstractOperator {

    public CrispSet execute(CrispSet s1, CrispSet... s2) {
        DefaultFunction function = operation(s1, s2[0]);
        if (s1 instanceof FuzzySet || s2[0] instanceof FuzzySet)
            return new FuzzySet(function);
        return new CrispSet(function);
    }

    public abstract DefaultFunction operation(CrispSet s1, CrispSet s2);
}
