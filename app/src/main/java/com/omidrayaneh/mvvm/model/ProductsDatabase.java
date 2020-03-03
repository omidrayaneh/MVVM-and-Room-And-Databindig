package com.omidrayaneh.mvvm.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class,Product.class},version = 1,exportSchema = false)
public abstract class ProductsDatabase extends RoomDatabase {
    public abstract CategoryDAO categoryDAO();
    public abstract ProductDAO productDAO();

    private static ProductsDatabase instance;
    public static synchronized ProductsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductsDatabase.class, "products_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };


    private static class InitialDataAsyncTask extends AsyncTask<Void,Void,Void> {
        private CategoryDAO categoryDAO;
        private ProductDAO productDAO;

        public InitialDataAsyncTask(ProductsDatabase productsDatabase) {

            categoryDAO=productsDatabase.categoryDAO();
            productDAO =productsDatabase.productDAO();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            Category category1=new Category();
            category1.setCategoryName("Text products");
            category1.setCategoryDescription("Text products Description");

            Category category2=new Category();
            category2.setCategoryName("Novels");
            category2.setCategoryDescription("Novels Description");

            Category category3=new Category();
            category3.setCategoryName("Other products");
            category3.setCategoryDescription("Text products Description");

            categoryDAO.insert(category1);
            categoryDAO.insert(category2);
            categoryDAO.insert(category3);

            Product product1=new Product();
            product1.setProductName("High school Java ");
            product1.setUnitPrice("$150");
            product1.setProductCategoryId(1);

            Product product2=new Product();
            product2.setProductName("Mathematics for beginners");
            product2.setUnitPrice("$200");
            product2.setProductCategoryId(1);

            Product product3=new Product();
            product3.setProductName("Object Oriented Androd App Design");
            product3.setUnitPrice("$150");
            product3.setProductCategoryId(1);

            Product product4=new Product();
            product4.setProductName("Astrology for beginners");
            product4.setUnitPrice("$190");
            product4.setProductCategoryId(1);

            Product product5=new Product();
            product5.setProductName("High school Magic Tricks ");
            product5.setUnitPrice("$150");
            product5.setProductCategoryId(1);

            Product product6=new Product();
            product6.setProductName("Chemistry  for secondary school students");
            product6.setUnitPrice("$250");
            product6.setProductCategoryId(1);

            Product product7=new Product();
            product7.setProductName("A Game of Cats");
            product7.setUnitPrice("$19.99");
            product7.setProductCategoryId(2);

            Product product8=new Product();
            product8.setProductName("The Hound of the New York");
            product8.setUnitPrice("$16.99");
            product8.setProductCategoryId(2);

            Product product9=new Product();
            product9.setProductName("Adventures of Joe Finn");
            product9.setUnitPrice("$13");
            product9.setProductCategoryId(2);

            Product product10=new Product();
            product10.setProductName("Arc of witches");
            product10.setUnitPrice("$19.99");
            product10.setProductCategoryId(2);

            Product product11=new Product();
            product11.setProductName("Can I run");
            product11.setUnitPrice("$16.99");
            product11.setProductCategoryId(2);

            Product product12=new Product();
            product12.setProductName("Story of a joker");
            product12.setUnitPrice("$13");
            product12.setProductCategoryId(2);

            Product product13=new Product();
            product13.setProductName("Notes of a alien life cycle researcher");
            product13.setUnitPrice("$1250");
            product13.setProductCategoryId(3);

            Product product14=new Product();
            product14.setProductName("Top 9 myths abut UFOs");
            product14.setUnitPrice("$789");
            product14.setProductCategoryId(3);

            Product product15=new Product();
            product15.setProductName("How to become a millionaire in 24 hours");
            product15.setUnitPrice("$1250");
            product15.setProductCategoryId(3);

            Product product16=new Product();
            product16.setProductName("1 hour work month");
            product16.setUnitPrice("$199");
            product16.setProductCategoryId(3);

            productDAO.insert(product1);
            productDAO.insert(product2);
            productDAO.insert(product3);
            productDAO.insert(product4);
            productDAO.insert(product5);
            productDAO.insert(product6);
            productDAO.insert(product7);
            productDAO.insert(product8);
            productDAO.insert(product9);
            productDAO.insert(product10);
            productDAO.insert(product11);
            productDAO.insert(product12);
            productDAO.insert(product13);
            productDAO.insert(product14);
            productDAO.insert(product15);
            productDAO.insert(product16);

            return null;
        }
    }
}
