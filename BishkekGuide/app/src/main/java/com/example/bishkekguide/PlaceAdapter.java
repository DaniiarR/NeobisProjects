package com.example.bishkekguide;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

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

        final Place currentPlace = getItem(position);
        // set the name of the place
        TextView placeNameTextView = listItemView.findViewById(R.id.place_name);
        placeNameTextView.setText(currentPlace.getName());
        // set the district of the place
        TextView placeDistrictTextView = listItemView.findViewById(R.id.place_district);
        placeDistrictTextView.setText(currentPlace.getDistrict());
        // set the photo of the place
        ImageView placeImageView = listItemView.findViewById(R.id.place_photo);
        placeImageView.setImageResource(currentPlace.getImageResourceId());
        // set the description of the place
        TextView onClickDescription = listItemView.findViewById(R.id.onClick_description);
        onClickDescription.setText(currentPlace.getDescriptionLong());
        // set the location of the place
        TextView onClickLocation = listItemView.findViewById(R.id.onClick_location);
        SpannableString locationSpannable = new SpannableString("Location: " + currentPlace.getLocation());
        locationSpannable.setSpan(new UnderlineSpan(), 10, locationSpannable.length(), 0);
        onClickLocation.setText(locationSpannable);
        onClickLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentPlace.getLocationForParsing()));
                getContext().startActivity(mapIntent);
            }
        });
        // set the phone number
        if (currentPlace.getPhoneNumber() != null) {
            TextView phoneNumberTextView = listItemView.findViewById(R.id.call_textView);
            final String phoneNumber = currentPlace.getPhoneNumber();
            SpannableString numberSpannable = new SpannableString("+" + phoneNumber.substring(4, 7) + " " + phoneNumber.substring(7, 10) + " " +
                                                                    phoneNumber.substring(10, 13) + " " + phoneNumber.substring(13));
            numberSpannable.setSpan(new UnderlineSpan(), 0, numberSpannable.length(), 0);
            phoneNumberTextView.setText(numberSpannable);
            phoneNumberTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                    getContext().startActivity(callIntent);
                }
            });
        }

        return listItemView;
    }
}
