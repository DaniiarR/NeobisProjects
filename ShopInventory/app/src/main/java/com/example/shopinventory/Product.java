package com.example.shopinventory;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    private String name;

    private int quantity;

    private double price;

    private String imageUri;


    public Product(String name, int quantity, double price, String imageUri) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imageUri = imageUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %f", getName(), getQuantity(), getPrice());
    }
}
