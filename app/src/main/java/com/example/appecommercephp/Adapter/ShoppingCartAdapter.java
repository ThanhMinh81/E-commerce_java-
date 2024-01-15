package com.example.appecommercephp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ItemListShoppingCartBinding;
import com.example.appecommercephp.interfaceUI.IUpdateState;
import com.example.appecommercephp.model.ItemOrder;
import com.example.appecommercephp.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private ArrayList<OrderModel> orderModels ;
    private ItemListShoppingCartBinding shoppingCartLayoutBinding ;
    private Context context ;
    private IUpdateState iUpdateState ;

    public ShoppingCartAdapter(ArrayList<OrderModel> orderModels, Context context  ) {
        this.orderModels = orderModels;
        this.context = context;
        this.iUpdateState = iUpdateState ;
    }


    public ShoppingCartAdapter(ArrayList<OrderModel> orderModels, ItemListShoppingCartBinding shoppingCartLayoutBinding, Context context) {
        this.orderModels = orderModels;
        this.shoppingCartLayoutBinding = shoppingCartLayoutBinding;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       shoppingCartLayoutBinding =
               DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_shopping_cart,parent,false);
        return new ShoppingCartAdapter.ViewHolder(shoppingCartLayoutBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ViewHolder holder, int position) {

                // lay danh sach cua san pham cua tung don hang
              // khoi tao adapter

           OrderModel orderModel = orderModels.get(position);

           holder.shoppingCartLayoutBinding.setOrderModel(orderModel);

           List<ItemOrder> itemOrders = orderModels.get(position).getItemOrderList();

           Log.d("ồdishfoasfhsoafoasodf112",itemOrders.size() + " ");

         ItemCardAdapter itemCardAdapter = new ItemCardAdapter(true,itemOrders);

           holder.shoppingCartLayoutBinding.rcvItemListShoppingCart.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
           holder.shoppingCartLayoutBinding.rcvItemListShoppingCart.setAdapter(itemCardAdapter);

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemListShoppingCartBinding shoppingCartLayoutBinding ;

        public ViewHolder(ItemListShoppingCartBinding shoppingCartLayoutBinding ) {
            super(shoppingCartLayoutBinding.getRoot());
            this.shoppingCartLayoutBinding = shoppingCartLayoutBinding;
        }
    }
}
