package com.omidrayaneh.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omidrayaneh.mvvm.databinding.ActivityAddEditBinding;
import com.omidrayaneh.mvvm.model.Product;

public class AddEditActivity extends AppCompatActivity {

    Product product;
    public static final String PRODUCT_ID="productId";
    public static final String PRODUCT_NAME="productName";
    public static final String UNIT_PRICE="unitPrice";
    private ActivityAddEditBinding activityAddEditBinding;
    private AddEditActivityClickHandlers addEditActivityClickHandlers;
    public static final String ADD_PRODUCT="Add New Product";
    public static final String EDIT_PRODUCT="Edit Product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

         product =new Product();
         activityAddEditBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_edit);
         activityAddEditBinding.setProduct(product);

         addEditActivityClickHandlers=new AddEditActivityClickHandlers(this);
         activityAddEditBinding.setClickHandler(addEditActivityClickHandlers);

        Intent intent=getIntent();

        if (intent.hasExtra(PRODUCT_ID)){
            setTitle(EDIT_PRODUCT);
            product.setProductName(intent.getStringExtra(PRODUCT_NAME));
            product.setUnitPrice(intent.getStringExtra(UNIT_PRICE));

        }
        else {
            setTitle(ADD_PRODUCT);

        }



    }

    public class AddEditActivityClickHandlers{
        Context context;

        private AddEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if (product.getProductName()==null){
                Toast.makeText(context, "Product Name Can`t be null", Toast.LENGTH_SHORT).show();
                return ;
            }
            Intent intent=new Intent();
            intent.putExtra(PRODUCT_NAME,product.getProductName());
            intent.putExtra(UNIT_PRICE,product.getUnitPrice());
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
