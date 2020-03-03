package com.omidrayaneh.mvvm.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "products_table",foreignKeys = @ForeignKey(entity = Category.class
        ,parentColumns = "category_id",childColumns = "productCategoryId"
        ,onDelete = ForeignKey.CASCADE),indices = @Index("productCategoryId"))
public class Product extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")

    private int productId;
    @ColumnInfo(name = "productName")
    private String productName;
    @ColumnInfo(name = "productDescription")

    private String productDescription;
    @ColumnInfo(name = "unitPrice")

    private String unitPrice;
    @ColumnInfo(name = "productQty")

    private String productQty;
    @ColumnInfo(name = "productCategoryId")
    private int productCategoryId;


    public Product(int productId, String productName, String productDescription, String unitPrice, String productQty, int productCategoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.productQty = productQty;
        this.productCategoryId = productCategoryId;
    }

    @Ignore
    public Product() {
    }

    @Bindable

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    @Bindable
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @Bindable
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Bindable
    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }
    @Bindable
    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
}

