package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent intent = getIntent();
        setText(intent.getDoubleExtra("answer", 0));
    }

    private void setText(double answer) {
        TextView answerTextView = findViewById(R.id.answer_textView);
        if ((answer == Math.floor(answer)) && !Double.isInfinite(answer)) {
            answerTextView.setText(String.valueOf((int) answer));
        } else {
            answerTextView.setText(String.valueOf(answer));
        }
    }
}