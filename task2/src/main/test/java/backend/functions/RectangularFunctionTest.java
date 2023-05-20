package backend.functions;

import backend.functions.factory.FunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularFunctionTest {

    @Test
    void getValueOfTheFunction() {
        RectangularFunction rectangularFunction = FunctionFactory.createRectangularFunction(40, 50, 0, 60);
        assertEquals(0, rectangularFunction.getValue(39.99), 0.01);
        assertEquals(1, rectangularFunction.getValue(40), 0.01);
        assertEquals(1, rectangularFunction.getValue(45), 0.01);
        assertEquals(1, rectangularFunction.getValue(50), 0.01);
        assertEquals(0, rectangularFunction.getValue(50.01), 0.01);
    }
}