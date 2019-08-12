package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivitySimple extends AppCompatActivity {

    public static final String LOG_TAG = LoginActivity.class.getName();
    public String cityUrl = "http://dataservice.accuweather.com/locations/v1/cities/autocomplete?apikey=fZN3MX3gULInTIaPpzzzYZKFXcJgBORP&q=";
    Spinner citiesSpinner;
    ArrayList<String> citiesList = new ArrayList<>();
    Map map = new HashMap();

    private LocationManager locationManager;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple);

        final RequestQueue queue = Volley.newRequestQueue(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        try {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                Log.i(LOG_TAG, "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
                                Log.i(LOG_TAG, String.valueOf(location.getLatitude()));
                                Log.i(LOG_TAG, String.valueOf(location.getLongitude()));
                            } else {
                                Log.e(LOG_TAG, "location object is null");
                            }
                        }
                    });
        } catch (SecurityException e) {
            Log.e(LOG_TAG, "Securite exception occured");
        }

        final EditText editCityName = findViewById(R.id.editText);
        editCityName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == KeyEvent.KEYCODE_BACK) {
                    queue.add(populateSpinner(cityUrl + editCityName.getText().toString()));
                    return true;
                }
                return false;
            }
        });

        Button goButton = findViewById(R.id.button2);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivitySimple.this, MainActivity.class);
                citiesSpinner = findViewById(R.id.spinner4);
                String cityNameForKey = citiesSpinner.getSelectedItem().toString().substring(0, citiesSpinner.getSelectedItem().toString().indexOf(","));
                Log.i(LOG_TAG, cityNameForKey);
                intent.putExtra("cityKey", String.valueOf(map.get(cityNameForKey)));
                intent.putExtra("cityName", cityNameForKey);
                startActivity(intent);
            }
        });

        ImageView getLocationButton = findViewById(R.id.location);
        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private JsonArrayRequest populateSpinner(String url) {
        return new JsonArrayRequest(
                Request.Method.GET,
                url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i(LOG_TAG, "Got the response, will try to parse it");
                citiesList = parseCitiesJsonData(response);
                ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, citiesList);
                citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                citiesSpinner = findViewById(R.id.spinner4);
                citiesSpinner.setAdapter(citiesAdapter);
                citiesAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Could not perform jsonArrayRequest");
            }
        });
    }

    private ArrayList<String> parseCitiesJsonData(JSONArray response) {
        try {
            citiesList.clear();

            for (int i = 0; i < response.length(); i++) {
                JSONObject city = response.getJSONObject(i);
                String cityName = city.getString("LocalizedName");
                String countryName = city.getJSONObject("Country").getString("LocalizedName");
                String key = city.getString("Key");
                citiesList.add(String.format("%s, %s", cityName, countryName));
                Log.i(LOG_TAG, String.format("%s, %s", cityName, countryName));
                map.put(cityName, key);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Could not extract cities from JSON");
        }

        return citiesList;
    }
}
