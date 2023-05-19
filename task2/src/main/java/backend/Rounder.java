package backend;

public class Rounder {
    public static final double DECIMAL_PLACES_DIVISION = 100.0;
    public static final double NUMBER_OF_STEPS = 10000.0;

    private Rounder() {
    }

    public static double round(double value) {
        return Math.round(value * DECIMAL_PLACES_DIVISION) / DECIMAL_PLACES_DIVISION;
    }
    public static double floor(double value) {
        return Math.floor(value * DECIMAL_PLACES_DIVISION) / DECIMAL_PLACES_DIVISION;
    }
}
