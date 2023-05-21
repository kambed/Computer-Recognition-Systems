package backend.lingustic.predefined;

import backend.functions.factory.FunctionFactory;
import backend.lingustic.factory.LinguisticFactory;
import backend.lingustic.quantifier.AbsoluteQuantifier;
import backend.lingustic.quantifier.RelativeQuantifier;

import java.util.List;

public class PredefinedQuantifiers {

    private PredefinedQuantifiers() {
    }

    public static List<RelativeQuantifier> getPredefinedRelativeQuantifiers() {
        return List.of(
                LinguisticFactory.createRelativeQuantifier("Prawie żaden",
                        FunctionFactory.createTriangularFunction(0, 0, 0.25, 0, 1)),
                LinguisticFactory.createRelativeQuantifier("Niewiele",
                        FunctionFactory.createTrapezoidalFunction(0.1, 0.2, 0.3, 0.4, 0, 1)),
                LinguisticFactory.createRelativeQuantifier("Około połowa",
                        FunctionFactory.createGaussianFunction(0.5, 0.2, 0, 1)),
                LinguisticFactory.createRelativeQuantifier("Dużo",
                        FunctionFactory.createTriangularFunction(0.55, 0.75, 0.95, 0, 1)),
                LinguisticFactory.createRelativeQuantifier("Większość",
                        FunctionFactory.createTrapezoidalFunction(0.8, 0.95, 1, 1, 0, 1))
        );
    }

    public static List<AbsoluteQuantifier> getPredefinedAbsoluteQuantifiers() {
        return List.of(
                LinguisticFactory.createAbsoluteQuantifier("Mniej niż 500",
                        FunctionFactory.createTrapezoidalFunction(0, 0, 500, 1000, 0, 11073)),
                LinguisticFactory.createAbsoluteQuantifier("Mniej niż 2000",
                        FunctionFactory.createTrapezoidalFunction(0, 0, 2000, 4000, 0, 11073)),
                LinguisticFactory.createAbsoluteQuantifier("Około 4000",
                        FunctionFactory.createTriangularFunction(1500, 4000, 6500, 0, 11073)),
                LinguisticFactory.createAbsoluteQuantifier("Powyżej 7000",
                        FunctionFactory.createTrapezoidalFunction(5000, 7500, 11073, 11073, 0, 11073)),
                LinguisticFactory.createAbsoluteQuantifier("Około 10000",
                        FunctionFactory.createGaussianFunction(10000, 1500, 0, 11073))
        );
    }
}
