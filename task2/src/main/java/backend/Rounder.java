package backend;

public class Rounder {
    public static final double DECIMAL_PLACES_DIVISION = 100.0;
    public static final double NUMBER_OF_STEPS = 1000.0;

    private Rounder() {
    }

    public static Double round(Double value) {
        if (value == null) {
            return null;
        }
        return Math.round(value * DECIMAL_PLACES_DIVISION) / DECIMAL_PLACES_DIVISION;
    }

    public static String roundSummary(Double value) {
        Double rounded = round(value);
        if (rounded == null) {
            return null;
        }
        String roundedString = rounded.toString();
        if ((rounded == 0.0 && value != 0.0) || (rounded == 1.0 && value != 1.0)) {
            return "~" + roundedString;
        }
        return roundedString;
    }
    public static double floor(double value) {
        return Math.floor(value * DECIMAL_PLACES_DIVISION) / DECIMAL_PLACES_DIVISION;
    }

    public static double getStep(double min, double max) {
        return (max - min) / Rounder.NUMBER_OF_STEPS;
    }
}
