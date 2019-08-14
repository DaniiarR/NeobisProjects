package com.example.shopinventory;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Insert
    long addProduct(Product product);

    @Query("UPDATE Product SET name = :name, quantity = :quantity, price = :price, imageUri = :imageUri")
    void updateProduct(String name, int quantity, double price, String imageUri);

    @Query("DELETE FROM Product")
    void deleteAllProducts();

    @Query("DELETE FROM Product WHERE _id = :id")
    void deleteProduct(int id);
}
