package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    assertEquals("0-", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsEqualsAfterSingleNumber_then_outputShouldBeTheNumber(){
    // 13=
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertEquals();
    assertEquals("13", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsEqualsAfterOperator_then_operatorShouldBeIgnored(){
    // 14+21+=
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertEquals();
    assertEquals("35", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsOperatorsAndEqualsSequence_then_operatorsShouldBeIgnored(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertEquals();
    assertEquals("68", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsOperatorAfterDifferentOperator_then_secondOperatorShouldBeIgnored(){
    // 51+-12=
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    assertEquals("51+12", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    String expected = "63";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsDigitAfterEquals_then_digitShouldContinueLastOutput(){
    // (1-2=)4
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertDigit(4);
    assertEquals("-14", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    String expected = "-14";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsAfterEquals_then_calculationShouldBeCorrect(){
    // (1-2=)+4=
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    assertEquals("-1+4", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    String expected = "3";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsLongNumbers_then_outputShouldBeCorrect(){
    // 9999-8888-2222=-3333
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    assertEquals("-1111-3333", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    String expected = "-4444";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }

  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_insertingAfterCallingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.deleteLast();
    assertEquals("1", calculatorUnderTest.output());
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    assertEquals("12+", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("12", calculatorUnderTest.output());
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("11", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("1", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("1", calculatorUnderTest.output());
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(2);
    assertEquals("19+2", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("21", calculatorUnderTest.output());
  }

  @Test
  public void when_deletingNothing_then_isNoIgnored(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("0", calculatorUnderTest.output());

  }

  @Test
  public void when_zeroIsBetweenOperators_then_isNotIgnored(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // successfully deleting a zero: 21+0+9
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    assertEquals("21+", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("21", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClearMultiTimes_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    assertEquals("8-7", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("1", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // give some input
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);
    firstCalculator.insertEquals();
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(3);
    firstCalculator.deleteLast();
    firstCalculator.insertDigit(1);

    // save current state
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);

    // load the saved state and make sure state was loaded correctly
    secondCalculator.loadState(savedState);
    assertEquals("12-1", secondCalculator.output());
    secondCalculator.insertEquals();
    assertEquals("11", secondCalculator.output());
  }

  @Test
  public void when_savingStateFromFirstCalculatorAndSavingSecondCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // give some input to both calculators
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);
    firstCalculator.insertEquals();
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(3);
    firstCalculator.deleteLast();
    firstCalculator.insertDigit(1);

    secondCalculator.insertDigit(3);
    secondCalculator.insertDigit(1);
    secondCalculator.insertPlus();
    secondCalculator.insertDigit(8);
    secondCalculator.insertEquals();
    secondCalculator.insertMinus();
    secondCalculator.insertDigit(7);

    // save current states
    Serializable savedFirstState = firstCalculator.saveState();
    assertNotNull(savedFirstState);
    Serializable savedSecondState = secondCalculator.saveState();
    assertNotNull(savedSecondState);

    // call `clear` and make sure calculator cleared
    firstCalculator.clear();
    assertEquals("0", firstCalculator.output());
    secondCalculator.clear();
    assertEquals("0", secondCalculator.output());

    // change calculators states
    firstCalculator.insertDigit(2);
    firstCalculator.insertDigit(3);
    firstCalculator.insertEquals();
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(2);
    secondCalculator.insertMinus();
    secondCalculator.insertDigit(3);
    secondCalculator.insertDigit(1);

    // load the saved state and make sure state was loaded correctly
    firstCalculator.loadState(savedSecondState);
    assertEquals("39-7", firstCalculator.output());
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(4);
    firstCalculator.insertEquals();
    assertEquals("36", firstCalculator.output());

    secondCalculator.loadState(savedFirstState);
    assertEquals("12-1", secondCalculator.output());
    secondCalculator.insertEquals();
    assertEquals("11", secondCalculator.output());
  }
}