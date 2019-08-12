package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DayAdapter extends ArrayAdapter<Day> {

    public DayAdapter(Context context, ArrayList<Day> daysList) {
        super(context, 0, 0, daysList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Day currentDay = getItem(position);

        // set city name
        TextView cityNameTextView = listItemView.findViewById(R.id.textView8);
        cityNameTextView.setText(MainActivity.cityName);

        // set current date
        TextView dateTextView = listItemView.findViewById(R.id.date_textView);
        dateTextView.setText(currentDay.getDate().substring(0, 10));

        // set sunrise and sunset time
        TextView sunriseTextView = listItemView.findViewById(R.id.sunrise_textView);
        sunriseTextView.setText("Sunrise: " + currentDay.getSunRise().substring(11, 16));
        TextView sunsetTextView = listItemView.findViewById(R.id.sunset_textView);
        sunsetTextView.setText("Sunset: " + currentDay.getSunSet().substring(11, 16));

        // set image icons
        ImageView dayImage = listItemView.findViewById(R.id.day_image);
        Picasso.get().load(currentDay.getDayIcon()).into(dayImage);

        ImageView nightImage = listItemView.findViewById(R.id.night_image);
        Picasso.get().load(currentDay.getNightIcon()).into(nightImage);

        // set icon phrases
        TextView dayIconPhrase = listItemView.findViewById(R.id.day_iconPhrase);
        dayIconPhrase.setText(currentDay.getDayPhrase());

        TextView nightIconPhrase = listItemView.findViewById(R.id.night_iconPhrase);
        nightIconPhrase.setText(currentDay.getNightPhrase());

        // set rain probability
        TextView dayRainProbability = listItemView.findViewById(R.id.day_rain);
        dayRainProbability.setText("Rain probability: " + currentDay.getDayRainProbability() + "%");

        TextView nightRainProbability = listItemView.findViewById(R.id.night_rain);
        nightRainProbability.setText("Rain probability: " + currentDay.getNightRainProbability() + "%");

        // set wind speed and direction
        TextView dayWind = listItemView.findViewById(R.id.day_wind);
        dayWind.setText(String.format("Wind: %.1f KM/H %s", currentDay.getDayWind(), currentDay.getDayWindDirection()));

        TextView nightWind = listItemView.findViewById(R.id.night_wind);
        nightWind.setText(String.format("Wind: %.1f KM/H %s", currentDay.getNightWind(), currentDay.getNightWindDirection()));

        // set temperature
        TextView dayTemperature = listItemView.findViewById(R.id.day_temperature);
        dayTemperature.setText(currentDay.getMaxTemperature() + "°C");

        TextView nightTemperature = listItemView.findViewById(R.id.night_temperature);
        nightTemperature.setText(currentDay.getMinTemperature() + "°C");

        return listItemView;
    }
}
