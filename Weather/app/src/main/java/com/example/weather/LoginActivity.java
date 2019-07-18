package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    public static final String LOG_TAG = LoginActivity.class.getName();
    public static final String REGIONS_URL = "http://dataservice.accuweather.com/locations/v1/regions?apikey=fZN3MX3gULInTIaPpzzzYZKFXcJgBORP";
    ArrayList<String> regionsList = new ArrayList<>();
    ArrayList<String> countriesList = new ArrayList<>();
    ArrayList<String> citiesList;
    private FusedLocationProviderClient fusedLocationClient;
    double locationLatitude;
    double locationLongtitude;
    Spinner regionsSpinner;
    Spinner countriesSpinner;
    String provider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // add functionality to GPS button
        ImageView locationImageView = findViewById(R.id.get_location);
        locationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button is clicked", Toast.LENGTH_LONG).show();
                LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
                boolean enabled = service
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);
                // check if enabled and if not send user to the GSP settings
                // Better solution would be to display a dialog and suggesting to
                // go to the settings
                if (!enabled) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
                Criteria criteria = new Criteria();
                provider = service.getBestProvider(criteria, false);
                try {
                    Location location = service.getLastKnownLocation(provider);
                } catch (SecurityException e ) {
                    Toast.makeText(getApplicationContext(), "Permossion denied", Toast.LENGTH_LONG).show();
                }
            }
        });

        // create new RequestQueue for Volley and add Regions JSON request
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(makeRegionsArrayRequest(REGIONS_URL));

        regionsSpinner = findViewById(R.id.spinner);

        regionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int check = 0;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (++check > 1) {
                    Log.e(LOG_TAG, "Spinner1's onItemSelected method is being executed");
                    countriesList.clear();
                    citiesList.clear();
                    JsonArrayRequest request = null;
                    //switch (regionsSpinner.getSelectedItem().toString()) {
                    switch (regionsSpinner.getSelectedItem().toString()) {
                        case "Africa":
                            request = makeCountriesArrayRequest("AFR");
                            break;
                        case "Antarctica":
                            request = makeCountriesArrayRequest("ANT");
                            break;
                        case "Arctic":
                            request = makeCountriesArrayRequest("ARC");
                            break;
                        case "Asia":
                            request = makeCountriesArrayRequest("ASI");
                            break;
                        case "Central America":
                            request = makeCountriesArrayRequest("CAC");
                            break;
                        case "Europe":
                            request = makeCountriesArrayRequest("EUR");
                            break;
                        case "Middle East":
                            request = makeCountriesArrayRequest("MEA");
                            break;
                        case "North America":
                            request = makeCountriesArrayRequest("NAM");
                            break;
                        case "Oceania":
                            request = makeCountriesArrayRequest("OCN");
                            break;
                        case "South America":
                            request = makeCountriesArrayRequest("SAM");
                            break;
                    }
                    queue.add(request);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(LOG_TAG, "No item has been selected");
            }
        });
    }

    private JsonArrayRequest makeRegionsArrayRequest(String url) {
        return new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i(LOG_TAG, "Got the response, will try to parse it");
                regionsList = parseRegionsJsonData(response);
                ArrayAdapter<String> regionsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, regionsList);
                regionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                regionsSpinner = findViewById(R.id.spinner);
                regionsSpinner.setAdapter(regionsAdapter);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Could not perform jsonArrayRequest");
            }
        });
    }

    private JsonArrayRequest makeCountriesArrayRequest(String id) {
        return new JsonArrayRequest(
                Request.Method.GET,
                "http://dataservice.accuweather.com/locations/v1/countries/" + id + "?apikey=fZN3MX3gULInTIaPpzzzYZKFXcJgBORP",
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i(LOG_TAG, "Got the response, will try to parse it");
                countriesList = parseCountriesJsonData(response);
                ArrayAdapter<String> countriesAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, countriesList);
                countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                countriesSpinner = findViewById(R.id.spinner2);
                countriesSpinner.setAdapter(countriesAdapter);
                countriesAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Could not perform jsonArrayRequest");
            }
        });
    }

    public ArrayList<String> parseRegionsJsonData(JSONArray regions) {
        try {
            for (int i = 0; i < regions.length(); i++) {
                JSONObject region = regions.getJSONObject(i);
                String regionString = region.getString("LocalizedName");
                Log.i(LOG_TAG, regionString);
                regionsList.add(regionString);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Could not extract REGIONS from JSON");
        }
        return regionsList;
    }

    public ArrayList<String> parseCountriesJsonData(JSONArray countries) {
        // Delete all the elements from citiesList
        citiesList.clear();

        try {
            for (int i = 0; i < countries.length(); i++) {
                JSONObject country = countries.getJSONObject(i);
                String name = country.getString("LocalizedName");
                Log.i(LOG_TAG, name);
                countriesList.add(name);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Could not extract COUNTRIES from JSON");
        }

        return countriesList;
    }
}
