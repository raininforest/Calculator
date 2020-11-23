package com.splenectomy.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }
}