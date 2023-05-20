package backend.functions;

import backend.functions.factory.FunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaussianFunctionTest {

    @Test
    void getValueOfTheFunction() {
        GaussianFunction gaussianFunction = FunctionFactory.createGaussianFunction(210, 70, 0, 300);
        assertEquals(0.03, gaussianFunction.getValue(282), 0.01);
        assertEquals(0.32, gaussianFunction.getValue(252), 0.01);
        assertEquals(0.69, gaussianFunction.getValue(234), 0.01);
        assertEquals(1, gaussianFunction.getValue(210), 0.01);
        assertEquals(0.69, gaussianFunction.getValue(186), 0.01);
        assertEquals(0.32, gaussianFunction.getValue(168), 0.01);
        assertEquals(0.03, gaussianFunction.getValue(138), 0.01);
    }

    @Test
    void getValueOfTheFunctionEndingOnTheLeftSide() {
        GaussianFunction gaussianFunction = FunctionFactory.createGaussianFunction(0, 20, 0, 100);
        assertEquals(1, gaussianFunction.getValue(0), 0.01);
        assertEquals(0.82, gaussianFunction.getValue(5), 0.01);
        assertEquals(0.04, gaussianFunction.getValue(20), 0.01);
    }
}