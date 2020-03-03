package com.omidrayaneh.mvvm;

import android.content.Intent;
import android.os.Bundle;

import com.omidrayaneh.mvvm.adapter.ProductAdapter;
import com.omidrayaneh.mvvm.databinding.ActivityMainBinding;
import com.omidrayaneh.mvvm.model.Category;
import com.omidrayaneh.mvvm.model.Product;
import com.omidrayaneh.mvvm.viewmodel.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoriesList;
    private ArrayList<Product> productList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    private Category selectedCategory;
    private RecyclerView productRecyclerView;
    private ProductAdapter adapter;

    private int selectedProductId;

    public static final int ADD_PRODUCT_REQUEST_CODE=1;
    public static final int EDIT_PRODUCT_REQUEST_CODE=2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        handlers=new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {

                categoriesList=(ArrayList<Category>)categories;
                for(Category c:categories){

                    Log.i("MyTAG",c.getCategoryName());
                }
                showOnSpinner();
            }
        });



    }

    private void showOnSpinner(){

        ArrayAdapter<Category> categoryArrayAdapter=new ArrayAdapter<Category>(this,R.layout.spinner_item,categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    private void LoadProductArraylist(int categoryId){
        mainActivityViewModel.getProductsOfASelectedCategory(categoryId).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
               productList=(ArrayList<Product>)products;
               LoadRecyclerView();
            }
        });
    }

    private void LoadRecyclerView(){
        productRecyclerView=activityMainBinding.secondaryLayout.rvProducts;
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setHasFixedSize(true);
        adapter=new ProductAdapter();
        productRecyclerView.setAdapter(adapter);
        adapter.setProducts(productList);

        adapter.setListener(new ProductAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(Product product) {
                selectedProductId =product.getProductId();
                Intent intent= new Intent(MainActivity.this,AddEditActivity.class);
                intent.putExtra(AddEditActivity.PRODUCT_ID,selectedProductId);
                intent.putExtra(AddEditActivity.PRODUCT_NAME,product.getProductName());
                intent.putExtra(AddEditActivity.UNIT_PRICE,product.getUnitPrice());
                startActivityForResult(intent,EDIT_PRODUCT_REQUEST_CODE);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Product deleteProduct=productList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteProduct(deleteProduct);
            }
        }).attachToRecyclerView(productRecyclerView);




    }

    //Floating action button clicked
    public class MainActivityClickHandlers{
        public void FBAClicked(View view){
            //Toast.makeText(getApplicationContext(),"FBA Clicked",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,AddEditActivity.class);
            startActivityForResult(intent,ADD_PRODUCT_REQUEST_CODE);

        }
        public void onSelectedItem(AdapterView<?> parent,View view,int pos,long id){
            selectedCategory=(Category)parent.getItemAtPosition(pos);

            String message = "id is " + selectedCategory.getId()+ "\n name is " +selectedCategory.getCategoryName();

          //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

            LoadProductArraylist(selectedCategory.getId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedCategoryId=selectedCategory.getId();

        if (requestCode==ADD_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK ){

            Product product=new Product();
            product.setProductCategoryId(selectedCategoryId);
            product.setProductName(data.getStringExtra(AddEditActivity.PRODUCT_NAME));
            product.setUnitPrice(data.getStringExtra(AddEditActivity.UNIT_PRICE));
            mainActivityViewModel.addNewProduct(product);
            adapter.notifyDataSetChanged();

        }else if(requestCode==EDIT_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK){
            Product product=new Product();
            product.setProductCategoryId(selectedCategoryId);
            product.setProductName(data.getStringExtra(AddEditActivity.PRODUCT_NAME));
            product.setUnitPrice(data.getStringExtra(AddEditActivity.UNIT_PRICE));
            product.setProductId(selectedProductId);
            mainActivityViewModel.updateProduct(product);
        }
    }
}
