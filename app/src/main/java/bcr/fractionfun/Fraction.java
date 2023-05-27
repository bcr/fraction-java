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
        String[] wholeAndFraction = fraction.split("_");
        String wholePart = null;
        String fractionalPart = null;
        if (wholeAndFraction.length == 1) {
            fractionalPart = fraction;
        }
        else {
            wholePart = wholeAndFraction[0];
            fractionalPart = wholeAndFraction[1];
        }

        int whole = 0;
        if (wholePart != null) {
            whole = Integer.parseInt(wholePart);
        }

        String[] parts = fractionalPart.split("/");
        int numerator = Integer.parseInt(parts[0]);
        int denominator = Integer.parseInt(parts[1]);
        return new Fraction(numerator + (whole * denominator), denominator);
    }

    @Override
    public String toString() {
        int whole = 0;
        int outputNumerator;
        int outputDenominator = denominator;

        if (numerator > denominator) {
            whole = numerator / denominator;
            outputNumerator = numerator - (whole * denominator);
        }
        else {
            outputNumerator = numerator;
        }

        if (whole > 0) {
            return "" + whole + "_" + outputNumerator + "/" + outputDenominator;
        }
        else {
            return "" + outputNumerator + "/" + outputDenominator;
        }
    }

    // Bard wrote reduce and gcd
    // Given a numerator and a denominator, can you give me Java code to compute the numerator and denominator in lowest terms?

    public Fraction reduce() {
        int gcd = gcd(numerator, denominator);
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }    

    public static Fraction computeExpression(String expression) {
        String[] parts = expression.split(" ");
        Fraction left = Fraction.parseFraction(parts[0]);
        String operator = parts[1];
        Fraction right = Fraction.parseFraction(parts[2]);

        switch (operator) {
            case "*":
                return new Fraction(left.numerator * right.numerator, left.denominator * right.denominator);
            case "+":
                return new Fraction(left.numerator + right.numerator, right.denominator).reduce();
            default:
                // Freak out?
        }
        return null;
    }
}
