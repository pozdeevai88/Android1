package ru.geekbrains.android1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private static final LinkedList<String> formula = new LinkedList<>();
    static final String LOG_TAG = "CALC_LOG:";
    private static boolean isResultOnScreen = true;
    private static boolean isActionSet = false;
    private static String currentNumber = "";
    private TextView numbersBox;
    private UserData userData;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userData = new UserData("0");
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "onCreate()");

        numbersBox = findViewById(R.id.numbersBox);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSub = findViewById(R.id.buttonSub);
        Button buttonMul = findViewById(R.id.buttonMul);
        Button buttonDiv = findViewById(R.id.buttonDiv);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonResult = findViewById(R.id.buttonResult);

        button1.setOnClickListener(v -> numberPressed("1"));
        button2.setOnClickListener(v -> numberPressed("2"));
        button3.setOnClickListener(v -> numberPressed("3"));
        button4.setOnClickListener(v -> numberPressed("4"));
        button5.setOnClickListener(v -> numberPressed("5"));
        button6.setOnClickListener(v -> numberPressed("6"));
        button7.setOnClickListener(v -> numberPressed("7"));
        button8.setOnClickListener(v -> numberPressed("8"));
        button9.setOnClickListener(v -> numberPressed("9"));
        button0.setOnClickListener(v -> numberPressed("0"));
        buttonDot.setOnClickListener(v -> numberPressed("."));

        buttonAdd.setOnClickListener(v -> {
            actionPressed();
            numbersBox.setText(numbersBox.getText() + "+");
            formula.add("ADD");
        });

        buttonSub.setOnClickListener(v -> {
            actionPressed();
            numbersBox.setText(numbersBox.getText() + "-");
            formula.add("SUB");

        });

        buttonMul.setOnClickListener(v -> {
            actionPressed();
            numbersBox.setText(numbersBox.getText() + "*");
            formula.add("MUL");
        });

        buttonDiv.setOnClickListener(v -> {
            actionPressed();
            numbersBox.setText(numbersBox.getText() + "/");
            formula.add("DIV");
        });

        buttonResult.setOnClickListener(v -> {
            formula.add(currentNumber);
            isResultOnScreen = true;
            currentNumber = "";
            Log.i(LOG_TAG, formula.toString());
            if (formula.size() >= 3) {
                for (int i = 1; i <= formula.size() - 1; i += 2) {
                    float a = 0;
                    float b = 0;
                    try {
                        a = Float.parseFloat(formula.get(i - 1));
                        b = Float.parseFloat(formula.get(i + 1));
                    } catch (NumberFormatException e) {
                        numbersBox.setText("error");
                    }
                    Log.i(LOG_TAG, formula.toString());
                    Log.i(LOG_TAG, a + " " + formula.get(i) + " " + b);
                    switch (formula.get(i)) {
                        case "ADD":
                            formula.set(i + 1, String.valueOf(Float.sum(a, b)));
                            break;
                        case "SUB":
                            formula.set(i + 1, String.valueOf(a - b));
                            break;
                        case "MUL":
                            formula.set(i + 1, String.valueOf(a * b));
                            break;
                        case "DIV":
                            formula.set(i + 1, String.valueOf(a / b));
                            break;
                    }
                    Log.i(LOG_TAG, formula.toString());
                }
                numbersBox.setText(formula.getLast());
            }
            formula.clear();
        });
    }

    @SuppressLint("SetTextI18n")
    private void actionPressed() {
        if (isResultOnScreen) numbersBox.setText("");
        if (isActionSet) {
            try {
                numbersBox.setText(
                        numbersBox.getText().subSequence(0, numbersBox.getText().length() - 1));
            } catch (StringIndexOutOfBoundsException e) {
                numbersBox.setText("error");
            }
            formula.removeLast();
        }
        if (!isActionSet) formula.add(currentNumber);
        isActionSet = true;
        currentNumber = "";
    }

    @SuppressLint("SetTextI18n")
    private void numberPressed(String number) {
        if (isResultOnScreen) numbersBox.setText("");
        isResultOnScreen = false;
        isActionSet = false;
        numbersBox.setText(numbersBox.getText() + number);
        currentNumber += number;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        numbersBox.setText(userData.getTextBox());
    }

    @Override
    protected void onPause() {
        super.onPause();
        userData.setTextBox(numbersBox.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        userData.setTextBox(numbersBox.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        numbersBox.setText(userData.getTextBox());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable("NumbersBox", userData);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        userData = instanceState.getParcelable("NumbersBox");
    }

}