package com.example.shopinventory.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shopinventory.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Insert
    long addProduct(Product product);

    @Query("UPDATE Product SET name = :name, quantity = :quantity, price = :price, imageUri = :imageUri, _id = :id")
    void updateProduct(String name, int quantity, double price, String imageUri, long id);

    @Query("DELETE FROM Product")
    void deleteAllProducts();

    @Query("DELETE FROM Product WHERE _id = :id")
    void deleteProduct(int id);

    @Delete
    long deleteProduct(Product product);
}
