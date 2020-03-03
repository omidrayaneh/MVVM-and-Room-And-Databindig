package com.omidrayaneh.mvvm.model;



import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyApplicationRepository {

    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private LiveData<List<Category>> categories;
    private LiveData<List<Product>> products;

    public MyApplicationRepository(Application application) {

        ProductsDatabase productsDatabase=ProductsDatabase.getInstance(application);
        categoryDAO=productsDatabase.categoryDAO();
        productDAO=productsDatabase.productDAO();

    }
    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Product>> getProducts(int categoryId) {
        return productDAO.getProducts(categoryId);
    }

    public void insertCategory(Category category){

        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Category,Void,Void> {
        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.insert(categories[0]);
            return null;
        }
    }

    public void insertProduct(Product product){

        new InsertProductAsyncTask(productDAO).execute(product);
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDAO productDAO;

        public InsertProductAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {

            productDAO.insert(products[0]);
            return null;
        }
    }

    public void deleteCategory(Category category){

        new DeleteCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category,Void,Void>{
        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.delete(categories[0]);
            return null;
        }
    }

    public void deleteProduct(Product product){

        new DeleteProductAsyncTask(productDAO).execute(product);
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDAO productDAO;

        public DeleteProductAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {

            productDAO.delete(products[0]);
            return null;
        }
    }

    public void updateCategory(Category category){

        new UpdateCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category,Void,Void>{
        private CategoryDAO categoryDAO;

        public UpdateCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.update(categories[0]);
            return null;
        }
    }

    public void updateProduct(Product product){

        new UpdateProductAsyncTask(productDAO).execute(product);
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDAO productDAO;

        public UpdateProductAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {

            productDAO.update(products[0]);
            return null;
        }
    }


}
