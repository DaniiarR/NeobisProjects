package com.example.bishkekguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParksFragment extends Fragment {

    public ParksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list_view, container, false);

        final ArrayList<Place> placesList = new ArrayList<>();
        placesList.add(new Place("Ata-Turk", "Lenin", "One of the best parks in Bishkek",
                                        "Bishkek, Akhunbaeva/Maldybaeva", R.drawable.ataturk, "42.839486,74.595389"));
        placesList.add(new Place("Panfilova", "Oktyabr", "A big park located right in the middle of Bishkek",
                                        "Bishkek, Frunze/Togolok Moldo", R.drawable.panfilova, "42.879672,74.600131"));
        placesList.add(new Place("Southern gates", "Lenin", "The best park in the south of Bishkek",
                                "Bishkek, Masalieva / Sovetskaya", R.drawable.southern_gates_park, "42.826472,74.604131"));
        placesList.add(new Place("Flamingo", "Lenin", "Best park for kids",
                                "Mira ave./Imanalieva", R.drawable.flamingo_park, "42.805058,74.579273"));
//        placesList.add(new Place("Asanbay", "Oktyabr", "A perfect park for romantic walks with your girlfriend",
//                                 "Masalieve/Suhe-Batora", R.drawable.asanbay_park, "42.818815, 74.619128"));
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
                if (place.isOpen) {
                    // make the description invisible
//                    onClickDescription.setVisibility(View.GONE);
//                    onClickLocation.setVisibility(View.GONE);
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
//                    onClickDescription.setVisibility(View.VISIBLE);
//                    onClickLocation.setVisibility(View.VISIBLE);
                    descriptionLayout.setVisibility(View.VISIBLE);

                    place.isOpen = true;
                }
            }
        });
        return rootView;
    }

}
