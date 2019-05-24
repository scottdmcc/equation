package com.chronoviking.app;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for math app.
 */
public class MathematicsTest
{
    /**
     * Simple tests
     */
    @Test
    public void testSanitation() {
        Mathematics problem = new Mathematics();
        String equation;

        equation = "1 + 1";
        problem.setEquation(equation);
        Assert.assertNull(equation + " is valid", problem.getErrorMsg());

        equation = "(2 + 3) + 5";
        problem.setEquation(equation);
        Assert.assertNull(equation + " is valid", problem.getErrorMsg());

        equation = "1 + 2 * 3";
        problem.setEquation(equation);
        Assert.assertNull(equation + " is valid", problem.getErrorMsg());

        equation = "(3 + 4) * 6";
        problem.setEquation(equation);
        Assert.assertNull(equation + " is valid", problem.getErrorMsg());

        equation = "apple + (5 * 2)";
        problem.setEquation(equation);
        Assert.assertNotNull(equation + " is not valid", problem.getErrorMsg());
    }

    @Test
    public void testFixingFormatting() {
        Mathematics problem = new Mathematics();
        String before;
        String after;

        before = "1+1"; after = "1 + 1";
        problem.setEquation(before);
        Assert.assertEquals("'" + before + "' becomes '" + after + "'", after, problem.getEquation());

        before = " 1  +  1 "; after = "1 + 1";
        problem.setEquation(before);
        Assert.assertEquals("'" + before + "' becomes '" + after + "'", after, problem.getEquation());

        before = "(2+3) + 5"; after = "(2 + 3) + 5";
        problem.setEquation(before);
        Assert.assertEquals("'" + before + "' becomes '" + after + "'", after, problem.getEquation());

        before = " ( 1 * 4 ) + ( 5 * 2 ) "; after = "(1 * 4) + (5 * 2)";
        problem.setEquation(before);
        Assert.assertEquals("'" + before + "' becomes '" + after + "'", after, problem.getEquation());
    }

    @Test
    public void testSolvingEquations() {
        Mathematics problem = new Mathematics();
        String equation;
        int answer;

        equation = "1 + 1"; answer = 2;
        problem.parse(equation);
        Assert.assertEquals(equation + " = " + answer, answer, problem.getAnswer());

        equation = "(2 + 3) + 5"; answer = 10;
        problem.parse(equation);
        Assert.assertEquals(equation + " = " + answer, answer, problem.getAnswer());

        equation = "1 + 2 * 3"; answer = 7;
        problem.parse(equation);
        Assert.assertEquals(equation + " = " + answer, answer, problem.getAnswer());

        equation = "(3 + 4) * 6"; answer = 42;
        problem.parse(equation);
        Assert.assertEquals(equation + " = " + answer, answer, problem.getAnswer());

        equation = "(1 * 4) + (5 * 2)"; answer = 14;
        problem.parse(equation);
        Assert.assertEquals(equation + " = " + answer, answer, problem.getAnswer());
    }
}
