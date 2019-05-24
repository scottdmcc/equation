package com.chronoviking.app;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Equation solver for PLEXSYS
 *
 */
public class Mathematics {
    private String equation = null;
    private int answer;
    private String errorMsg = null;

    public static void main( String[] args ) {
        Mathematics problem = new Mathematics();

        // If there are zero, or more than one argument, show the instructions and exit
        if (args.length != 1) {
            System.out.println("You must include a single argument of a math expression, enclosed in quotes.");
            System.out.println("Example: equation \"(1 + 2) * 3\"");
            System.exit(0);
        }

        // Store the argument for usage
        problem.setEquation(args[0]);

        // Check to see if the sanitation produced an error
        if (problem.getErrorMsg() != null) {
            System.out.println("The given expression is not an equation this app can currently reduce.");
            System.out.println("Expression: " + args[0]);
            System.out.println("Error: " + problem.getErrorMsg());
            System.exit(0);
        }

        // Solve the problem
        problem.solve();

        // Print results to the console
        System.out.println(problem.getEquation() + " = " + problem.getAnswer());
    }

    protected void setEquation(String equation) {
        this.equation = sanitize(equation);
    }
    protected String getEquation() {
        return this.equation;
    }
    private String sanitize(String equation) {
        String cleanEquation = equation;

        // Make sure there are no unexpected characters
        if (!equation.matches("^[0-9()\\+\\* ]+$")) {
            setErrorMsg("Foreign characters in the equation");
            return null;
        }

        // Remove all spaces, and then only put them back on either side of operands
        cleanEquation = cleanEquation.replace(" ", "");
        cleanEquation = cleanEquation.replaceAll("([\\+\\*])", " $1 ");

        // Future updates can also test for mismatched parenthesis and other errors

        return cleanEquation;
    }

    private void setAnswer(int answer) {
        this.answer = answer;
    }
    protected int getAnswer() {
        return this.answer;
    }

    private void setErrorMsg(String msg) {
        this.errorMsg = msg;
    }
    protected String getErrorMsg() {
        return errorMsg;
    }

    private void solve() {
        parse(getEquation());
    }
    protected void parse(String equation) {
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
            for (int i = open_pos; i < close_pos; i++) {
                if (equation.charAt(i) == OPEN_CHAR) {
                    open_pos = i;
                }
            }

            // Solve inside the parenthesis
            subtotal = reduce(equation.substring(open_pos + 1, close_pos));

            // Remove the parenthesis and the inner equation and substitute in the subtotal
            equation = equation.replace(OPEN_CHAR + equation.substring(open_pos + 1, close_pos) + CLOSE_CHAR, Integer.toString(subtotal));

            // Look for new parenthesis
            open_pos = equation.indexOf(OPEN_CHAR);
            close_pos = equation.indexOf(CLOSE_CHAR);
        }

        // By now we have removed all the parenthesis, so reduce the final simplified equation
        setAnswer(reduce(equation));
    }
    private int reduce(String equation) {
        // Set up variables
        int total = 0;
        String[] factors = equation.split(" ");

        // Go through the equation, searching for all multiplication operations
        factors = evaluateOperation("*", factors);

        // Go through the equation, searching for all addition operations
        factors = evaluateOperation("+", factors);

        // Return the final value
        return Integer.parseInt(factors[0]);
    }
    private String[] evaluateOperation(String operandChar, String[] factors) {
        boolean operandExists;
        int primary;
        int secondary;

        do {
            // Assume there are no operations to perform
            operandExists = false;
            for (int i = 0; i < factors.length - 1; i++) {
                // Look through the array of elements for the operand symbol
                if (factors[i].equals(operandChar)) {
                    // If found, get the int value of the index before and after the symbol
                    primary = Integer.parseInt(factors[i - 1]);
                    secondary = Integer.parseInt(factors[i + 1]);
                    // Based on the symbol, do the math and convert back to a string in the index pos before the symbol
                    switch (operandChar) {
                        case "*":
                            factors[i - 1] = Integer.toString(primary * secondary);
                            break;
                        case "+":
                            factors[i - 1] = Integer.toString(primary + secondary);
                            break;
                    }

                    // Remove the symbol
                    factors = ArrayUtils.remove(factors, i);
                    // Remove the element past the symbol; but because we deleted one, don't remove i + 1 as it's now the new i
                    factors = ArrayUtils.remove(factors, i);
                    // Signify we found at least one more operand to evaluate
                    operandExists = true;
                    // Start over; can't continue as we forcibly changed the length of the array
                    break;
                }
            }
        } while (operandExists);
        return factors;
    }
}
