package com.chronoviking.app;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Equation solver for PLEXSYS
 *
 */
public class Mathematics {
    private String equation = null;

    public static void main( String[] args ) {
        Mathematics problem = new Mathematics();

        if (args.length != 1) {
            System.out.println("You must include a single argument of a math expression, enclosed in quotes.");
            System.out.println("Example: equation \"(1 + 2) * 3\"");
            System.exit(0);
        }

        problem.setEquation(args[0]);

        if (problem.getEquation() == null) {
            System.out.println("The given expression is not an equation this app can currently solve.");
            System.out.println("Argument: " + args[0]);
            System.exit(0);
        }

        System.out.println(problem.getEquation() + " = " + problem.parse());
    }

    protected void setEquation(String equation) {
        this.equation = sanitize(equation);
    }
    protected String getEquation() {
        return this.equation;
    }

    private String sanitize(String equation) {
        // For right now, this just makes sure there are no unexpected characters
        // Future updates would allow much more robust validation and sanitation
        String cleanEquation = equation;

        if (!equation.matches("^[0-9()\\+\\* ]+$")) {
            return null;
        }

        return cleanEquation;
    }

    protected int parse() {
        String equation = getEquation();

        if (equation == null) {
            return -1;
        }

        // Define parenthesis
        final char OPEN_CHAR = '(';
        final char CLOSE_CHAR = ')';

        // Fine the first instance of the open and close parenthesis
        int open_pos = equation.indexOf(OPEN_CHAR);
        int close_pos = equation.indexOf(CLOSE_CHAR);
        int subtotal;

        // While there are parenthesis in the equation...
        while (open_pos >= 0) {
            // Loop from the first open to the first close; making sure we are looking at the inner most group
            for (int i = ++open_pos; i < close_pos; i++) {
                if (equation.charAt(i) == OPEN_CHAR) {
                    open_pos = i;
                }
            }

            // Solve inside the parenthesis
            subtotal = solve(equation.substring(open_pos, close_pos));

            // Remove the parenthesis and the inner equation and substitute in the subtotal
            equation = equation.replace(OPEN_CHAR + equation.substring(open_pos, close_pos) + CLOSE_CHAR, Integer.toString(subtotal));

            // Look for new parenthesis
            open_pos = equation.indexOf(OPEN_CHAR);
            close_pos = equation.indexOf(CLOSE_CHAR);
        }

        // By now we have removed all the parenthesis, so solve the final simplified equation
        return solve(equation);
    }
    private int solve(String equation) {
        // Set up variables
        int total = 0;
        String[] factors = equation.split(" ");
        boolean multiply;

        // Multiplication first; can do the same for division
        do {
            // Assume there are no multiplication parts left
            multiply = false;
            for (int i = 0; i < factors.length - 1; i++) {
                // Look through the array of elements for the multiplication symbol
                if (factors[i].equals("*")) {
                    // If found solve and replace the element before the symbol
                    // Converting the string to ints and back again
                    factors[i - 1] = Integer.toString(Integer.parseInt(factors[i - 1]) * Integer.parseInt(factors[i + 1]));
                    // Remove the symbol
                    factors = ArrayUtils.remove(factors, i);
                    // Remove the element past the symbol; but because we deleted one, don't remove i + 1 as it's now the new i
                    factors = ArrayUtils.remove(factors, i);
                    // Signify we found at least one more multiplication to evaluate
                    multiply = true;
                    // Start over; can't continue as we forcibly changed the length of the array
                    break;
                }
            }
        } while (multiply);

        // Works the same as multiplication, except we can assume there is only addition left so we don't need the boolean
        do {
            for (int i = 0; i < factors.length - 1; i++) {
                if (factors[i].equals("+")) {
                    factors[i - 1] = Integer.toString(Integer.parseInt(factors[i - 1]) + Integer.parseInt(factors[i + 1]));
                    factors = ArrayUtils.remove(factors, i);
                    factors = ArrayUtils.remove(factors, i);
                    break;
                }
            }
            // Instead, test that there is more than a single element to the array left
        } while (factors.length > 1);

        // Return the final value
        return Integer.parseInt(factors[0]);
    }
}
