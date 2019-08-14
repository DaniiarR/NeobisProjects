package com.example.shopinventory;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {
    /** Log tag for all Log messages */
    public static final String LOG_TAG = ProductsDatabase.class.getSimpleName();

    public abstract ProductDao productDao();
    private static ProductsDatabase INSTANCE;

    /**
     * Get an existing version of database if already exists,
     * if not create a new instance of ProductsDatabase
     * @param context context of the application
     * @return return new database or the existing one
     */
    public static ProductsDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            Log.i(LOG_TAG, "No existing database found. Will create a new one");
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ProductsDatabase.class,
                    "productsDatabase")
                    .build();
        }
        return INSTANCE;
    }
}
