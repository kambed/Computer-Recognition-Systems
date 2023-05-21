package backend.functions.factory;

import backend.domain.ContinuousDomain;
import backend.domain.DiscreteDomain;
import backend.functions.*;

import java.util.List;
import java.util.function.DoubleFunction;

public class FunctionFactory {

    private FunctionFactory() {
    }

    public static BaseFunction createFunction(DoubleFunction<Double> function, List<Double> discreteDomain) {
        return new BaseFunction(new DiscreteDomain(discreteDomain), function);
    }

    public static BaseFunction createFunction(DoubleFunction<Double> function, double min, double max) {
        return new BaseFunction(new ContinuousDomain(min, max), function);
    }

    public static GaussianFunction createGaussianFunction(double average, double standardDeviation, List<Double> discreteDomain) {
        return new GaussianFunction(new DiscreteDomain(discreteDomain), average, standardDeviation);
    }

    public static GaussianFunction createGaussianFunction(double average, double standardDeviation, double domainFrom, double domainTo) {
        return new GaussianFunction(new ContinuousDomain(domainFrom, domainTo), average, standardDeviation);
    }

    public static TrapezoidalFunction createTrapezoidalFunction(double minSupp, double minHeight, double maxHeight, double maxSupp, List<Double> discreteDomain) {
        return new TrapezoidalFunction(new DiscreteDomain(discreteDomain), minSupp, minHeight, maxHeight, maxSupp);
    }

    public static TrapezoidalFunction createTrapezoidalFunction(double minSupp, double minHeight, double maxHeight, double maxSupp, double domainFrom, double domainTo) {
        return new TrapezoidalFunction(new ContinuousDomain(domainFrom, domainTo), minSupp, minHeight, maxHeight, maxSupp);
    }

    public static TriangularFunction createTriangularFunction(double minSupp, double maxHeight, double maxSupp, List<Double> discreteDomain) {
        return new TriangularFunction(new DiscreteDomain(discreteDomain), minSupp, maxHeight, maxSupp);
    }

    public static TriangularFunction createTriangularFunction(double minSupp, double maxHeight, double maxSupp, double domainFrom, double domainTo) {
        return new TriangularFunction(new ContinuousDomain(domainFrom, domainTo), minSupp, maxHeight, maxSupp);
    }

    public static RectangularFunction createRectangularFunction(double minSupp, double maxSupp, List<Double> discreteDomain) {
        return new RectangularFunction(new DiscreteDomain(discreteDomain), minSupp, maxSupp);
    }

    public static RectangularFunction createRectangularFunction(double minSupp, double maxSupp, double domainFrom, double domainTo) {
        return new RectangularFunction(new ContinuousDomain(domainFrom, domainTo), minSupp, maxSupp);
    }
}
