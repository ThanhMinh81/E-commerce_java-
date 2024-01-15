package com.example.appecommercephp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ItemPaymentBinding;
import com.example.appecommercephp.model.ItemCartModel;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

     ItemPaymentBinding itemPaymentBinding ;
    private Context context ;
    ArrayList<ItemCartModel> itemCartModels ;


    public PaymentAdapter(Context context, ArrayList<ItemCartModel> itemCartModels) {
        this.context = context;
        this.itemCartModels = itemCartModels;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemPaymentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_payment,parent,false);
        return new  ViewHolder(itemPaymentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {

        ItemCartModel itemCartModel = itemCartModels.get(position);

        holder.itemPaymentBinding.setCartItem(itemCartModel);



    }

    @Override
    public int getItemCount() {

        return itemCartModels.size() ;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemPaymentBinding itemPaymentBinding ;
        public ViewHolder(ItemPaymentBinding itemPaymentBinding ) {

            super(itemPaymentBinding.getRoot());

            this.itemPaymentBinding = itemPaymentBinding ;

        }
    }
}
