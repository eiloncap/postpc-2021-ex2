package android.exercise.mini.calculator.app;

import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {

    // todo: add fields as needed
    private final static String DEFAULT_STATE = "0";

    private String stateStr = DEFAULT_STATE;
    private int calculatedValue = 0, lastValue = 0;
    private boolean isLAstOperatorMinus = false;

    @Override
    public String output() {
        return stateStr;
    }

    @Override
    public void insertDigit(int digit) {
        String digitAsString = Integer.toString(digit);
        stateStr = stateStr.equals(DEFAULT_STATE) ? digitAsString : stateStr + digitAsString;
        lastValue = (lastValue * 10) + digit * Integer.signum(lastValue);
    }

    @Override
    public void insertPlus() {
        if (Character.isDigit(stateStr.charAt(stateStr.length() - 1))) {
            stateStr += "+";
            calculateLastOperator();
            isLAstOperatorMinus = false;
        }
    }

    @Override
    public void insertMinus() {
        if (Character.isDigit(stateStr.charAt(stateStr.length() - 1))) {
            stateStr += "-";
            calculateLastOperator();
            isLAstOperatorMinus = true;
        }
    }

    @Override
    public void insertEquals() {
        // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
        //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
        calculateLastOperator();
        isLAstOperatorMinus = false;
        stateStr = Integer.toString(calculatedValue);
        lastValue = calculatedValue;
        calculatedValue = 0;
    }

    private void calculateLastOperator() {
        calculatedValue += isLAstOperatorMinus ? -lastValue : lastValue;
        lastValue = 0;
    }

    @Override
    public void deleteLast() {
        // todo: delete the last input (digit, plus or minus)
        //  e.g.
        //  if input was "12+3" and called `deleteLast()`, then delete the "3"
        //  if input was "12+" and called `deleteLast()`, then delete the "+"
        //  if no input was given, then there is nothing to do here
    }

    @Override
    public void clear() {
        // todo: clear everything (same as no-input was never given)
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        // todo: insert all data to the state, so in the future we can load from this state
        return state;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        // todo: use the CalculatorState to load
    }

    private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    }
}
