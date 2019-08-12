package com.example.bishkekguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonumentsFragment extends Fragment {

    //private boolean isOpen = false;

    public MonumentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Put our listView inside a container (Linear Layout) and set it as the rootView for the Fragment
        final View rootView = inflater.inflate(R.layout.place_list_view, container, false);

        final ArrayList<Place> placesList = new ArrayList<>();
        placesList.add(new Place("Manas's monument",
                                "Oktyabr",
                         "The Epic of Manas is perhaps the most famous part of Kyrgyz culture, " +
                "and is (arguably) the longest epic poem in the world. At 20 times longer than the Odyssey, this epic tells the life of Manas, an epic warrior, and his son and grandson. " +
                "The original tale was passed orally from performer to performer, who were known as manaschi. The tale was written down starting in the 1800s, and the first complete version was published in the 1920s. " +
                "The Epic of Manas has since been translated into many different languages, and published in the Soviet Union and abroad.",
                              "Bishkek, Ala-Too Square",
                                        R.drawable.manas,
                       "42.876476,74.603726"));
        placesList.add(new Place("Lenin's monument",
                                "Oktyabr",
                         "Vladimir Lenin, also called Vladimir Ilich Lenin, original name Vladimir Ilich Ulyanov, (born April 10 [April 22, New Style], 1870, Simbirsk, Russia—died January 21, 1924, Gorki [later Gorki Leninskiye], near Moscow), " +
                "founder of the Russian Communist Party (Bolsheviks), inspirer and leader of the Bolshevik Revolution (1917), and the architect, builder, and first head (1917–24) of the Soviet state. He was the founder of the organization known as Comintern (Communist International) and the posthumous source of “Leninism,” " +
                "the doctrine codified and conjoined with Karl Marx’s works by Lenin’s successors to form Marxism-Leninism, which became the Communist worldview.",
                              "Bishkek, Former AUCA building",
                                       R.drawable.lenin,
                "42.878630,74.603869"));
        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesList);
        final ListView listView = rootView.findViewById(R.id.list);
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
