package backend.functions;

import backend.domain.ContinuousDomain;
import backend.domain.Domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GaussianFunction extends BaseFunction {
    private double average;
    private double standardDeviation;

    public GaussianFunction(double average, double standardDeviation) {
        this(new ContinuousDomain(-Double.MAX_VALUE, Double.MAX_VALUE), average, standardDeviation);
    }

    public GaussianFunction(Domain domain, double average, double standardDeviation) {
        super(domain, x -> Math.exp(
                -(x - average) * (x - average) /
                        (2 * ((standardDeviation / Math.sqrt(2 * Math.PI)) * (standardDeviation / Math.sqrt(2 * Math.PI))))
        ));
        this.average = average;
        this.standardDeviation = standardDeviation;
    }
}
