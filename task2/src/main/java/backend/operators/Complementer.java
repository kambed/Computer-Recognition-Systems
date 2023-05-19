package backend.operators;

import backend.functions.DefaultFunction;
import backend.sets.CrispSet;

public class Complementer extends AbstractOperator {

    public DefaultFunction operation(CrispSet set) {
        return new DefaultFunction(set.getFunction().getDomain(),
                x -> 1 - set.getFunction().getValue(x));
    }

    @Override
    public DefaultFunction operation(CrispSet s1, CrispSet s2) {
        return operation(s1);
    }
}
