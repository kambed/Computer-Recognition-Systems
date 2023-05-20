package backend.functions;

import backend.domain.Domain;
import lombok.Getter;

import java.util.function.DoubleFunction;

@Getter
public class BaseFunction {
    protected final DoubleFunction<Double> function;
    protected final Domain domain;

    public BaseFunction(Domain domain, DoubleFunction<Double> function) {
        this.domain = domain;
        this.function = function;
    }

    public double getValue(double x) {
        if (!domain.isMember(x)) {
            throw new IllegalArgumentException("Value " + x + " is not in domain " + domain);
        }
        return function.apply(x);
    }

    public double getMin() {
        return domain.getMin();
    }

    public double getMax() {
        return domain.getMax();
    }
}
