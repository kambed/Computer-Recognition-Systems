package backend.functions;

import java.util.function.DoubleFunction;

public class AbstractFunction {

    protected final DoubleFunction<Double> function;

    public AbstractFunction(DoubleFunction<Double> function) {
        this.function = function;
    }

    public double getValue(double x) {
        return function.apply(x);
    }
}
