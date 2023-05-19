package backend.functions;


import backend.domain.ContinuousDomain;
import backend.domain.Domain;

public class TrapezoidalFunction extends AbstractFunction {

    public TrapezoidalFunction(double minSupp, double minHeight, double maxHeight, double maxSupp) {
        this(new ContinuousDomain(-Double.MAX_VALUE, Double.MAX_VALUE), minSupp, minHeight, maxHeight, maxSupp);
    }

    public TrapezoidalFunction(Domain domain, double minSupp, double minHeight, double maxHeight, double maxSupp) {
        super(domain, x -> {
            if (x < minSupp || x > maxSupp) {
                return 0.0;
            } else if (x >= minSupp && x <= minHeight) {
                return (minHeight - minSupp) == 0 ? 1 : (x - minSupp) / (minHeight - minSupp);
            } else if (x >= maxHeight && x <= maxSupp) {
                return (maxSupp - maxHeight) == 0 ? 1 : (maxSupp - x) / (maxSupp - maxHeight);
            } else {
                return 1.0;
            }
        });
    }
}
