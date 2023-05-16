package backend.functions;

import java.util.function.Function;

public class TrapezoidalFunction {

    private final Function<Double, Double> trapezoidal;

    public TrapezoidalFunction(double minSupp, double minHeight, double maxHeight, double maxSupp) {
        this.trapezoidal = x -> {
            if (x < minSupp || x > maxSupp) {
                return 0.0;
            } else if (x >= minSupp && x <= minHeight) {
                return (minHeight - minSupp) == 0 ? 1 : (x - minSupp) / (minHeight - minSupp);
            } else if (x >= maxHeight && x <= maxSupp) {
                return (maxSupp - maxHeight) == 0 ? 1 : (maxSupp - x) / (maxSupp - maxHeight);
            } else {
                return 1.0;
            }
        };
    }

    public double getValue(double x) {
        return trapezoidal.apply(x);
    }
}
