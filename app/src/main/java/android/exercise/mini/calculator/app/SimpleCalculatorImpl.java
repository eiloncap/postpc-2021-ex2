package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public class SimpleCalculatorImpl implements SimpleCalculator {

    private final static String DEFAULT_STATE = "0";

    private String stateStr = DEFAULT_STATE;
    private boolean isLastOperatorMinus = false;
    private LinkedList<Integer> values = new LinkedList<>(Collections.singletonList(0));

    @Override
    public String output() {
        return stateStr;
    }

    @Override
    public void insertDigit(int digit) throws IllegalArgumentException {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be a number between 0 to 9.");
        }
        String digitAsString = Integer.toString(digit);
        stateStr = stateStr.equals(DEFAULT_STATE) ? digitAsString : stateStr + digitAsString;
        int val = (values.pop() * 10) + (isLastOperatorMinus ? -digit : digit);
        values.push(val);
    }

    @Override
    public void insertPlus() {
        if (Character.isDigit(stateStr.charAt(stateStr.length() - 1))) {
            stateStr += "+";
            values.push(0);
            isLastOperatorMinus = false;
        }
    }

    @Override
    public void insertMinus() {
        if (Character.isDigit(stateStr.charAt(stateStr.length() - 1))) {
            stateStr += "-";
            values.push(0);
            isLastOperatorMinus = true;
        }
    }

    @Override
    public void insertEquals() {
        int sum = 0;
        while (!values.isEmpty()) {
            sum += values.pop();
        }
        values.push(sum);
        isLastOperatorMinus = (sum < 0);
        stateStr = Integer.toString(sum);
    }

    @Override
    public void deleteLast() {
        if (stateStr.length() == 1) {
            clear();
        } else {
            char deletedChar = stateStr.charAt(stateStr.length() - 1);
            stateStr = stateStr.substring(0, stateStr.length() - 1);
            isLastOperatorMinus = (stateStr.lastIndexOf('-') > stateStr.lastIndexOf('+'));
            int lastValue = values.pop();
            if (Character.isDigit(deletedChar)) {
                values.push(lastValue / 10);
            }
        }
    }

    @Override
    public void clear() {
        stateStr = DEFAULT_STATE;
        values.clear();
        values.push(0);
        isLastOperatorMinus = false;
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        state.stateStr = stateStr;
        state.isLastOperatorMinus = isLastOperatorMinus;
        state.values = new LinkedList<>(values);
        return state;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        stateStr = casted.stateStr;
        isLastOperatorMinus = casted.isLastOperatorMinus;
        values = new LinkedList<>(casted.values);
    }

    private static class CalculatorState implements Serializable {
        /*
        all fields must only be from the types:
        - primitives (e.g. int, boolean, etc)
        - String
        - ArrayList<> where the type is a primitive or a String
        - HashMap<> where the types are primitives or a String
         */
        public String stateStr;
        public boolean isLastOperatorMinus;
        public LinkedList<Integer> values;
    }
}
