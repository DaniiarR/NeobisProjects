package com.example.weather;

import androidx.annotation.NonNull;

public class Day {

    private String date;
    private String sunRise;
    private String sunSet;
    private double minTemperature;
    private double maxTemperature;
    private String dayIcon;
    private String nightIcon;
    private String dayPhrase;
    private String nightPhrase;
    private int dayRainProbability;
    private int nightRainProbability;
    private double dayWind;
    private String dayWindDirection;
    private double nightWind;
    private String nightWindDirection;

    public Day(String date, String sunRise, String sunSet, double minTemperature, double maxTemperature,
               int dayIcon, String dayPhrase, int dayRainProbability, double dayWind, String dayWindDirection,
               int nightIcon, String nightPhrase, int nightRainProbability, double nightWind, String nightWindDirection) {
        this.date = date;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.dayIcon = "https://developer.accuweather.com/sites/default/files/" + dayIcon + "-s.png";
        this.nightIcon = "https://developer.accuweather.com/sites/default/files/" + nightIcon + "-s.png";
        this.dayPhrase = dayPhrase;
        this.nightPhrase = nightPhrase;
        this.dayRainProbability = dayRainProbability;
        this.nightRainProbability = nightRainProbability;
        this.dayWind = dayWind;
        this.nightWind = nightWind;
        this.dayWindDirection = dayWindDirection;
        this.nightWindDirection = nightWindDirection;
    }

    public String getDayWindDirection() {
        return dayWindDirection;
    }

    public String getNightWindDirection() {
        return nightWindDirection;
    }

    public double getDayWind() {
        return dayWind;
    }

    public double getNightWind() {
        return nightWind;
    }

    public String getDate() {
        return date;
    }

    public String getSunRise() {
        return sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public String getDayIcon() {
        return dayIcon;
    }

    public String getNightIcon() {
        return nightIcon;
    }

    public String getDayPhrase() {
        return dayPhrase;
    }

    public String getNightPhrase() {
        return nightPhrase;
    }

    public int getDayRainProbability() {
        return dayRainProbability;
    }

    public int getNightRainProbability() {
        return nightRainProbability;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s %s", getDate(), getSunRise(), getSunSet());
    }
}
