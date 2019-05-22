package com.chronoviking.app;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MathematicsTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Mathematics problem = new Mathematics();
        int answer;
        System.out.println("Running tests...");

        problem.setEquation("1 + 1"); answer = 2;
        Assert.assertEquals(problem.getEquation() + " = " + answer, answer, problem.parse());

        problem.setEquation("(2 + 3) + 5"); answer = 10;
        Assert.assertEquals(problem.getEquation() + " = " + answer, answer, problem.parse());

        problem.setEquation("1 + 2 * 3"); answer = 7;
        Assert.assertEquals(problem.getEquation() + " = " + answer, answer, problem.parse());

        problem.setEquation("(3 + 4) * 6"); answer = 42;
        Assert.assertEquals(problem.getEquation() + " = " + answer, answer, problem.parse());

        problem.setEquation("(1 * 4) + (5 * 2)"); answer = 14;
        Assert.assertEquals(problem.getEquation() + " = " + answer, answer, problem.parse());

        problem.setEquation("apple + (5 * 2)"); answer = -1;
        Assert.assertEquals(problem.getEquation() + " is not a valid equation", answer, problem.parse());
    }
}
