package backend.functions;

public class TriangularFunction extends TrapezoidalFunction {
    public TriangularFunction(double minSupp, double max, double maxSupp) {
        super(minSupp, max, max, maxSupp);
    }
}
