I pledge the highest level of ethical principles in support of academic excellence.
I ensure that all of my work reflects my own abilities and not those of someone else.

Question: Saying we want to add a cool feature - button "x" to run multiplication.
          What code do we need to change/add/remove to support this feature?
          Which tests can we run on the calculator? On the activity? On the app?

Answer: Obviously we will need to add insertMult() function in SimpleCalculatorImpl.
        Also, my implementation will require changing the way I handle the linkedList of numbers,
        by remembering also the operators and actually handling each operator differently in
        insertEquals() (Right now I am just keeping a linkedList containing only the numbers,
        while keeping numbers that coming after a minus operator as negative numbers and then
        summing the list). I guess that adding parentheses buttons will be required as well.

        SimpleCalculatorImplTest should be run on the calculator - test that ensures that the
        calculator behaves as desired, and is correct mathematically (logically).

        MainActivityTest should be run on the activity - test that ensures that all GUI part work
        fine, that buttons call the right functions, and that the TextView is updated as desired.

        AppFlowTest should be run on the app - test that ensures that the "glue" between the
        activity and the calculator is working well, for example clicking a buttons sequence
        supposed to give an output as expected from the calculator.