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
            if (fraction.contains("/")) {
                fractionalPart = fraction;
            }
            else {
                wholePart = fraction;
            }
        }
        else {
            wholePart = wholeAndFraction[0];
            fractionalPart = wholeAndFraction[1];
        }

        int whole = 0;
        if (wholePart != null) {
            whole = Integer.parseInt(wholePart);
        }

        int numerator = 0;
        int denominator = 1;
        if (fractionalPart != null) {
            String[] parts = fractionalPart.split("/");
            numerator = Integer.parseInt(parts[0]);
            denominator = Integer.parseInt(parts[1]);
        }
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
        String[] parts = expression.split("\s+");
        Fraction left = Fraction.parseFraction(parts[0]);
        String operator = parts[1];
        Fraction right = Fraction.parseFraction(parts[2]);
        Fraction result = null;

        switch (operator) {
            case "*":
                result = new Fraction(left.numerator * right.numerator, left.denominator * right.denominator);
                break;
            case "+":
                result = new Fraction((left.numerator * right.denominator) + (right.numerator * left.denominator), left.denominator * right.denominator);
                break;
            default:
                // Freak out?
        }
        return (result != null) ? result.reduce() : null;
    }
}
