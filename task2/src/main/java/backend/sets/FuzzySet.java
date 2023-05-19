package backend.sets;

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
                x -> getFunction().getValue(x) >= alfa ? 1.0 : 0.0));
    }
}
