package backend.operators;

import backend.functions.DefaultFunction;
import backend.sets.CrispSet;

public class Summarizer extends AbstractOperator {

    public DefaultFunction operation(CrispSet s1, CrispSet s2) {
        return new DefaultFunction(s1.getFunction().getDomain(),
                x -> Math.max(s1.getFunction().getValue(x), s2.getFunction().getValue(x)));
    }
}
