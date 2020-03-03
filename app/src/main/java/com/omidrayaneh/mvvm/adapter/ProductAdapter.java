package com.omidrayaneh.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.omidrayaneh.mvvm.R;
import com.omidrayaneh.mvvm.databinding.ProductListItemBinding;
import com.omidrayaneh.mvvm.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private OnItemClickListner listener;
    private ArrayList<Product> products=new ArrayList<>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductListItemBinding productListItemBinding= DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.product_list_item,parent,false);
        return new ProductViewHolder(productListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product=products.get(position);
        holder.productListItemBinding.setProduct(product);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        private ProductListItemBinding productListItemBinding;
        public ProductViewHolder(@NonNull final ProductListItemBinding productListItemBinding) {
            super(productListItemBinding.getRoot());
            this.productListItemBinding=productListItemBinding;
            productListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int clickedPosetion=getAdapterPosition();
                    if (listener !=null && clickedPosetion !=RecyclerView.NO_POSITION){
                        listener.onItemClick(products.get(clickedPosetion));
                    }



                }
            });

        }
    }

    public interface OnItemClickListner{
        void onItemClick(Product product);
    }

    public void setListener(OnItemClickListner listener) {
        this.listener = listener;
    }
}
