package com.example.greeter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void greet(View view) {
        EditText nameEditText = findViewById(R.id.name_editText);
        String name = nameEditText.getText().toString();
        if (name.isEmpty() || name.matches(".*\\d+.*")) {
            showToast();
        } else {
            TextView nameTextView = findViewById(R.id.name_textView);
            nameTextView.setText("Hello, " + name.substring(0, 1).toUpperCase() + name.substring(1));
        }
    }

    private void showToast() {
        Context context = getApplicationContext();
        CharSequence text = "Enter a correct name!";
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }
}
