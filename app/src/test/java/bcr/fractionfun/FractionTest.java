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
}
