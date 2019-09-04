package com.example.shopinventory;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    /** Log tag for all log messages */
    public static final String LOG_TAG = ProductAdapter.class.getSimpleName();

    public ProductAdapter(Context context, List<Product> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Product currentProduct = getItem(position);
        Log.i(LOG_TAG, currentProduct.toString());

        if (currentProduct.get_id() != 0) {
            TextView nameTextView = listItemView.findViewById(R.id.nameTextView);
            nameTextView.setText(currentProduct.getName());

            TextView priceTextView = listItemView.findViewById(R.id.priceTextView);
            priceTextView.setText(String.valueOf(currentProduct.getPrice()));

            TextView availabilityTextView = listItemView.findViewById(R.id.availabilityTextView);
            availabilityTextView.setText(currentProduct.getQuantity());

            ImageView productImageView = listItemView.findViewById(R.id.productImageView);
            productImageView.setImageURI(Uri.parse(currentProduct.getImageUri()));
        }


        return listItemView;
    }
}
