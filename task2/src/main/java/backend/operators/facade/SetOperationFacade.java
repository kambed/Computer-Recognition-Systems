package backend.operators.facade;

import backend.operators.Complement;
import backend.operators.Intersection;
import backend.operators.Union;
import backend.sets.CrispSet;

public class SetOperationFacade {

    private SetOperationFacade() {
    }

    private static final Complement complement = new Complement();
    private static final Intersection INTERSECTION = new Intersection();
    private static final Union UNION = new Union();

    public static CrispSet complement(CrispSet set) {
        return complement.execute(set);
    }

    public static CrispSet multiply(CrispSet s1, CrispSet s2) {
        return INTERSECTION.execute(s1, s2);
    }

    public static CrispSet sum(CrispSet s1, CrispSet s2) {
        return UNION.execute(s1, s2);
    }
}
