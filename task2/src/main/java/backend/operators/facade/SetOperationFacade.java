package backend.operators.facade;

import backend.operators.Complement;
import backend.operators.Multiply;
import backend.operators.Sum;
import backend.sets.CrispSet;

public class SetOperationFacade {

    private SetOperationFacade() {
    }

    private static final Complement complement = new Complement();
    private static final Multiply multiply = new Multiply();
    private static final Sum sum = new Sum();

    public static CrispSet complement(CrispSet set) {
        return complement.execute(set);
    }

    public static CrispSet multiply(CrispSet s1, CrispSet s2) {
        return multiply.execute(s1, s2);
    }

    public static CrispSet sum(CrispSet s1, CrispSet s2) {
        return sum.execute(s1, s2);
    }
}
