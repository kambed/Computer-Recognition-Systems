package backend.lingustic.quantifier;

import backend.domain.ContinuousDomain;
import backend.functions.BaseFunction;

public class RelativeQuantifier extends AbstractQuantifier {
    public RelativeQuantifier(String label, BaseFunction function) {
        super(label, function);
        function.setDomain(new ContinuousDomain(0, 1));
        if (!this.isConvex() || !this.isNormal()) {
            throw new IllegalArgumentException("Quantifier is not convex and normal");
        }
    }
}
