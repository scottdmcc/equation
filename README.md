# Write a program to parse and evaluate arithmetic expressions

## By Scott McCollough

---

## Requirements

Assignment:

Write a program to parse and evaluate arithmetic expressions that supports addition and multiplication at a minimum.
In addition, the solution submitted should contain a testing framework which will be used to validate that the solution is functioning as desired.

The input into your solution will be text similar to the following example:

1 + 1

(3 + 4) * 6

(1 * 4) + (5 * 2)

Your solution should demonstrate the ability to parse/evaluate arithmetic expressions (do not use the "Shunting Yard" algorithm) as well as provide
indications that the testing framework is validating your software solution.

Please feel free to use whatever tools and languages you are most comfortable with when working through your solution however, the solution will need
to be written in an Object Oriented fashion. Once completed, be prepared to show us your source code, insight into the design/architecture decisions
made, results of the solution (i.e. output) and any other information relevant to the provided solution.

## Setup

The java executable should be named `equation.jar`.

The syntax is simple

`java -jar ./equation.jar "{EQUATION}"`

The equation has a few rules of syntax:

* Only addition and multiplication are functional. Subtraction can be added with minimal changes.
* Parenthesis can be added to affect order.
* The equation must be enclosed in quotes for the program to read it correctly. If not, an information blurb will be displayed instead.
    
Here are a few example equations:

* "1 + 1"
* "(2 + 3) + 5"
* "1 + 2 * 3"
* "(1 * 4) + (5 * 2)"
* "((10 * 10) * 10)"

The sanitation routine takes care of most spacing issues, so "1+1" works as an input but gets converted to "1 + 1".

Division is not included as this app deals strictly with integers, and division may produce floating point answers. 

The output is the formatted equation, an equal sign, and then the answer.
