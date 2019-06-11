package com.example.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void display(int number) {
        TextView numberTextView = findViewById(R.id.number_text_view);
        numberTextView.setText("" + number);
    }

    public void increment(View view) { display(++number); }

    public void decrement(View view) { display(--number);}
}
