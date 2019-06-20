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
        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("number");
        }
        display(number);
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putInt("number", number);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        number = savedInstanceState.getInt("number");
        System.out.println(number);

    }

    private void display(int number) {
        TextView numberTextView = findViewById(R.id.number_text_view);
        if (number < 0) {
            numberTextView.setText(String.format("%05d", number));
        } else {
            numberTextView.setText(String.format("%04d", number));
        }
    }

    public void increment(View view) { display(++number); }

    public void decrement(View view) { display(--number);}
}
