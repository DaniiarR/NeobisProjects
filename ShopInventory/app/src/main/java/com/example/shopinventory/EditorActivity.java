package com.example.shopinventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shopinventory.data.ProductDao;
import com.example.shopinventory.data.ProductsDatabase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditorActivity extends AppCompatActivity {
    /** Log tag for all the Log messages in this activity */
    public static final String LOG_TAG = EditorActivity.class.getSimpleName();

    /** Our views declared as member variable for easier use in future */
    private ImageView productImageView;
    private EditText nameEditText;
    private EditText quantityEditText;
    private EditText priceEditText;

    /** Every Product's fields. Will be used to create new Products and edit/delete them */
    public long mId;
    public String mName;
    public int mQuantity;
    public double mPrice;
    String mCurrentPhotoPath;

    /** Products database and DAO to access it's objects */
    public ProductsDatabase database;
    public ProductDao productDao;

    /** Intent that might have called this activity */
    Intent intent = null;

    /** Constants for permission */
    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private final int GALLERY_REQUEST_CODE = 1;
    private final int CAMERA_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        database = ProductsDatabase.getInMemoryDatabase(this);
        productDao = database.productDao();
        // Try/catch block for checking if this Activity was launched from MainActivity
        // If so, set all views' texts to match clicked product's properties
        try {
            intent = getIntent();
            getProductInfoFromIntent((Product) intent.getSerializableExtra("product"));
            setViewsText();
        } catch (NullPointerException e) {
            Log.i(LOG_TAG, "No intent that has started this activity");
            // if no intent launched this activity,
            // set the intent variable to null because later in AsyncTask we want to know
            // if we need to update a pet or to create a new one
            intent = null;
        }
        // set onClickListener to the imageView to invoke AlertDialog with 2 options available: gallery and camera
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
                                        break;
                                    case 1:
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
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /** Add current image to gallery */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    /** Crop the image that we got from camera for better performance and set it productImageView */
    private void cropImage() {
        productImageView = findViewById(R.id.imageViewEditor);
        // Get the dimensions of the View
        int targetW = productImageView.getWidth();
        int targetH = productImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        productImageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            productImageView = findViewById(R.id.imageViewEditor);
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImageUri = data.getData();
                    mCurrentPhotoPath = selectedImageUri.toString();
                    cropImage();
                    break;
                case CAMERA_REQUEST_CODE:
                    cropImage();
                    break;
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

    /** Method to launch an intent to choose a picture from gallery */
    private void pickImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    private void getProductInfoFromIntent(Product product) {
        mName = product.getName();
        mPrice = product.getPrice();
        mQuantity = product.getQuantity();
        mCurrentPhotoPath = product.getImageUri();
    }

    /** Set EditTexts' and ImageView's values to the ones we got from intent */
    private void setViewsText() {
        nameEditText.setText(mName);
        quantityEditText.setText(mQuantity);
        priceEditText.setText(String.valueOf(mPrice));
        productImageView.setImageURI(Uri.parse(mCurrentPhotoPath));
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
                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                return true;
            case R.id.action_delete:
                deletePet();
                return true;
            default:
                return false;
        }
    }

    /** Get values from the UI and assign the to mVariables */
    private void savePet() {
        nameEditText = findViewById(R.id.nameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        priceEditText = findViewById(R.id.priceEditText);
        productImageView = findViewById(R.id.imageViewEditor);

        mName = nameEditText.getText().toString();
        mQuantity = Integer.parseInt(quantityEditText.getText().toString());
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
                Product product = new Product(mName, mQuantity, mPrice, mCurrentPhotoPath);
                productDao.addProduct(product);

            } else {
                productDao.updateProduct(mName, mQuantity, mPrice, mCurrentPhotoPath, mId);
            }
            return null;
        }

    }
}
