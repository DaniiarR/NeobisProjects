package com.example.bookslist;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String urlString = "https://www.googleapis.com/books/v1/volumes?q=";
    public static String nameForSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent intent = getIntent();
        nameForSearch = intent.getStringExtra("bookNameForSearch").replace(' ', '+');

        getLoaderManager().initLoader(0, null, this);

    }


    public void updateUi(final ArrayList<Book> books) {
        // find the ListView and set the adapter to it
        ListView listView = findViewById(R.id.listView);
        BooksAdapter adapter = new BooksAdapter(this, books);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book currentBook = books.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentBook.getPreviewLink()));
                startActivity(intent);
            }
        });
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BooksLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        updateUi((ArrayList<Book>) data);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
