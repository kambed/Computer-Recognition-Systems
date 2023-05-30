package frontend.model;

import java.util.Objects;

public record ComparableMeasureString(String string) implements Comparable<ComparableMeasureString> {

    @Override
    public int compareTo(ComparableMeasureString cms) {
        if (this.string.equals("~0.0") && cms.string.equals("0.0") ||
                cms.string.equals("~1.0") && this.string.equals("1.0")) {
            return 1;
        }
        return this.string.replace("~", "").compareTo(
                cms.string.replace("~", "")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparableMeasureString that = (ComparableMeasureString) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public String toString() {
        return string;
    }
}
