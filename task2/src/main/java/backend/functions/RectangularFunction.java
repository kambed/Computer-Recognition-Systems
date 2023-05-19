package backend.functions;

import backend.domain.ContinuousDomain;
import backend.domain.Domain;

public class RectangularFunction extends TrapezoidalFunction {

    public RectangularFunction(double minSupp, double maxSupp) {
        this(new ContinuousDomain(-Double.MAX_VALUE, Double.MAX_VALUE), minSupp, maxSupp);
    }

    public RectangularFunction(Domain domain, double minSupp, double maxSupp) {
        super(domain, minSupp, minSupp, maxSupp, maxSupp);
    }
}
