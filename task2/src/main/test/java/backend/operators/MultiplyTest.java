package backend.operators;

import backend.functions.RectangularFunction;
import backend.functions.TrapezoidalFunction;
import backend.functions.factory.FunctionFactory;
import backend.operators.facade.SetOperationFacade;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;
import backend.sets.factory.SetFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyTest {

    @Test
    void multiplyFuzzySetsTest() {
        TrapezoidalFunction trapezoidalFunction1 = FunctionFactory.createTrapezoidalFunction(25, 45, 55, 75, 0, 100);
        FuzzySet fuzzySet1 = SetFactory.createFuzzySet(trapezoidalFunction1);
        TrapezoidalFunction trapezoidalFunction2 = FunctionFactory.createTrapezoidalFunction(30, 50, 55, 75, 0, 100);
        FuzzySet fuzzySet2 = SetFactory.createFuzzySet(trapezoidalFunction2);

        FuzzySet fuzzySetMultiply = (FuzzySet) SetOperationFacade.multiply(fuzzySet1, fuzzySet2);
        assertEquals(0, fuzzySetMultiply.getFunction().getValue(20), 0.01);
        assertEquals(0, fuzzySetMultiply.getFunction().getValue(25), 0.01);
        assertEquals(0, fuzzySetMultiply.getFunction().getValue(30), 0.01);
        assertEquals(0.5, fuzzySetMultiply.getFunction().getValue(40), 0.01);
        assertEquals(1, fuzzySetMultiply.getFunction().getValue(50), 0.01);
        assertEquals(1, fuzzySetMultiply.getFunction().getValue(55), 0.01);
        assertEquals(0.5, fuzzySetMultiply.getFunction().getValue(65), 0.01);
        assertEquals(0, fuzzySetMultiply.getFunction().getValue(75), 0.01);
    }

    @Test
    void addCrispSetsTest() {
        TrapezoidalFunction trapezoidalFunction1 = FunctionFactory.createTrapezoidalFunction(25, 45, 55, 75, 0, 100);
        FuzzySet fuzzySet1 = SetFactory.createFuzzySet(trapezoidalFunction1);
        TrapezoidalFunction trapezoidalFunction2 = FunctionFactory.createTrapezoidalFunction(30, 50, 55, 75, 0, 100);
        FuzzySet fuzzySet2 = SetFactory.createFuzzySet(trapezoidalFunction2);

        FuzzySet fuzzySetSum = (FuzzySet) SetOperationFacade.sum(fuzzySet1, fuzzySet2);
        assertEquals(0, fuzzySetSum.getFunction().getValue(20), 0.01);
        assertEquals(0.25, fuzzySetSum.getFunction().getValue(30), 0.01);
        assertEquals(0.5, fuzzySetSum.getFunction().getValue(35), 0.01);
        assertEquals(1, fuzzySetSum.getFunction().getValue(45), 0.01);
        assertEquals(1, fuzzySetSum.getFunction().getValue(55), 0.01);
        assertEquals(0, fuzzySetSum.getFunction().getValue(75), 0.01);
    }
}