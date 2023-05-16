package backend.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaussianFunctionTest {

    @Test
    void getValueOfTheFunction() {
        GaussianFunction gaussianFunction = new GaussianFunction(210, 70);
        assertEquals(0.03, gaussianFunction.getValue(282), 0.01);
        assertEquals(0.32, gaussianFunction.getValue(252), 0.01);
        assertEquals(0.69, gaussianFunction.getValue(234), 0.01);
        assertEquals(1, gaussianFunction.getValue(210), 0.01);
        assertEquals(0.69, gaussianFunction.getValue(186), 0.01);
        assertEquals(0.32, gaussianFunction.getValue(168), 0.01);
        assertEquals(0.03, gaussianFunction.getValue(138), 0.01);
    }
}