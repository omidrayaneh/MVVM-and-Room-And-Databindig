package com.omidrayaneh.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.omidrayaneh.mvvm.model.Category;
import com.omidrayaneh.mvvm.model.MyApplicationRepository;
import com.omidrayaneh.mvvm.model.Product;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MyApplicationRepository myApplicationRepository;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Product>> productsOfASelectedCategory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        myApplicationRepository=new MyApplicationRepository(application);

    }
    public LiveData<List<Category>> getAllCategories() {

        allCategories=myApplicationRepository.getCategories();
        return allCategories;
    }

    public LiveData<List<Product>> getProductsOfASelectedCategory(int categoryId) {

        productsOfASelectedCategory=myApplicationRepository.getProducts(categoryId);
        return productsOfASelectedCategory;
    }

    public void addNewProduct(Product product){
        myApplicationRepository.insertProduct(product);
    }

    public void updateProduct(Product product){
        myApplicationRepository.updateProduct(product);
    }

    public void deleteProduct(Product product){
        myApplicationRepository.deleteProduct(product);
    }

}
