package com.example.counter;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("number");
        }
        TextView numberTextView = findViewById(R.id.number_text_view);
        numberTextView.setText(String.format("%04d", number));
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        TextView numberTextView = findViewById(R.id.number_text_view);
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            setContentView(R.layout.activity_main_landscape);
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
//            numberTextView.setText(String.format("%04d", number));
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_main);
//            numberTextView.setText(String.format("%04d", number));
//        }
//    }

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
        numberTextView.setText(String.format("%04d", number));
    }

    public void increment(View view) { display(++number); }

    public void decrement(View view) { display(--number);}
}
