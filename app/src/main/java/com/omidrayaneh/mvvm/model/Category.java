package com.omidrayaneh.mvvm.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    private int id;
    @ColumnInfo(name = "category_name")
    private String CategoryName;
    @ColumnInfo(name = "category_description")
    private String CategoryDescription;


    public Category() {
    }

    public Category(int id, String categoryName, String categoryDescription) {
        this.id = id;
        CategoryName = categoryName;
        CategoryDescription = categoryDescription;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Bindable
    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
    @Bindable
    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return this.CategoryName;
    }
}
