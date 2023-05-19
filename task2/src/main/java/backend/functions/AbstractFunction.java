package backend.functions;

import backend.domain.Domain;

import java.util.function.DoubleFunction;

public class AbstractFunction {
    protected final DoubleFunction<Double> function;
    protected final Domain domain;

    public AbstractFunction(Domain domain, DoubleFunction<Double> function) {
        this.domain = domain;
        this.function = function;
    }

    public double getValue(double x) {
        if (!domain.isMember(x)) {
            throw new IllegalArgumentException("Value " + x + " is not in domain " + domain);
        }
        return function.apply(x);
    }
}
