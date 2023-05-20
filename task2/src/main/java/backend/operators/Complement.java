package backend.operators;

import backend.functions.BaseFunction;
import backend.sets.CrispSet;

public class Complement extends AbstractOperator {

    public BaseFunction operation(CrispSet set) {
        return new BaseFunction(set.getFunction().getDomain(),
                x -> 1 - set.getFunction().getValue(x));
    }

    @Override
    public BaseFunction operation(CrispSet s1, CrispSet s2) {
        return operation(s1);
    }
}
