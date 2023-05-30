package backend.sets;

import backend.Rounder;
import backend.domain.DiscreteDomain;
import backend.functions.BaseFunction;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;

import java.util.stream.DoubleStream;

@Getter
@Setter
public class FuzzySet extends CrispSet {

    private final RombergIntegrator ri = new RombergIntegrator();

    public FuzzySet(BaseFunction function) {
        super(function);
    }

    public CrispSet getSupport() {
        return getAlfaCut(0.0);
    }

    public CrispSet getAlfaCut(double alfa) {
        return new CrispSet(new BaseFunction(getFunction().getDomain(),
                x -> getFunction().getValue(x) > alfa ? 1.0 : 0.0));
    }

    public double getHeight() {
        double min = getFunction().getMin();
        double max = getFunction().getMax();
        double step = Rounder.getStep(min, max);
        return Rounder.round(DoubleStream.iterate(min, d -> d <= max, d -> d + step)
                .map(d -> getFunction().getValue(d))
                .max()
                .orElse(Double.NEGATIVE_INFINITY));
    }

    public boolean isNormal() {
        return getHeight() == 1.0;
    }

    public boolean isEmpty() {
        return getHeight() == 0.0;
    }

    public boolean isConvex() {
        double min = getFunction().getMin();
        double max = getFunction().getMax();
        double step = Rounder.getStep(min, max);
        for (double x = min; x <= max; x += step) {
            for (double y = x; y <= max; y += step) {
                for (double z = y; z <= max; z += step) {
                    if (getFunction().getValue(y) < Math.min(getFunction().getValue(x), getFunction().getValue(z)))
                        return false;
                }
            }
        }
        return true;
    }

    public double getDegreeOfFuzziness() {
        CrispSet support = getSupport();
        double min = getFunction().getMin();
        double max = getFunction().getMax();
        if (this.getFunction().getDomain() instanceof DiscreteDomain domain) {
            double sum = domain.getValues().stream().mapToDouble(
                    d -> support.getFunction().getValue(d)
            ).sum();
            return sum / domain.getValues().size();
        }
        double sum = ri.integrate(Integer.MAX_VALUE, support.getFunction()::getValue, min, max);
        return sum / (support.getFunction().getMax() - support.getFunction().getMin());
    }

    public double getCardinality() {
        double min = getFunction().getMin();
        double max = getFunction().getMax();
        if (this.getFunction().getDomain() instanceof DiscreteDomain domain) {
            double sum = domain.getValues().stream().mapToDouble(
                    d -> getFunction().getValue(d)
            ).sum();
            return sum / domain.getValues().size();
        }
        double sum = ri.integrate(Integer.MAX_VALUE, getFunction()::getValue, min, max);
        return sum / (getFunction().getMax() - getFunction().getMin());
    }
}
