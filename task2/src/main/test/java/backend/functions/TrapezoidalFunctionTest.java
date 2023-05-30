package backend.functions;

import backend.functions.factory.FunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapezoidalFunctionTest {

    @Test
    void getValueOfTheFunction() {
        TrapezoidalFunction trapezoidalFunction = FunctionFactory.createTrapezoidalFunction(25, 45, 55, 75, 0, 100);
        assertEquals(1, trapezoidalFunction.getValue(45), 0.01);
        assertEquals(1, trapezoidalFunction.getValue(50), 0.01);
        assertEquals(1, trapezoidalFunction.getValue(55), 0.01);

        assertEquals(0.25, trapezoidalFunction.getValue(30), 0.01);
        assertEquals(0.3, trapezoidalFunction.getValue(31), 0.01);
        assertEquals(0.5, trapezoidalFunction.getValue(35), 0.01);
        assertEquals(0.95, trapezoidalFunction.getValue(44), 0.01);

        assertEquals(0, trapezoidalFunction.getValue(25), 0.01);
        assertEquals(0, trapezoidalFunction.getValue(20), 0.01);
        assertEquals(0, trapezoidalFunction.getValue(75), 0.01);
        assertEquals(0, trapezoidalFunction.getValue(80), 0.01);
    }

    @Test
    void getValueOfTheFunctionNotEqualSidesEndingOnRight() {
        TrapezoidalFunction trapezoidalFunction = FunctionFactory.createTrapezoidalFunction(39, 49, 51, 51, 0, 51);
        assertEquals(0, trapezoidalFunction.getValue(39), 0.01);
        assertEquals(0.1, trapezoidalFunction.getValue(40), 0.01);
        assertEquals(0.9, trapezoidalFunction.getValue(48), 0.01);
        assertEquals(1, trapezoidalFunction.getValue(49), 0.01);
        assertEquals(1, trapezoidalFunction.getValue(51), 0.01);
        assertThrows(IllegalArgumentException.class, () -> trapezoidalFunction.getValue(52));
    }

    @Test
    void getValueOfTheFunctionNotEqualSidesEndingOnLeft() {
        TrapezoidalFunction trapezoidalFunction = FunctionFactory.createTrapezoidalFunction(0, 0, 20, 40, 0, 50);
        assertThrows(IllegalArgumentException.class, () -> trapezoidalFunction.getValue(-1));
        assertEquals(1, trapezoidalFunction.getValue(0), 0.01);
        assertEquals(1, trapezoidalFunction.getValue(20), 0.01);
        assertEquals(0.95, trapezoidalFunction.getValue(21), 0.01);
        assertEquals(0.05, trapezoidalFunction.getValue(39), 0.01);
        assertEquals(0, trapezoidalFunction.getValue(40), 0.01);
    }
}