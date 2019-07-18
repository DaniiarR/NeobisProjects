package com.example.weather;

public class City {

    private String key;
    private String name;
    private String country;

    public City(String key, String name, String country) {
        this.key = key;
        this.name = name;
        this.country = country;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
