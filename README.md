I pledge the highest level of ethical principles in support of academic excellence.
I ensure that all of my work reflects my own abilities and not those of someone else.

# Question:
Saying we want to add a cool feature - button "x" to run multiplication.
What code do we need to change/add/remove to support this feature?
Which tests can we run on the calculator? On the activity? On the app?

# Answer:
Obviously we will need to add insertMult() function in SimpleCalculatorImpl.
Also, my implementation will require changing the way I handle the linkedList of numbers,
by remembering also the operators and actually handling each operator differently in
insertEquals() (Right now I am just keeping a linkedList containing only the numbers,
while keeping numbers that coming after a minus operator as negative numbers and then
summing the list). I guess that adding parentheses buttons will be nice as well.

Calculator tests - We can run test for multiplication by 0, test for multiplication of
negative numbers, test checking correct order of operations. Tests for calculator behaviour
when calling insertMult() twice, or calling insertMult() before/after insertPlus(),
insertMinus(), insertEquals(). Testing a call to insertMult() before any other function was
called.

Activity tests - Check that clicking the X button will call insertMult() function, and tests
checking that saving state involving multiplication works well.

App tests - Tests performing sequences of buttons clicks that involve multiplication and
checking the output, including edge cases as mentioned for the calculator tests.