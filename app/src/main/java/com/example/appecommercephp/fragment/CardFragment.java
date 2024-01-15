package com.example.appecommercephp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.Adapter.CartAdapter;
import com.example.appecommercephp.PaymentActivity;
import com.example.appecommercephp.databinding.FragmentCardBinding;
import com.example.appecommercephp.model.CartModel;
import com.example.appecommercephp.model.ItemCartModel;
import com.example.appecommercephp.viewmodel.CartViewModel;

import java.util.ArrayList;


public class CardFragment extends Fragment {

    FragmentCardBinding fragmentCardBinding ;
   public static CartViewModel cartViewModel ;

    public CardFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentCardBinding = FragmentCardBinding.inflate(inflater,container,false);

         cartViewModel = new CartViewModel();
        fragmentCardBinding.setCartViewModel(cartViewModel);

//        khi binding data trong mvvm thì chỉ cần tạo linearlayout và xét cho nó thôi


        LinearLayoutManager linearLayoutManagerLaptop = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        fragmentCardBinding.rcvCart.setLayoutManager(linearLayoutManagerLaptop);

          if(cartViewModel.getStringObservableField().equals("1"))
          {
              Toast.makeText(getContext(), "Xóa sản phẩm thành công !", Toast.LENGTH_SHORT).show();
          }else {

          }

          // xác nhận thanh toán đơn hàng
          fragmentCardBinding.xacNhan.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  ArrayList<ItemCartModel> itemCartModels = new ArrayList<>();

                  itemCartModels = cartViewModel.getDataCart();

                  if(itemCartModels.size()  > 0)
                  {
                      Intent intent = new Intent(getContext(), PaymentActivity.class);

                      Bundle bundle = new Bundle();
                      bundle.putParcelableArrayList("listObject", itemCartModels);
                      intent.putExtras(bundle);

                      startActivity(intent);

                  }
                  else
                  {
                      Toast.makeText(getContext(),"Bạn chưa chọn sản phẩm nào",Toast.LENGTH_SHORT).show();
                  }

              }
          });

        return fragmentCardBinding.getRoot();
    }


    @BindingAdapter({"listCart"})
    public static void getDATa(RecyclerView view, ObservableArrayList<CartModel> cartModelObservableArrayList  )
    {
        CartAdapter mainAdapter = new CartAdapter(cartModelObservableArrayList, view.getContext(),cartViewModel);
        mainAdapter.notifyDataSetChanged();
        view.setAdapter(mainAdapter);
    }




}