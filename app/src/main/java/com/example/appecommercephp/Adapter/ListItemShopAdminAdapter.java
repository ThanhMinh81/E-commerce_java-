package com.example.appecommercephp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.databinding.ItemLayoutShopOrderBinding;
import com.example.appecommercephp.interfaceUI.IUpdateState;
import com.example.appecommercephp.model.ItemOrder;

import java.util.List;

public class ListItemShopAdminAdapter extends RecyclerView.Adapter<ListItemShopAdminAdapter.ViewHolder> {

    List<ItemOrder> itemOrders ;

    ItemLayoutShopOrderBinding itemLayoutListOrderBinding ;



    private IUpdateState iUpdateState ;

    public ListItemShopAdminAdapter( List<ItemOrder> itemOrders ,  IUpdateState iUpdateState) {
        this.itemOrders = itemOrders;
        this.iUpdateState = iUpdateState;
    }

    @NonNull
    @Override
    public ListItemShopAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        itemLayoutListOrderBinding = ItemLayoutShopOrderBinding.inflate(inflater,parent,false);

        return new ViewHolder(itemLayoutListOrderBinding);
    }

    @Override
    public void  onBindViewHolder(@NonNull ListItemShopAdminAdapter.ViewHolder holder, int position) {

         ItemOrder itemOrder = itemOrders.get(position);
         holder.itemLayoutListOrderBinding.setItemOrder(itemOrder);

         // GOI CALLBACK DE MO BOTTOMSHEFT TRONG CLASS listOrderAdminActivity
         holder.itemLayoutListOrderBinding.capnhatdonhang.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 iUpdateState.clickUpdateState(itemOrder, true);

             }
         });

    }

    @Override
    public int getItemCount() {
        return itemOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutShopOrderBinding itemLayoutListOrderBinding  ;

        public ViewHolder(     ItemLayoutShopOrderBinding itemLayoutListOrderBinding ) {
            super(itemLayoutListOrderBinding.getRoot());
            this.itemLayoutListOrderBinding = itemLayoutListOrderBinding ;
        }
    }
}
