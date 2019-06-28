package com.example.bishkekguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Place> {

    public PlaceAdapter(Context context, ArrayList<Place> placesList) {
        super(context, 0,0, placesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        Place currentPlace = getItem(position);
        // set the name of the place
        TextView placeNameTextView = listItemView.findViewById(R.id.place_name);
        placeNameTextView.setText(currentPlace.getName());
        // set the district of the place
        TextView placeDistrictTextView = listItemView.findViewById(R.id.place_district);
        placeDistrictTextView.setText(currentPlace.getDistrict());
        // set the photo of the place
        ImageView placeImageView = listItemView.findViewById(R.id.place_photo);
        placeImageView.setImageResource(currentPlace.getImageResourceId());

        return listItemView;
    }
}
