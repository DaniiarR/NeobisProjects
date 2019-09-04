package com.example.shopinventory;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.shopinventory.data.ProductDao;
import com.example.shopinventory.data.ProductsDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private ProductsDatabase database;
    private ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        GetProductsFromDatabaseAsyncTask task = new GetProductsFromDatabaseAsyncTask();
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetProductsFromDatabaseAsyncTask extends AsyncTask<Void, Void, List<Product>> {

        @Override
        protected List<Product> doInBackground(Void... voids) {
            database = ProductsDatabase.getInMemoryDatabase(getApplicationContext());
            productDao = database.productDao();

            List<Product> products = productDao.getAllProducts();
            adapter = new ProductAdapter(getApplicationContext(), products);

            return products;
        }

        @Override
        protected void onPostExecute(final List<Product> products) {
            super.onPostExecute(products);

            ListView listView = findViewById(R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                    intent.putExtra("product", products.get(i));

                    startActivity(intent);
                }
            });
        }
    }
}
