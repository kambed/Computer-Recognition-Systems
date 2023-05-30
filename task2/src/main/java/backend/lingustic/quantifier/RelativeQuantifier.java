package backend.lingustic.quantifier;

import backend.domain.ContinuousDomain;
import backend.functions.BaseFunction;

public class RelativeQuantifier extends AbstractQuantifier {
    public RelativeQuantifier(String label, BaseFunction function) {
        super(label, function);
        function.setDomain(new ContinuousDomain(0, 1));
    }
}
