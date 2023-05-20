package backend.operators;

import backend.functions.BaseFunction;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;

public abstract class AbstractOperator {

    public CrispSet execute(CrispSet s1) {
        return execute(s1, null);
    }

    public CrispSet execute(CrispSet s1, CrispSet s2) {
        BaseFunction function = operation(s1, s2);
        if (s1 instanceof FuzzySet || s2 instanceof FuzzySet) {
            return new FuzzySet(function);
        }
        return new CrispSet(function);
    }

    public abstract BaseFunction operation(CrispSet s1, CrispSet s2);
}
