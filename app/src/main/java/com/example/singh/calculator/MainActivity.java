package com.example.singh.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.singh.calculator.R.id.operation;

public class MainActivity extends AppCompatActivity {

    private EditText newNumber;
    private EditText result;
    private TextView displayOperation;

    private Double operand1 = null;
    public static final String OP_SAVER="opSaver";
    public static final String rssaver="rsSaver";
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(operation);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button negative = (Button) findViewById(R.id.neg);
        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonDecimal = (Button) findViewById(R.id.buttonDecimal);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String str=b.getText().toString();
                if(str.equals("Neg")){
                    String temp=newNumber.getText().toString();
                    if(temp.length()!=0)
                        temp="-"+temp;
                    else
                        temp="-";
                    newNumber.setText(temp);
                } else {
                    newNumber.append(b.getText().toString());
                }

            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        negative.setOnClickListener(listener);
        buttonDecimal.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    performOperation(Double.valueOf(value), op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }

                pendingOperation = op;
                displayOperation.setText(pendingOperation);

            }
        };

        buttonAdd.setOnClickListener(opListener);
        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(OP_SAVER,pendingOperation);
        if(operand1!=null){
            outState.putDouble(rssaver,operand1);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        displayOperation.setText(savedInstanceState.getString(OP_SAVER));
        pendingOperation=savedInstanceState.getString(OP_SAVER);
        operand1=savedInstanceState.getDouble(rssaver);

    }

    public void performOperation(Double value, String op) {
        String ans = "";
        if (operand1 == null) {
            operand1 = (value);
        } else {

            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }

            if (pendingOperation.equals("=")) {
                operand1 = value;
            } else if (pendingOperation.equals("+")) {
                operand1 += value;
            } else if (pendingOperation.equals("-")) {
                operand1 -= value;
            } else if (pendingOperation.equals("*")) {
                operand1 *= value;
            } else if (pendingOperation.equals("/")) {
                if (value == 0) {
                    operand1 = 0.0;
                    ans = "Invalid";

                } else {
                    operand1 /= value;
                }
            }
        }
        if (ans.length() == 0)
            result.setText(operand1.toString());
        else {
            result.setText(ans);
            ans = "";
        }
        newNumber.setText("");
    }


}
