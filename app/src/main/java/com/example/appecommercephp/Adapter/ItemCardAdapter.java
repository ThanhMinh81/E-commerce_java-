package com.example.appecommercephp.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appecommercephp.databinding.ItemCartCheckBinding;
import com.example.appecommercephp.databinding.ItemShoppingCartBinding;
import com.example.appecommercephp.model.CartModel;
import com.example.appecommercephp.model.ItemCartModel;
import com.example.appecommercephp.model.ItemOrder;
import com.example.appecommercephp.viewmodel.CartViewModel;
import com.example.appecommercephp.viewmodel.ItemCartViewModel;
import java.util.ArrayList;
import java.util.List;

public class ItemCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CartViewModel cartViewModel;
    private ArrayList<ItemCartModel> itemCartModels;
    private ItemCartCheckBinding itemCartCheckBinding;
    private ItemCartViewModel itemCartViewModel;

    private CartModel cartModel;

    private boolean checkAdapterShopping = false;

    private List<ItemOrder> itemOrders;

    public static final int VIEW_TYPE_CART = 1;
    public static final int VIEW_TYPE_SHOPPING_CART = 2;

    public ItemCardAdapter(boolean checkAdapterShopping, List<ItemOrder> itemOrders) {
        this.checkAdapterShopping = checkAdapterShopping;
        this.itemOrders = itemOrders;
    }

    public ItemCardAdapter(ArrayList<ItemCartModel> itemCartModels, CartViewModel cartViewModel, CartModel cartModel, ItemCartViewModel itemCartViewModel) {
        this.itemCartModels = itemCartModels;
        this.cartViewModel = cartViewModel;
        this.cartModel = cartModel;
        this.itemCartViewModel = itemCartViewModel;
        this.itemCartViewModel.itemCartViewModels.clear();
        this.itemCartViewModel.itemCartViewModels.addAll(itemCartModels);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_CART:
                itemCartCheckBinding = ItemCartCheckBinding.inflate(inflater, parent, false);
                return new CartViewHolder(itemCartCheckBinding);
            case VIEW_TYPE_SHOPPING_CART:
                ItemShoppingCartBinding itemShoppingCartBinding = ItemShoppingCartBinding.inflate(inflater, parent, false);
                return new ShoppingCartViewHolder(itemShoppingCartBinding);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CartViewHolder) {
            ItemCartModel cartModel = itemCartModels.get(position);
            ((CartViewHolder) holder).bind(cartModel);
        } else if (holder instanceof ShoppingCartViewHolder) {
            ItemOrder itemOrder = itemOrders.get(position);
            ((ShoppingCartViewHolder) holder).bind(itemOrder);
        }
    }

    @Override
    public int getItemCount() {
        if (!checkAdapterShopping) {
            return itemCartModels.size();
        }
        return itemOrders.size();
    }

    @Override
    public int getItemViewType(int position) {
        return checkAdapterShopping ? VIEW_TYPE_SHOPPING_CART : VIEW_TYPE_CART;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        ItemCartCheckBinding itemCartCheckBinding;

        public CartViewHolder(ItemCartCheckBinding itemCartCheckBinding) {
            super(itemCartCheckBinding.getRoot());
            this.itemCartCheckBinding = itemCartCheckBinding;
        }

        public void bind(ItemCartModel cartModel) {
            itemCartCheckBinding.setItemCartModel(cartModel);
            itemCartCheckBinding.setCartModel(ItemCardAdapter.this.cartModel);
            itemCartCheckBinding.setCartViewModel(cartViewModel);
        }
    }

    public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {

        ItemShoppingCartBinding itemShoppingCartBinding;

        public ShoppingCartViewHolder(ItemShoppingCartBinding itemShoppingCartBinding) {
            super(itemShoppingCartBinding.getRoot());
            this.itemShoppingCartBinding = itemShoppingCartBinding;
        }

        public void bind(ItemOrder itemOrder) {
            itemShoppingCartBinding.setItemOrder(itemOrder);
            // Bạn có thể thực hiện các thao tác khác cần thiết ở đây
        }
    }
}
