package com.example.appecommercephp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.R;
import com.example.appecommercephp.model.ProductsData;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {



    private ArrayList<ProductsData> productsData ;
    private HomeFragment.ClickToDetails clickToDetails ;
    public MainAdapter(ArrayList<ProductsData> productsData , HomeFragment.ClickToDetails clickToDetails) {
        this.productsData = productsData;
        this.clickToDetails = clickToDetails ;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        com.example.appecommercephp.databinding.ItemProductBinding itemProductBinding ;
        itemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_product,parent,false);

        return new ViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {

        ProductsData productsData1 = productsData.get(position);
        holder.itemProductBinding.setProducts(productsData1);

        holder.itemProductBinding.imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickToDetails.Click(productsData1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        com.example.appecommercephp.databinding.ItemProductBinding itemProductBinding ;


        public ViewHolder(com.example.appecommercephp.databinding.ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding ;
        }
    }
}
