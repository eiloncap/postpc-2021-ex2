package android.exercise.mini.calculator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @VisibleForTesting
    public SimpleCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculator == null) {
            calculator = new SimpleCalculatorImpl();
        }

        // find all views
        TextView textViewCalculatorOutput = findViewById(R.id.textViewCalculatorOutput),
                button0 = findViewById(R.id.button0),
                button1 = findViewById(R.id.button1),
                button2 = findViewById(R.id.button2),
                button3 = findViewById(R.id.button3),
                button4 = findViewById(R.id.button4),
                button5 = findViewById(R.id.button5),
                button6 = findViewById(R.id.button6),
                button7 = findViewById(R.id.button7),
                button8 = findViewById(R.id.button8),
                button9 = findViewById(R.id.button9),
                buttonEquals = findViewById(R.id.buttonEquals),
                buttonClear = findViewById(R.id.buttonClear),
                buttonPlus = findViewById(R.id.buttonPlus),
                buttonMinus = findViewById(R.id.buttonMinus);
        View buttonBackSpace = findViewById(R.id.buttonBackSpace);

    /*
    TODO:
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // todo: save calculator state into the bundle
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
    }
}