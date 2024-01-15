package com.example.appecommercephp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.databinding.ItemLayoutListOrderBinding;
import com.example.appecommercephp.interfaceUI.IUpdateState;
import com.example.appecommercephp.model.OrderModel;

import java.util.List;

public class ListOrderAdminAdapter extends RecyclerView.Adapter<ListOrderAdminAdapter.ViewHolder> {

    List<OrderModel>  orderModels ;

    ItemLayoutListOrderBinding itemLayout ;
    IUpdateState iUpdateState ;

    Context context ;


    public ListOrderAdminAdapter(List<OrderModel> orderModels , Context context ,  IUpdateState iUpdateState) {
        this.orderModels = orderModels;
        this.context = context ;
        this.iUpdateState = iUpdateState ;
    }

    @NonNull
    @Override
    public ListOrderAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        itemLayout = ItemLayoutListOrderBinding.inflate(inflater,parent,false);

        return new ViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOrderAdminAdapter.ViewHolder holder, int position) {

            OrderModel orderModel = orderModels.get(position);
            holder.itemLayout.setOrderModel(orderModel);
            /// khoi tao recycleview cho list item
          ListItemShopAdminAdapter listItemShopAdminAdapter  = new ListItemShopAdminAdapter(orderModel.getItemOrderList() , iUpdateState);

          holder.itemLayout.rcvItemListShoppingCart.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
          holder.itemLayout.rcvItemListShoppingCart.setAdapter(listItemShopAdminAdapter);
          listItemShopAdminAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutListOrderBinding itemLayout  ;

        public ViewHolder(ItemLayoutListOrderBinding itemLayout ) {
            super(itemLayout.getRoot());
            this.itemLayout = itemLayout ;
        }
    }
}
