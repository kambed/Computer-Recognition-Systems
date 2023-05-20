package backend.sets.factory;

import backend.functions.BaseFunction;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;

public class SetFactory {
    private SetFactory() {
    }

    public static CrispSet createCrispSet(BaseFunction function) {
        return new CrispSet(function);
    }

    public static FuzzySet createFuzzySet(BaseFunction function) {
        return new FuzzySet(function);
    }
}
