package backend.functions;

import java.util.function.Function;

public class TriangularFunction {

    private final TrapezoidalFunction triangular;

    public TriangularFunction(double minSupp, double max, double maxSupp) {
        this.triangular = new TrapezoidalFunction(minSupp, max, max, maxSupp);
    }

    public double getValue(double x) {
        return triangular.getValue(x);
    }
}
