package com.example.bishkekguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

        ArrayList<Place> placesList = new ArrayList<>();
        placesList.add(new Place("Ata-Turk", "Lenin", "One of the best parks in Bishkek", true,
                                        "Bishkek, Akhunbaeva/Maldybaeva", R.drawable.ataturk));
        placesList.add(new Place("Panfilova", "Oktyabr", "A big park located right in the middle of Bishkek", true,
                                        "Bishkek, Frunze/Togolok Moldo", R.drawable.panfilova));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesList);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }

}
