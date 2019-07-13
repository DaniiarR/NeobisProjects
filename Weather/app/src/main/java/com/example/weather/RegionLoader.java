package com.example.weather;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class RegionLoader extends AsyncTaskLoader<List<String>> {

    public static final String LOG_TAG = RegionLoader.class.getName();

    public RegionLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<String> loadInBackground() {
        return null;
    }
}
