package com.example.bookslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View view) {
        Intent intent = new Intent(MainActivity.this, BooksActivity.class);
        EditText editText = findViewById(R.id.editText);
        intent.putExtra("bookNameForSearch", editText.getText().toString());
        Log.i("MAIN ACTIVITY: ", editText.getText().toString());
        startActivity(intent);
    }
}
