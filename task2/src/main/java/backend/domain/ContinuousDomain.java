package backend.domain;

import lombok.Setter;

@Setter
public class ContinuousDomain implements Domain {
    private final double from;
    private final double to;

    public ContinuousDomain(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isMember(double value) {
        return (value >= from && value <= to);
    }

    @Override
    public double getMin() {
        return from;
    }

    @Override
    public double getMax() {
        return to;
    }

    @Override
    public String toString() {
        return "[%s,%s]".formatted(from, to);
    }
}
