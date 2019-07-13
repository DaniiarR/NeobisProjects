package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ArrayList<String> regionsList;
    ArrayList<String> countryList;
    ArrayList<String> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}
