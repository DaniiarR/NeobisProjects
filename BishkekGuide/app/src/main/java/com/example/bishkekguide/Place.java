package com.example.bishkekguide;

public class Place {

    private String name;
    private String district;
    private String descriptionLong;
    private String location;
    private int imageResourceId;
    private boolean isFree;

    public Place(String name, String district, String descriptionLong,
                 boolean isFree, String location, int imageResourceId) {
        if ()
        this.name = name;
        this.district = district + "district";
        this.descriptionLong = descriptionLong;
        this.isFree = isFree;
        this.location = location;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public String getLocation() {
        return location;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean isFree() {
        return isFree;
    }
}
