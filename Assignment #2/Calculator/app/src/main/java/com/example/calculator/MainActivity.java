package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double number1, number2, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addition(View view) {
        TextView operatorTextView = findViewById(R.id.operatorTextView);
        operatorTextView.setText("+");
    }

    public void subtraction(View view) {
        TextView operatorTextView = findViewById(R.id.operatorTextView);
        operatorTextView.setText("-");
    }

    public void multiplication(View view) {
        TextView operatorTextView = findViewById(R.id.operatorTextView);
        operatorTextView.setText("x");
    }

    public void division(View view) {
        TextView operatorTextView = findViewById(R.id.operatorTextView);
        operatorTextView.setText("/");
    }

    public void calculate(View view) {
        EditText editText1 = findViewById(R.id.firstNumberEditText);
        number1 = Double.parseDouble(editText1.getText().toString());
        EditText editText2 = findViewById(R.id.secondNumberEditText);
        number2 = Double.parseDouble(editText2.getText().toString());
        TextView operatorTextView = findViewById(R.id.operatorTextView);
        System.out.println(number2);
        switch (operatorTextView.getText().toString()) {
            case "+":
                answer = number1 + number2;
                break;
            case "-":
                answer = number1 - number2;
                break;
            case "x":
                answer = number1 * number2;
                break;
            case "/":
                answer = number1 / number2;
        }
        Intent answerIntent = new Intent(this, AnswerActivity.class);
        answerIntent.putExtra("answer", answer);
        startActivity(answerIntent);
    }

//    private void startActivity() {
//        Intent answerIntent = new Intent(MainActivity.this, AnswerActivity.class);
//        answerIntent.putExtra("answer", answer);
//        startActivity(answerIntent);
//    }
}
