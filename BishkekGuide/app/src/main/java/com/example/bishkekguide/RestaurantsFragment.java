package com.example.bishkekguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {

    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.place_list_view, container, false);

        final ArrayList<Place> placesList = new ArrayList<>();
        placesList.add(new Place("Frunze restaurant",
                                "Okryabr",
                         "Cuisine: Asian, European, Japanese, Eastern \nAverage fee: 1500 - 2000 soms \n" +
                                        "Working time: 10:00 - 23:00 (M - S)",
                               "Abdymomunova st. 220A / Erkindik blvd.",
                                        R.drawable.frunze_restaurant,
                        "42.879106,74.606954",
                            "996551664466"));
        placesList.add(new Place("Arzu restaurant",
                                "Alamedin",
                        "Cuisine: Asian, Eastern, European \nAverage fee: 600 - 800 soms \n" +
                                        "Working time: 09:00 - 00:00 (M - S)",
                                "Pobedy ave. 311",
                                        R.drawable.arzu_restaurant,
                        "42.878388,74.596515",
                            "996558581876"));
        placesList.add(new Place("Assorti Buhara",
                                "Oktyabr",
                         "Cuisine: European, Kyrgyz, Korean, Japanese \nAverage fee: 400 - 600 soms \n" +
                                        "Working time: 11:00 - 00:00 (M - S)",
                                "Ibraimova st. 105 / Frunze st.",
                                        R.drawable.assorti_buhara,
                        "42.884861,74.616611",
                           "996555300200"));
        placesList.add(new Place("Obama Bar & Grill",
                                "Oktyabr",
                         "Cuisine: American, Mexican \nAverage fee: 400 - 600 soms \n" +
                                        "Working time: 11:00 - 00:00 (M - S)",
                                "Toktogula st. 93 / Tynystanova",
                                         R.drawable.obama_restaurant,
                        "42.872155,74.607655",
                           "996778685002"));


        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesList);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = placesList.get(position);
                TextView descriptionTextView = view.findViewById(R.id.place_district);
                TextView nameTextView = view.findViewById(R.id.place_name);
                RelativeLayout descriptionLayout = view.findViewById(R.id.description_relativeLayout);
                ImageView callImageView = view.findViewById(R.id.call_imageView);
                TextView callTextView = view.findViewById(R.id.call_textView);

                if (place.isOpen) {
                    // make the description invisible
                    descriptionLayout.setVisibility(View.GONE);
                    // return the name text state to normal
                    nameTextView.setGravity(Gravity.BOTTOM);
                    nameTextView.setTextSize(16);
                    // make the districtTextView visible again
                    descriptionTextView.setVisibility(View.VISIBLE);

                    place.isOpen = false;

                } else if (!place.isOpen) {
                    // Make the districtTextView invisible
                    descriptionTextView.setVisibility(View.GONE);
                    // Change the gravity and the textSize of the nameTextView
                    nameTextView = view.findViewById(R.id.place_name);
                    nameTextView.setGravity(Gravity.CENTER);
                    nameTextView.setTextSize(24);
                    // make the actual description visible
                    descriptionLayout.setVisibility(View.VISIBLE);
                    // make the phone number visible
                    callImageView.setVisibility(View.VISIBLE);
                    callTextView.setVisibility(View.VISIBLE);

                    place.isOpen = true;
                }
            }
        });
        return rootView;
    }

}
