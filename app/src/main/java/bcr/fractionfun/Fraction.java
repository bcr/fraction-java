package bcr.fractionfun;

/**
 * Represents a fraction.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    /**
     * Creates a new Fraction with the given numerator and denominator.
     * 
     * @param numerator the numerator
     * @param denominator the denominator
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Gets the numerator.
     * 
     * @return the numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets the denominator.
     * 
     * @return the denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Creates a new Fraction object from a string.
     *
     * @param fraction the fraction to parse
     * @return the new Fraction
     */
    public static Fraction parseFraction(String fraction) {
        int numeratorSignMultiplier = (fraction.charAt(0) == '-') ? -1 : 1;
        if (numeratorSignMultiplier == -1) {
            fraction = fraction.substring(1);
        }

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
        return new Fraction(numeratorSignMultiplier * (numerator + (whole * denominator)), denominator);
    }

    @Override
    public String toString() {
        int normalizedNumerator = numerator;
        int normalizedDenominator = denominator;

        if (normalizedDenominator < 0) {
            normalizedDenominator *= -1;
            normalizedNumerator *= -1;
        }

        boolean isNegative = (normalizedNumerator < 0);
        int whole = 0;
        int outputNumerator;
        int outputDenominator = normalizedDenominator;
        String finalString = "";

        if (isNegative) {
            normalizedNumerator = Math.abs(normalizedNumerator);
        }

        if (normalizedNumerator >= normalizedDenominator) {
            whole = normalizedNumerator / normalizedDenominator;
            outputNumerator = normalizedNumerator - (whole * normalizedDenominator);
        }
        else {
            outputNumerator = normalizedNumerator;
        }

        if (isNegative) {
            finalString += "-";
        }

        if (outputNumerator == 0) {
            return finalString + whole;
        }
        else if (whole > 0) {
            return finalString + whole + "_" + outputNumerator + "/" + outputDenominator;
        }
        else {
            return finalString + outputNumerator + "/" + outputDenominator;
        }
    }

    // Bard wrote reduce and gcd
    // Given a numerator and a denominator, can you give me Java code to compute the numerator and denominator in lowest terms?

    /**
     * Creates a new Fraction from this Fraction with the numerator and
     * denominator in lowest terms.
     * 
     * @return a new Fraction with the numerator and denominator in lowest terms
     */
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

    /**
     * Computes the result for an expression, returning a Fraction.
     *
     * @param expression a math expression
     * @return the result from computing the expression
     */
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
            case "/":
                result = new Fraction(left.numerator * right.denominator, left.denominator * right.numerator);
                break;
            case "+":
                result = new Fraction((left.numerator * right.denominator) + (right.numerator * left.denominator), left.denominator * right.denominator);
                break;
            case "-":
                result = new Fraction((left.numerator * right.denominator) - (right.numerator * left.denominator), left.denominator * right.denominator);
                break;
            default:
                // Freak out?
        }
        return (result != null) ? result.reduce() : null;
    }
}
