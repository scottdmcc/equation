# Write a program to parse and evaluate arithmetic expressions

## By Scott McCollough

---

## Setup

The java executable should be named `equation.jar`. It may have been renamed for easier emailing.

The syntax is simple

`java -jar ./equation.jar "{EQUATION}"`

The equation has a few rules of syntax:

* Only addition and multiplication are functional. Subtraction can be added with minimal changes.
* Parenthesis can be added to affect order.
    
Here are a few example equations:

* "1 + 1"
* "(2 + 3) + 5"
* "1 + 2 * 3"
* "(1 * 4) + (5 * 2)"

The sanitation routine takes care of most spacing issues, so "1+1" works as an input but gets converted to "1 + 1".

Division is not included as this app deals strictly with integers, and division may produce floating point answers. 

The output is the formatted equation, an equal sign, and then the answer.
