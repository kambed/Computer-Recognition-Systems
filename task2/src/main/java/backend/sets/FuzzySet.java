package backend.sets;

import backend.Rounder;
import backend.functions.DefaultFunction;
import lombok.Getter;

@Getter
public class FuzzySet extends CrispSet {
    public FuzzySet(DefaultFunction function) {
        super(function);
    }

    public CrispSet getSupport() {
        return getAlfaCut(0.0);
    }

    public CrispSet getAlfaCut(double alfa) {
        return new CrispSet(new DefaultFunction(getFunction().getDomain(),
                x -> getFunction().getValue(x) > alfa ? 1.0 : 0.0));
    }

    public double getHeight() {
        double highest = 0.0;
        double min = getFunction().getMin();
        double max = getFunction().getMax();
        double step = Rounder.round((max - min) / Rounder.NUMBER_OF_STEPS);
        for (double d = min; d <= max; d += step) {
            if (getFunction().getValue(d) > highest)
                highest = getFunction().getValue(d);
        }
        return Rounder.round(highest);
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
        double step = Rounder.round((max - min) / Rounder.NUMBER_OF_STEPS);
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
}
