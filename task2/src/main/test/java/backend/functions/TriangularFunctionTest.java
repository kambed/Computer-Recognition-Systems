package backend.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangularFunctionTest {

    @Test
    void getValueOfTheFunction() {
        TriangularFunction triangularFunction = new TriangularFunction(40,  50, 70);
        assertEquals(1, triangularFunction.getValue(50), 0.01);
        assertEquals(0.5, triangularFunction.getValue(45), 0.01);
        assertEquals(0.5, triangularFunction.getValue(60), 0.01);
        assertEquals(0, triangularFunction.getValue(40), 0.01);
        assertEquals(0, triangularFunction.getValue(70), 0.01);
    }

    @Test
    void getValueOfTheFunctionEndingOnTheLeft() {
        TriangularFunction triangularFunction = new TriangularFunction(0,  0, 5);
        assertEquals(1, triangularFunction.getValue(0), 0.01);
        assertEquals(0.5, triangularFunction.getValue(2.5), 0.01);
        assertEquals(0, triangularFunction.getValue(5), 0.01);
    }
}