package backend.operators;

import backend.functions.DefaultFunction;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;

public class Summarizer {

    public CrispSet summarize(CrispSet s1, CrispSet s2) {
        DefaultFunction function = new DefaultFunction(s1.getFunction().getDomain(),
                x -> Math.max(s1.getFunction().getValue(x), s2.getFunction().getValue(x)));
        if (s1 instanceof FuzzySet || s2 instanceof FuzzySet)
            return new FuzzySet(function);
        return new CrispSet(function);
    }
}
