package bcr.fractionfun;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FractionTest {
    @Test void fractionExists() {
        Fraction fraction = Fraction.parseFraction("1/2");
        assertNotNull(fraction);
    }
}
