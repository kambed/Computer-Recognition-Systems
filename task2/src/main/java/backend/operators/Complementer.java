package backend.operators;

import backend.functions.DefaultFunction;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;

public class Complementer {

    public CrispSet complement(CrispSet set) {
        DefaultFunction function = new DefaultFunction(set.getFunction().getDomain(),
                x -> 1 - set.getFunction().getValue(x));
        if (set instanceof FuzzySet)
            return new FuzzySet(function);
        return new CrispSet(function);
    }
}
