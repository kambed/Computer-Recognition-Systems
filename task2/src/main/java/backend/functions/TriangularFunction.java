package backend.functions;

import backend.domain.ContinuousDomain;
import backend.domain.Domain;

public class TriangularFunction extends TrapezoidalFunction {

    public TriangularFunction(double minSupp, double max, double maxSupp) {
        this(new ContinuousDomain(-Double.MAX_VALUE, Double.MAX_VALUE), minSupp, max, maxSupp);
    }

    public TriangularFunction(Domain domain, double minSupp, double max, double maxSupp) {
        super(domain, minSupp, max, max, maxSupp);
    }
}
