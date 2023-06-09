/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bcr.fractionfun;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The main application class.
 */
public class App {
    /**
     * The main entrypoint for the console application.
     * 
     * @param args provided, but unused
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("? ");
                String line = null;
                try {
                    line = scanner.nextLine();
                }
                catch (NoSuchElementException e) {
                    break;
                }
                System.out.println("= " + Fraction.computeExpression(line));    
            }
        }
    }
}
