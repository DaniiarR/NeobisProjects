package com.example.bookslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BooksLoader extends AsyncTaskLoader<List<Book>> {

    public BooksLoader(Context context) {
        super(context);
    }

    @Override
    public List<Book> loadInBackground() {
        Log.i("BooksLoader: ", BooksActivity.urlString + BooksActivity.nameForSearch + "&maxResults=10");
        return Utils.fetchBookData(BooksActivity.urlString + BooksActivity.nameForSearch + "&maxResults=10");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


}
