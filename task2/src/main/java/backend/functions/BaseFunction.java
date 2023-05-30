package backend.functions;

import backend.domain.Domain;
import lombok.Getter;
import lombok.Setter;

import java.util.function.DoubleFunction;

@Getter
@Setter
public class BaseFunction {
    protected final DoubleFunction<Double> function;
    protected Domain domain;

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

    @Override
    public String toString() {
        return "Function: " + function + "\nDomain: " + domain.toString();
    }
}
