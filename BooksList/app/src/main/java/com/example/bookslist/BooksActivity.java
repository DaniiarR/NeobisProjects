package com.example.bookslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BooksActivity extends AppCompatActivity {

    public static String urlString = "https://www.googleapis.com/books/v1/volumes?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent intent = getIntent();
        String nameForSearch = intent.getStringExtra("bookNameForSearch");
        nameForSearch = nameForSearch.replace(' ', '+');
        urlString += nameForSearch + "&maxValue=10";
        Log.i(BooksActivity.class.getName(), urlString);
    }
}
