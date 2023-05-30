package backend.lingustic.quantifier;

import backend.domain.ContinuousDomain;
import backend.functions.BaseFunction;
import backend.repository.StatsRepository;

public class AbsoluteQuantifier extends AbstractQuantifier {
    public AbsoluteQuantifier(String label, BaseFunction function) {
        super(label, function);
        function.setDomain(new ContinuousDomain(0, StatsRepository.getStats().size()));
        if (!this.isConvex() || !this.isNormal()) {
            throw new IllegalArgumentException("Quantifier is not convex and normal");
        }
    }
}
