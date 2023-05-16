package backend.functions;

import java.util.function.Function;

public class GaussianFunction {

    private final Function<Double, Double> gaussian;

    public GaussianFunction(double average, double standardDeviation) {
        this.gaussian = x -> Math.exp(-(x - average) * (x - average) /
                        (2 * ((standardDeviation / Math.sqrt(2 * Math.PI)) * (standardDeviation / Math.sqrt(2 * Math.PI)))));
    }

    public double getValue(double x) {
        return gaussian.apply(x);
    }
}
