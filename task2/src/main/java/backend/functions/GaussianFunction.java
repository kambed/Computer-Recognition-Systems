package backend.functions;

public class GaussianFunction extends AbstractFunction {

    public GaussianFunction(double average, double standardDeviation) {
        super(x -> Math.exp(
                -(x - average) * (x - average) /
                        (2 * ((standardDeviation / Math.sqrt(2 * Math.PI)) * (standardDeviation / Math.sqrt(2 * Math.PI))))
        ));
    }
}
