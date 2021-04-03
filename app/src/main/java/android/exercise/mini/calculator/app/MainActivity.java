package android.exercise.mini.calculator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private final static String DEFAULT_STATE = "state";

    @VisibleForTesting
    public SimpleCalculator calculator;
    private TextView textViewCalculatorOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculator == null) {
            calculator = new SimpleCalculatorImpl();
        }

        // find all views
        textViewCalculatorOutput = findViewById(R.id.textViewCalculatorOutput);
        TextView buttonEquals = findViewById(R.id.buttonEquals);
        TextView buttonClear = findViewById(R.id.buttonClear);
        TextView buttonPlus = findViewById(R.id.buttonPlus);
        TextView buttonMinus = findViewById(R.id.buttonMinus);
        View buttonBackSpace = findViewById(R.id.buttonBackSpace);
        TextView[] digitButtons = new TextView[]{
                findViewById(R.id.button0),
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)
        };

        // initial update main text-view based on calculator's output
        textViewCalculatorOutput.setText(calculator.output());

        // set click listeners on all buttons to operate on the calculator and refresh main text-view
        for (int i = 0; i < 10; ++i) {
            final int finalI = i;
            digitButtons[i].setOnClickListener(v -> {
                calculator.insertDigit(finalI);
                textViewCalculatorOutput.setText(calculator.output());
            });
        }

        buttonClear.setOnClickListener(v -> {
            calculator.clear();
            textViewCalculatorOutput.setText(calculator.output());
        });
        buttonMinus.setOnClickListener(v -> {
            calculator.insertMinus();
            textViewCalculatorOutput.setText(calculator.output());
        });
        buttonPlus.setOnClickListener(v -> {
            calculator.insertPlus();
            textViewCalculatorOutput.setText(calculator.output());
        });
        buttonEquals.setOnClickListener(v -> {
            calculator.insertEquals();
            textViewCalculatorOutput.setText(calculator.output());
        });
        buttonBackSpace.setOnClickListener(v -> {
            calculator.deleteLast();
            textViewCalculatorOutput.setText(calculator.output());
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DEFAULT_STATE, calculator.saveState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
        calculator.loadState(savedInstanceState.getSerializable(DEFAULT_STATE));
        textViewCalculatorOutput.setText(calculator.output());
    }
}