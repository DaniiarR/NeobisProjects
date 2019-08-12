package com.example.greeter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nameEditText = findViewById(R.id.name_editText);
        if (savedInstanceState != null) {
            name = savedInstanceState.getString("name");
        }
        TextView nameTextView = findViewById(R.id.name_textView);
        if (name != null && isStringOnlyAlphabet(name)) {
            nameTextView.setText("Hello, " + name.substring(0, 1).toUpperCase() + name.substring(1));
        }
    }

    public void greet(View view) {
        EditText nameEditText = findViewById(R.id.name_editText);
        String name = nameEditText.getText().toString();
        if (!isStringOnlyAlphabet(name)) {
            showToast();
        } else {
            TextView nameTextView = findViewById(R.id.name_textView);
            nameTextView.setText("Hello, " + name.substring(0, 1).toUpperCase() + name.substring(1));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText nameEditText = findViewById(R.id.name_editText);
        String name = nameEditText.getText().toString();
        outState.putString("name", name);
    }

    public static boolean isStringOnlyAlphabet(String str)
    {
        if (str == null || str.equals("")) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ((!(ch >= 'A' && ch <= 'Z'))
                    && (!(ch >= 'a' && ch <= 'z'))) {
                return false;
            }
        }
        return true;
    }

    private void showToast() {
        Context context = getApplicationContext();
        CharSequence text = "Enter a correct name!";
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }
}
