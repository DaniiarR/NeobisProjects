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
public class MonumentsFragment extends Fragment {


    public MonumentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Put our listView inside a container (Linear Layout) and set it as the rootView for the Fragment
        View rootView = inflater.inflate(R.layout.place_list_view, container, false);

        ArrayList<Place> placesList = new ArrayList<>();
        placesList.add(new Place("Manas",
                                "Oktyabr",
                         "The Epic of Manas is perhaps the most famous part of Kyrgyz culture, " +
                "and is (arguably) the longest epic poem in the world. At 20 times longer than the Odyssey, this epic tells the life of Manas, an epic warrior, and his son and grandson. " +
                "The original tale was passed orally from performer to performer, who were known as manaschi. The tale was written down starting in the 1800s, and the first complete version was published in the 1920s. " +
                "The Epic of Manas has since been translated into many different languages, and published in the Soviet Union and abroad.",
                                true,
                              "Bishkek, Ala-Too Square",
                                        R.drawable.manas));
        placesList.add(new Place("Lenin",
                                "Oktyabr",
                         "Vladimir Lenin, also called Vladimir Ilich Lenin, original name Vladimir Ilich Ulyanov, (born April 10 [April 22, New Style], 1870, Simbirsk, Russia—died January 21, 1924, Gorki [later Gorki Leninskiye], near Moscow), " +
                "founder of the Russian Communist Party (Bolsheviks), inspirer and leader of the Bolshevik Revolution (1917), and the architect, builder, and first head (1917–24) of the Soviet state. He was the founder of the organization known as Comintern (Communist International) and the posthumous source of “Leninism,” " +
                "the doctrine codified and conjoined with Karl Marx’s works by Lenin’s successors to form Marxism-Leninism, which became the Communist worldview.",
                                true,
                              "Bishkek, Old AUCA building",
                                       R.drawable.lenin));
        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesList);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }
}
