package backend.domain;

import java.util.Collections;
import java.util.List;

public class DiscreteDomain implements Domain {
    private final List<Double> values;

    public DiscreteDomain(List<Double> values) {
        this.values = values;
    }

    @Override
    public boolean isMember(double value) {
        return values.contains(value);
    }

    @Override
    public double getMin() {
        return Collections.min(values);
    }

    @Override
    public double getMax() {
        return Collections.max(values);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        values.forEach(v -> sb.append(v).append(","));
        sb.append('}');
        return sb.toString();
    }
}
