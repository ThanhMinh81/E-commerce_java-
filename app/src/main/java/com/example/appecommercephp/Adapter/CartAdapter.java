package com.example.appecommercephp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ItemCartBinding;
import com.example.appecommercephp.model.CartModel;
import com.example.appecommercephp.viewmodel.CartViewModel;
import com.example.appecommercephp.viewmodel.ItemCartViewModel;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

//    private ArrayList<CartModel> cartModels ;
    ObservableArrayList<CartModel> cartModelObservableArrayList ;
    private ItemCartBinding itemCartBinding ;
    private Context context ;

    CartViewModel cartViewModel ;

    public CartAdapter(ObservableArrayList<CartModel> cartModelObservableArrayList, Context context , CartViewModel cartViewModel) {
        this.cartModelObservableArrayList  = cartModelObservableArrayList ;
        this.context = context;
        Log.d("IOfhoisdfsdfasfsKhoiTao","codfasdfhsadof");
        this.cartViewModel = cartViewModel ;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        itemCartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_cart,parent,false);
           return new ViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

//        Log.d("IOfhoisdfsdfasfs","codfasdfhsadof");


        CartModel cartModel = cartModelObservableArrayList.get(position);

        Log.d("1234567","" + cartModel.getItemCartModels().size() );

        holder.itemCartBinding.setCartModel(cartModel);
        holder.itemCartBinding.setCartViewModel(this.cartViewModel);

        LinearLayoutManager linearLayoutManagerLaptop = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        ItemCartViewModel itemCartViewModel = new ItemCartViewModel();

        ItemCardAdapter itemCardAdapter = new ItemCardAdapter(cartModel.getItemCartModels(),cartViewModel,cartModel , itemCartViewModel);

        itemCardAdapter.notifyDataSetChanged();

        holder.itemCartBinding.rcvItemCart.setLayoutManager(linearLayoutManagerLaptop);

        holder.itemCartBinding.rcvItemCart.setAdapter(itemCardAdapter);


    }

    @Override
    public int getItemCount() {
        return   cartModelObservableArrayList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCartBinding itemCartBinding ;
        public ViewHolder( ItemCartBinding itemCartBinding ) {
            super(itemCartBinding.getRoot());

            this.itemCartBinding =  itemCartBinding ;

        }
    }
}
