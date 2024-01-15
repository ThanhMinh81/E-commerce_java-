package com.example.appecommercephp.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appecommercephp.Adapter.ShoppingCartAdapter;
import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ShoppingCartLayoutBinding;
import com.example.appecommercephp.model.OrderModel;
import com.example.appecommercephp.viewmodel.ShoppingViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    ShoppingCartLayoutBinding shoppingCart ;
    ShoppingCartAdapter shoppingCartAdapter ;

    ShoppingViewModel shoppingViewModel ;

    ArrayList<OrderModel> orderModels ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_layout);

        shoppingCart = DataBindingUtil.setContentView(this, R.layout.shopping_cart_layout);

        orderModels = new ArrayList<>();

        shoppingCartAdapter = new ShoppingCartAdapter(orderModels , this );

        shoppingViewModel = new ShoppingViewModel();



        setSupportActionBar(shoppingCart.myToolbar);

        shoppingCart.backIconShoppingScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        shoppingCart.rcvShoppingCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));;
        shoppingCart.rcvShoppingCart.setAdapter(shoppingCartAdapter);



        final Observer<List<OrderModel>> observe =  new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels1) {

                orderModels.clear();
                orderModels.addAll(orderModels1);
                shoppingCartAdapter.notifyDataSetChanged();
            }
        };

        // Đăng ký lắng nghe cho LiveData trong sharedViewModel
        shoppingViewModel.getMutableLiveData().observe(ShoppingCartActivity.this,observe);



    }


    }


