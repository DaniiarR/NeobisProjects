package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double number1, number2, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (savedInstanceState != null) {
            TextView operatorTextView = findViewById(R.id.operatorTextView);
            operatorTextView.setText(savedInstanceState.getString("operator"));
        }
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
        EditText editText2 = findViewById(R.id.secondNumberEditText);
        if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()) {
            showToast("One or both numbers haven't been entered yet!");
        } else {
            TextView operatorTextView = findViewById(R.id.operatorTextView);
            try{
                number1 = Double.parseDouble(editText1.getText().toString());
                number2 = Double.parseDouble(editText2.getText().toString());
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
                        if (number2 == 0) {
                            showToast("Can't divide by zero!");
                        } else {
                            answer = number1 / number2;
                        }
                }
                startActivity();
            } catch (NumberFormatException ex) {
                showToast("Enter correct numbers!");
            }
        }
    }

    private void startActivity() {
        Intent answerIntent = new Intent(MainActivity.this, AnswerActivity.class);
        answerIntent.putExtra("answer", answer);
        startActivity(answerIntent);
    }

    public void clearValues(View view) {
        EditText editText1 = findViewById(R.id.firstNumberEditText);
        EditText editText2 = findViewById(R.id.secondNumberEditText);
        TextView operator = findViewById(R.id.operatorTextView);
        operator.setText("+");
        editText1.setText("");
        editText2.setText("");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView operatorTextView = findViewById(R.id.operatorTextView);
        outState.putString("operator", operatorTextView.getText().toString());
    }

    private void showToast(CharSequence message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
