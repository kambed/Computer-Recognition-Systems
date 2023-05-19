package backend.operators;

import backend.functions.DefaultFunction;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;

public class Multiplier {

    public CrispSet multiply(CrispSet s1, CrispSet s2) {
        DefaultFunction function = new DefaultFunction(s1.getFunction().getDomain(),
                x -> Math.min(s1.getFunction().getValue(x), s2.getFunction().getValue(x)));
        if (s1 instanceof FuzzySet || s2 instanceof FuzzySet)
            return new FuzzySet(function);
        return new CrispSet(function);
    }
}
