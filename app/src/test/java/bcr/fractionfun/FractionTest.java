package bcr.fractionfun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FractionTest {
    @Test void fractionExists() {
        Fraction fraction = Fraction.parseFraction("1/2");
        assertNotNull(fraction);
    }

    @Test void fractionParsesFraction() {
        Fraction fraction = Fraction.parseFraction("9/8");
        assertNotNull(fraction);
        assertEquals(9, fraction.getNumerator());
        assertEquals(8, fraction.getDenominator());
    }

    @Test void fractionParsesMixedNumber() {
        Fraction fraction = Fraction.parseFraction("3_3/4");
        assertNotNull(fraction);
        assertEquals(15, fraction.getNumerator());
        assertEquals(4, fraction.getDenominator());
    }

    @Test void fractionToStringFraction() {
        Fraction fraction = Fraction.parseFraction("1/2");
        assertEquals("1/2", fraction.toString());
    }

    @Test void fractionToStringMixed() {
        Fraction fraction = Fraction.parseFraction("1_7/8");
        assertEquals("1_7/8", fraction.toString());
    }

    // From the requirements example runs:
    // ? 1/2 * 3_3/4
    // = 1_7/8

    @Test void fractionComputeMultiply() {
        Fraction fraction = Fraction.computeExpression("1/2 * 3_3/4");
        assertEquals("1_7/8", fraction.toString());
    }

    // From the requirements example runs:
    // ? 2_3/8 + 9/8
    // = 3_1/2

    @Test void fractionComputeAdd() {
        Fraction fraction = Fraction.computeExpression("2_3/8 + 9/8");
        assertEquals("3_1/2", fraction.toString());
    }

    @Test void fractionComputeAddDifferentDenominators() {
        Fraction fraction = Fraction.computeExpression("3/8 + 1/4");
        assertEquals("5/8", fraction.toString());
    }

    // Operands and operators shall be separated by one or more spaces.

    @Test void fractionComputeHandleWhitespace() {
        Fraction fraction = Fraction.computeExpression("1/2   *   3_3/4");
        assertEquals("1_7/8", fraction.toString());
    }
}
