package bcr.fractionfun;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public static Fraction parseFraction(String fraction) {
        String[] parts = fraction.split("/");
        return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
}
