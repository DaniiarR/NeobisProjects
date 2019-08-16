package com.example.shopinventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditorActivity extends AppCompatActivity {

    public static final String LOG_TAG = EditorActivity.class.getSimpleName();

    private ImageView productImageView;
    private EditText nameEditText;
    private EditText quantityEditText;
    private EditText priceEditText;

    public long mId;
    public String mName;
    public int mQuantity;
    public double mPrice;

    public ProductsDatabase database;
    public ProductDao productDao;

    Intent intent = null;

    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private final int GALLERY_REQUEST_CODE = 1;
    private final int CAMERA_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        database = ProductsDatabase.getInMemoryDatabase(this);
        productDao = database.productDao();

        try {
            intent = getIntent();
            getProductInfoFromIntent();
            setViewsText();
        } catch (NullPointerException e) {
            Log.i(LOG_TAG, "No intent that has started this activity");
            intent = null;
        }

        ImageView productImageView = findViewById(R.id.imageViewEditor);
        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditorActivity.this);
                builder.setTitle(R.string.select_action)
                        .setItems(R.array.actions, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:
                                        requestGalleryPermission();
                                        pickImageFromGallery();
                                        break;
                                    case 1:
                                        //requestCameraPermission();
                                        dispatchTakePictureIntent();
                                        break;
                                }
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void requestGalleryPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(EditorActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(getApplicationContext(), "Give the app appropriate permissions in your phone settings", Toast.LENGTH_LONG).show();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(EditorActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            }
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }
    }


    private void requestCameraPermission() {
        //TODO implement this method
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            productImageView = findViewById(R.id.imageViewEditor);
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImageUri = data.getData();
                    productImageView.setImageURI(selectedImageUri);
                    break;
                case CAMERA_REQUEST_CODE:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    productImageView.setImageBitmap(imageBitmap);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Could not get image from Gallery", Toast.LENGTH_LONG).show();
                }
        }
    }

    private void pickImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
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
                return true;
            case R.id.action_delete:
                deletePet();
                return true;
            default:
                return false;
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
            }
            return null;
        }

    }
}
