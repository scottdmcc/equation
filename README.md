# Write a program to parse and evaluate arithmetic expressions

## By Scott McCollough

---

## Setup

The java executable should be named `equation.jar`. It may have been renamed for easier emailing.

The syntax is simple

`java -jar equation.jar "{EQUATION}"`

The equation has a few rules of syntax:

* Only addition and multiplication are functional
* Parenthesis can be added to affect order
* Digits and operators must have a single space around them
    * Exception: Parenthesis should not have a space on the side closest a digit
    
Here are a few example equations:

* "1 + 1"
* "(2 + 3) + 5"
* "1 + 2 * 3"
* "(1 * 4) + (5 * 2)"

Division is not included as this app deals strictly with integers, and division may produce floating point answers. There is minimal validation so spacing may cause issues.

The output is the original equation, an equal sign, and then the answer.
