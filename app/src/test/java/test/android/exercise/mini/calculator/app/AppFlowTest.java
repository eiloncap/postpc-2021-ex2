package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class AppFlowTest {

    private ActivityController<MainActivity> activityController;
    private MainActivity activityUnderTest;
    private View button0;
    private View button1;
    private View button2;
    private View button3;
    private View button4;
    private View button5;
    private View button6;
    private View button7;
    private View button8;
    private View button9;
    private View buttonBackspace;
    private View buttonClear;
    private View buttonPlus;
    private View buttonMinus;
    private View buttonEquals;
    private TextView textViewOutput;

    /**
     * initialize main activity with a real calculator
     */
    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(MainActivity.class);
        activityUnderTest = activityController.get();
        activityController.create().start().resume();
        button0 = activityUnderTest.findViewById(R.id.button0);
        button1 = activityUnderTest.findViewById(R.id.button1);
        button2 = activityUnderTest.findViewById(R.id.button2);
        button3 = activityUnderTest.findViewById(R.id.button3);
        button4 = activityUnderTest.findViewById(R.id.button4);
        button5 = activityUnderTest.findViewById(R.id.button5);
        button6 = activityUnderTest.findViewById(R.id.button6);
        button7 = activityUnderTest.findViewById(R.id.button7);
        button8 = activityUnderTest.findViewById(R.id.button8);
        button9 = activityUnderTest.findViewById(R.id.button9);
        buttonBackspace = activityUnderTest.findViewById(R.id.buttonBackSpace);
        buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
        buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);
        buttonMinus = activityUnderTest.findViewById(R.id.buttonMinus);
        buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);
        textViewOutput = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    }

    @Test
    public void flowTest1() {
        // run clicks on "13+5"
        for (View button : Arrays.asList(
                button1, button3, buttonPlus, button5
        )) {
            button.performClick();
        }

        assertEquals("13+5", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest2() {
        // run clicks on "7+5<backspace>4="
        for (View button : Arrays.asList(
                button7, buttonPlus, button5, buttonBackspace, button4, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("11", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest3() {
        // run clicks on "11++9"
        for (View button : Arrays.asList(
                button1, button1, buttonPlus, buttonPlus, button9
        )) {
            button.performClick();
        }

        assertEquals("11+9", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest4() {
        // run clicks on "21+-4"
        for (View button : Arrays.asList(
                button2, button1, buttonPlus, buttonMinus, button4
        )) {
            button.performClick();
        }

        assertEquals("21+4", textViewOutput.getText().toString());
        buttonEquals.performClick();
        assertEquals("25", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest5() {
        // run clicks on "1-<clear>3"
        for (View button : Arrays.asList(
                button1, buttonMinus, buttonClear, button3
        )) {
            button.performClick();
        }

        assertEquals("3", textViewOutput.getText().toString());
        buttonEquals.performClick();
        assertEquals("3", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest6() {
        // run clicks on "6=+7==<backspace><backspace>"
        for (View button : Arrays.asList(
                button6, buttonEquals, buttonPlus, buttonEquals, buttonEquals, buttonBackspace,
                buttonBackspace
        )) {
            button.performClick();
        }

        assertEquals("0", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest7() {
        // run clicks on "<backspace>1<backspace>3+222<clear>111-222="
        for (View button : Arrays.asList(
                buttonBackspace, button1, buttonBackspace, button3, buttonPlus, button2, button2,
                button2, buttonClear, button1, button1, button1, buttonMinus, button2, button2,
                button2, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("-111", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest8() {
        // run clicks on "33-55+--1+=<backspace>"
        for (View button : Arrays.asList(
                button3, button3, buttonMinus, button5, button5, buttonPlus, buttonMinus,
                buttonMinus, button1, buttonPlus, buttonEquals, buttonBackspace
        )) {
            button.performClick();
        }

        assertEquals("-2", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest9() {
        // run clicks on "12345+0-012343="
        for (View button : Arrays.asList(
                button1, button2, button3, button4, button5, buttonPlus, button0, buttonMinus,
                button0, button1, button2, button3, button4, button3, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("2", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest10() {
        // run clicks on "12-5<backspace>"
        for (View button : Arrays.asList(
                button1, button2, buttonMinus, button5, buttonBackspace
        )) {
            button.performClick();
        }

        assertEquals("12-", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest11() {
        // run clicks on "0000<backspace>1+<clear>-000555"
        for (View button : Arrays.asList(
                button0, button0, button0, button0, buttonBackspace, button1, buttonPlus,
                buttonClear, buttonMinus, button0, button0, button0, button5, button5, button5
        )) {
            button.performClick();
        }

        assertEquals("0-555", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest12() {
        // run clicks on "145-0200+1=+--54=+0+0+0"
        for (View button : Arrays.asList(
                button1, button4, button5, buttonMinus, button0, button2, button0, button0,
                buttonPlus, button1, buttonEquals, buttonPlus, buttonMinus, buttonMinus, button5,
                button4, buttonEquals, buttonPlus, button0, buttonPlus, button0, buttonPlus, button0
        )) {
            button.performClick();
        }

        assertEquals("0+0+0+0", textViewOutput.getText().toString());
    }
}
