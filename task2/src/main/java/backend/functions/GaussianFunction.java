package backend.functions;

import backend.domain.ContinuousDomain;
import backend.domain.Domain;

public class GaussianFunction extends AbstractFunction {

    public GaussianFunction(double average, double standardDeviation) {
        this(new ContinuousDomain(-Double.MAX_VALUE, Double.MAX_VALUE), average, standardDeviation);
    }

    public GaussianFunction(Domain domain, double average, double standardDeviation) {
        super(domain, x -> Math.exp(
                -(x - average) * (x - average) /
                        (2 * ((standardDeviation / Math.sqrt(2 * Math.PI)) * (standardDeviation / Math.sqrt(2 * Math.PI))))
        ));
    }
}
