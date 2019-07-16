package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    public static final String TAG = "MyTag";
    public final String WEATHER_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/222844?apikey=fZN3MX3gULInTIaPpzzzYZKFXcJgBORP&details=true&metric=true";
    public ArrayList<Day> daysList = new ArrayList<>();
    RequestQueue queue;
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        queue = Volley.newRequestQueue(this);
        request = makeWeatherRequest(WEATHER_URL);
        request.setTag(TAG);
        queue.add(request);
        ListView listView = findViewById(R.id.list);
        DayAdapter adapter = new DayAdapter(this, daysList);
        listView.setAdapter(adapter);
//        } else {
//            ProgressBar progressBar = (ProgressBar) findViewById(R.id.indeterminateBar);
//            progressBar.setVisibility(View.GONE);
//            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
//        }


        for (int i = 0; i < daysList.size(); i++) {
            Log.i(LOG_TAG, daysList.get(i).getSunRise());
        }

    }

    @Override
    protected void onStop () {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }
    private JsonObjectRequest makeWeatherRequest(String url) {

        return new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i(LOG_TAG, "Got the response, will try to parse it");
                daysList = parseWeatherJsonData(response);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.indeterminateBar);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Could not perform jsonObjectRequest");
            }
        });
    }

    private ArrayList<Day> parseWeatherJsonData(JSONObject response) {
        try {
            JSONArray dailyForecasts = response.getJSONArray("DailyForecasts");
            for (int i = 0; i < dailyForecasts.length(); i++) {
                JSONObject oneDay = dailyForecasts.getJSONObject(i);
                String date = oneDay.getString("Date");

                JSONObject sun = oneDay.getJSONObject("Sun");
                String sunRise = sun.getString("Rise");
                String sunSet = sun.getString("Set");

                double minTemperature = oneDay.getJSONObject("Temperature").getJSONObject("Minimum").getDouble("Value");
                double maxTemperature = oneDay.getJSONObject("Temperature").getJSONObject("Maximum").getDouble("Value");

                JSONObject day = oneDay.getJSONObject("Day");
                int dayIcon = day.getInt("Icon");
                String dayPhrase = day.getString("IconPhrase");
                int dayRainProbability = day.getInt("RainProbability");
                double dayWind = day.getJSONObject("Wind").getJSONObject("Speed").getDouble("Value");
                String dayWindDirection = day.getJSONObject("Wind").getJSONObject("Direction").getString("English");

                JSONObject night = oneDay.getJSONObject("Night");
                int nightIcon = night.getInt("Icon");
                String nightPhrase = night.getString("IconPhrase");
                int nightRainProbability = day.getInt("RainProbability");
                double nightWind = night.getJSONObject("Wind").getJSONObject("Speed").getDouble("Value");
                String nightWindDirection = night.getJSONObject("Wind").getJSONObject("Direction").getString("English");

                daysList.add(new Day(date, sunRise, sunSet, minTemperature, maxTemperature,
                        dayIcon, dayPhrase, dayRainProbability, dayWind, dayWindDirection,
                        nightIcon, nightPhrase, nightRainProbability, nightWind, nightWindDirection));
                Log.i(LOG_TAG, daysList.get(i).toString());
                Log.i(LOG_TAG, "Successfully parsed the JSON data");

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Could not extract weather forecast from JSON");
        }
        return daysList;
    }
}
