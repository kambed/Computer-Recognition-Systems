package backend.operators;

import backend.functions.RectangularFunction;
import backend.functions.TrapezoidalFunction;
import backend.functions.factory.FunctionFactory;
import backend.operators.facade.SetOperationFacade;
import backend.sets.CrispSet;
import backend.sets.FuzzySet;
import backend.sets.factory.SetFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplementTest {

    @Test
    void complementOfFuzzySetTest() {
        TrapezoidalFunction trapezoidalFunction = FunctionFactory.createTrapezoidalFunction(25, 45, 55, 75, 0, 100);
        FuzzySet fuzzySet = SetFactory.createFuzzySet(trapezoidalFunction);

        FuzzySet fuzzySetComplement = (FuzzySet) SetOperationFacade.complement(fuzzySet);
        assertEquals(1, fuzzySetComplement.getFunction().getValue(20), 0.01);
        assertEquals(1, fuzzySetComplement.getFunction().getValue(25), 0.01);
        assertEquals(0.5, fuzzySetComplement.getFunction().getValue(35), 0.01);
        assertEquals(0, fuzzySetComplement.getFunction().getValue(45), 0.01);
        assertEquals(0, fuzzySetComplement.getFunction().getValue(50), 0.01);
        assertEquals(0, fuzzySetComplement.getFunction().getValue(55), 0.01);
        assertEquals(0.5, fuzzySetComplement.getFunction().getValue(65), 0.01);
        assertEquals(1, fuzzySetComplement.getFunction().getValue(75), 0.01);
        assertEquals(1, fuzzySetComplement.getFunction().getValue(80), 0.01);
    }

    @Test
    void complementOfCrispSetTest() {
        RectangularFunction rectangularFunction = FunctionFactory.createRectangularFunction(40, 50, 0, 60);
        CrispSet crispSet = SetFactory.createCrispSet(rectangularFunction);

        CrispSet crispSetComplement = SetOperationFacade.complement(crispSet);
        assertEquals(1, crispSetComplement.getFunction().getValue(20), 0.01);
        assertEquals(0, crispSetComplement.getFunction().getValue(40), 0.01);
        assertEquals(0, crispSetComplement.getFunction().getValue(50), 0.01);
        assertEquals(1, crispSetComplement.getFunction().getValue(55), 0.01);
    }
}