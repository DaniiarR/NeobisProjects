package com.example.shopinventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditorActivity extends AppCompatActivity {

    public static final String LOG_TAG = EditorActivity.class.getSimpleName();

    private EditText nameEditText;
    private EditText quantityEditText;
    private EditText priceEditText;

    public long mId;
    public String mName;
    public int mQuantity;
    public double mPrice;

    public ProductsDatabase database =  ProductsDatabase.getInMemoryDatabase(this);
    public ProductDao productDao = database.productDao();

    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        try {
            intent = getIntent();
            getProductInfoFromIntent();
            setViewsText();
        } catch (NullPointerException e) {
            Log.i(LOG_TAG, "No intent that has started this activity");
            intent = null;
        }

        ImageView productImageView = findViewById(R.id.productImageView);
        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                }

            }
        });
    }

    private void getProductInfoFromIntent() {
        //TODO To implement this method
    }

    private void setViewsText() {
        nameEditText.setText(mName);
        quantityEditText.setText(mQuantity);
        priceEditText.setText(String.valueOf(mPrice));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                savePet();
            case R.id.action_delete:
                deletePet();
        }
    }

    private void savePet() {
        nameEditText = findViewById(R.id.nameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        priceEditText = findViewById(R.id.priceEditText);

        mName = nameEditText.getText().toString();
        mQuantity = Integer.parseInt(nameEditText.getText().toString());
        mPrice = Double.parseDouble(priceEditText.getText().toString());

        SaveProductAsyncTask task = new SaveProductAsyncTask();
        task.execute();
    }

    private void deletePet() {
        //TODO to implement this method
    }

    private class SaveProductAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (intent == null) {
                Log.i(LOG_TAG, "Creating a new product");
                Product product = new Product()
            }
        }
    }
}
