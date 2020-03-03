package com.omidrayaneh.mvvm.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insert(Product product);

    @Update
    void  update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM products_table")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM products_table WHERE productCategoryId==:categoryId")
    LiveData<List<Product>> getProducts(int categoryId);
}
