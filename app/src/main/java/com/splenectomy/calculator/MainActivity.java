package com.splenectomy.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView expressionTextView;
    private TextView resultTextView;

    private Button clearButton;
    private Button backspaceButton;
    private Button openBraceButton;
    private Button closeBraceButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    private Button pointButton;
    private Button equalsButton;
    private Button plusButton;
    private Button minusButton;
    private Button multiplyButton;
    private Button divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //получение ссылок на компоненты
        expressionTextView = (TextView) findViewById(R.id.expressionTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        clearButton = (Button) findViewById(R.id.clearButton);
        backspaceButton = (Button) findViewById(R.id.backspaceButton);
        openBraceButton = (Button) findViewById(R.id.openBraceButton);
        closeBraceButton = (Button) findViewById(R.id.closeBraceButton);
        oneButton = (Button) findViewById(R.id.oneButton);
        twoButton = (Button) findViewById(R.id.twoButton);
        threeButton = (Button) findViewById(R.id.threeButton);
        fourButton = (Button) findViewById(R.id.fourButton);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        sixButton = (Button) findViewById(R.id.sixButton);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        eightButton = (Button) findViewById(R.id.eightButton);
        nineButton = (Button) findViewById(R.id.nineButton);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        pointButton = (Button) findViewById(R.id.pointButton);
        equalsButton = (Button) findViewById(R.id.equalsButton);
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        divideButton = (Button) findViewById(R.id.divideButton);

        //установка слушателей событий
        clearButton.setOnClickListener(clearButtonListener);
        backspaceButton.setOnClickListener(backspaceButtonListener);
        openBraceButton.setOnClickListener(openBraceButtonListener);
        closeBraceButton.setOnClickListener(closeBraceButtonListener);
        oneButton.setOnClickListener(digitButtonListener);
        twoButton.setOnClickListener(digitButtonListener);
        threeButton.setOnClickListener(digitButtonListener);
        fourButton.setOnClickListener(digitButtonListener);
        fiveButton.setOnClickListener(digitButtonListener);
        sixButton.setOnClickListener(digitButtonListener);
        sevenButton.setOnClickListener(digitButtonListener);
        eightButton.setOnClickListener(digitButtonListener);
        nineButton.setOnClickListener(digitButtonListener);
        zeroButton.setOnClickListener(digitButtonListener);
        pointButton.setOnClickListener(digitButtonListener);
        equalsButton.setOnClickListener(equalsButtonListener);
    }

    private final View.OnClickListener clearButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            expressionTextView.setText("");
            resultTextView.setText("0");
        }
    };

    private final View.OnClickListener backspaceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            StringBuilder str = new StringBuilder(expressionTextView.getText().toString());
            if (str.length()>0) {
                str.deleteCharAt(str.length()-1);
                expressionTextView.setText(str);
            }
        }
    };

    private final View.OnClickListener openBraceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            expressionTextView.append("(");
        }
    };

    private final View.OnClickListener closeBraceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            StringBuilder str = new StringBuilder(expressionTextView.getText().toString());
            if (str.length() > 1) {
                expressionTextView.append(")");
            }
        }
    };

    private final View.OnClickListener digitButtonListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.oneButton:
                    expressionTextView.append(getResources().getString(R.string.one_text));
                    break;
                case R.id.twoButton:
                    expressionTextView.append(getResources().getString(R.string.two_text));
                    break;
                case R.id.threeButton:
                    expressionTextView.append(getResources().getString(R.string.three_text));
                    break;
                case R.id.fourButton:
                    expressionTextView.append(getResources().getString(R.string.four_text));
                    break;
                case R.id.fiveButton:
                    expressionTextView.append(getResources().getString(R.string.five_text));
                    break;
                case R.id.sixButton:
                    expressionTextView.append(getResources().getString(R.string.six_text));
                    break;
                case R.id.sevenButton:
                    expressionTextView.append(getResources().getString(R.string.seven_text));
                    break;
                case R.id.eightButton:
                    expressionTextView.append(getResources().getString(R.string.eight_text));
                    break;
                case R.id.nineButton:
                    expressionTextView.append(getResources().getString(R.string.nine_text));
                    break;
                case R.id.zeroButton:
                    expressionTextView.append(getResources().getString(R.string.zero_text));
                    break;
                case R.id.pointButton:
                    expressionTextView.append(getResources().getString(R.string.point_text));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    private final View.OnClickListener equalsButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            resultTextView.setText(getResources().getString(R.string.error_result));
        }
    };
}