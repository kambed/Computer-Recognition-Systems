package backend.sets;

import backend.domain.ContinuousDomain;
import backend.functions.DefaultFunction;
import backend.functions.TrapezoidalFunction;
import backend.operators.AbstractOperator;
import backend.operators.Complement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuzzySetTest {

    @Test
    void getSupport() {
        FuzzySet set = new FuzzySet(new TrapezoidalFunction(new ContinuousDomain(-1, 5),
                0, 1, 2, 3));
        assertEquals(0, set.getSupport().getFunction().getValue(-0.01));
        assertEquals(0, set.getSupport().getFunction().getValue(0));
        assertEquals(1, set.getSupport().getFunction().getValue(0.01));
        assertEquals(1, set.getSupport().getFunction().getValue(1));
        assertEquals(1, set.getSupport().getFunction().getValue(2));
        assertEquals(1, set.getSupport().getFunction().getValue(2.99));
        assertEquals(0, set.getSupport().getFunction().getValue(3));
        assertEquals(0, set.getSupport().getFunction().getValue(3.01));
    }

    @Test
    void getAlfaCut() {
        FuzzySet set = new FuzzySet(new TrapezoidalFunction(new ContinuousDomain(-1, 5),
                0, 1, 2, 3));
        assertEquals(0, set.getAlfaCut(0.5).getFunction().getValue(0));
        assertEquals(0, set.getAlfaCut(0.5).getFunction().getValue(0.5));
        assertEquals(1, set.getAlfaCut(0.5).getFunction().getValue(0.51));
        assertEquals(1, set.getAlfaCut(0.5).getFunction().getValue(1));
        assertEquals(1, set.getAlfaCut(0.5).getFunction().getValue(2));
        assertEquals(1, set.getAlfaCut(0.5).getFunction().getValue(2.49));
        assertEquals(0, set.getAlfaCut(0.5).getFunction().getValue(2.5));
        assertEquals(0, set.getAlfaCut(0.5).getFunction().getValue(3));
    }

    @Test
    void getHeight() {
        FuzzySet set = new FuzzySet(new TrapezoidalFunction(new ContinuousDomain(-1, 5),
                0, 1, 2, 3));
        assertEquals(1, set.getHeight());
        set = new FuzzySet(new DefaultFunction(new ContinuousDomain(-1, 5),
                x -> 0.5));
        assertEquals(0.5, set.getHeight());
    }

    @Test
    void isNormal() {
        FuzzySet set = new FuzzySet(new TrapezoidalFunction(new ContinuousDomain(-1, 5),
                0, 1, 2, 3));
        assertTrue(set.isNormal());
    }

    @Test
    void isNotNormal() {
        FuzzySet set = new FuzzySet(new DefaultFunction(new ContinuousDomain(-1, 5),
                x -> 0.5));
        assertFalse(set.isNormal());
    }

    @Test
    void isEmpty() {
        FuzzySet set = new FuzzySet(new DefaultFunction(new ContinuousDomain(-1, 5),
                x -> 0.0));
        assertTrue(set.isEmpty());
    }

    @Test
    void isNotEmpty() {
        FuzzySet set = new FuzzySet(new TrapezoidalFunction(new ContinuousDomain(-1, 5),
                0, 1, 2, 3));
        assertFalse(set.isEmpty());
    }

    @Test
    void isConvex() {
        FuzzySet set = new FuzzySet(new TrapezoidalFunction(new ContinuousDomain(-1, 5),
                0, 1, 2, 3));
        assertTrue(set.isConvex());
    }

    @Test
    void isNotConvex() {
        FuzzySet set = new FuzzySet(new TrapezoidalFunction(new ContinuousDomain(-1, 5),
                0, 1, 2, 3));
        AbstractOperator operator = new Complement();
        assertFalse(((FuzzySet) operator.execute(set)).isConvex());
    }

}