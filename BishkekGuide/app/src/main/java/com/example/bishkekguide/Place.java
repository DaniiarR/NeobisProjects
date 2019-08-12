package com.example.bishkekguide;

public class Place {

    private String name;
    private String district;
    private String descriptionLong;
    private String location;
    private int imageResourceId;
    private String locationForParsing;
    private String phoneNumber;
    public boolean isOpen = false;

    /**
     * The constructor for creating a single place in the list
     * @param name name of the place
     * @param district district name (appears under the name)
     * @param descriptionLong description that shows up when the place is clicked
     * @param location location that will be shown to the user
     * @param imageResourceId resource ID for the place e.g R.drawable.place
     * @param locationForParsing location for maps intent (format: "latitude,longitude")"
     */
    public Place(String name, String district, String descriptionLong, String location, int imageResourceId, String locationForParsing) {
        this.name = name;
        this.district = district + " district";
        this.descriptionLong = descriptionLong;
        this.location = location;
        this.imageResourceId = imageResourceId;
        this.locationForParsing = "geo:" + locationForParsing + "?20";
    }

    /**
     * The same constructor but for places that have a phone number
     * @param name name of the place
     * @param district district name (appears under the name)
     * @param descriptionLong description that shows up when the place is clicked
     * @param location location that will be shown to the user
     * @param imageResourceId resource ID for the place e.g R.drawable.place
     * @param locationForParsing location for maps intent (format: "latitude,longitude")"
     * @param phoneNumber phone number of the place (format: "996773438888")
     */
    public Place(String name, String district, String descriptionLong, String location, int imageResourceId, String locationForParsing, String phoneNumber) {
        this.name = name;
        this.district = district + " district";
        this.descriptionLong = descriptionLong;
        this.location = location;
        this.imageResourceId = imageResourceId;
        this.locationForParsing = "geo:" + locationForParsing + "?20";
        this.phoneNumber = "tel:" + phoneNumber;
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

    public String getLocationForParsing() { return locationForParsing; }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
