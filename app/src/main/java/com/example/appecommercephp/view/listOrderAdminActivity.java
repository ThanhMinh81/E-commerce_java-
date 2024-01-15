package com.example.appecommercephp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.Adapter.ListOrderAdminAdapter;
import com.example.appecommercephp.databinding.ActivityListOrderAdminBinding;
import com.example.appecommercephp.fragment.BottomSheetUpdateStateFragment;
import com.example.appecommercephp.interfaceUI.IGetValueUpdateState;
import com.example.appecommercephp.interfaceUI.IUpdateState;
import com.example.appecommercephp.model.ItemOrder;
import com.example.appecommercephp.model.OrderModel;
import com.example.appecommercephp.viewmodel.ListOrderAdminViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class listOrderAdminActivity extends AppCompatActivity {



    ActivityListOrderAdminBinding  binding   ;


    ListOrderAdminAdapter cartAdapter ;

    ArrayList<OrderModel> orderModels ;


    // callback duuoc goi tu ListItemShopAdminAdapter
    // de mo bottomsheft ben trong activity nay
    IUpdateState iUpdateState ;

    /// calblback nay truyen cho bottomsheft de lay gia tri trang thai don hang
    IGetValueUpdateState iGetValueUpdateState ;

    ListOrderAdminViewModel listOrderAdminViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding  = ActivityListOrderAdminBinding.inflate(getLayoutInflater());
        View view  = binding.getRoot();
        setContentView(view);

        orderModels = new ArrayList<>();

        listOrderAdminViewModel = new ListOrderAdminViewModel();



        iGetValueUpdateState = new IGetValueUpdateState() {
            @Override
            public void getStateItemOrder(ItemOrder itemOrder) {

                // goi den ViewModel de update trang thai don hang
                listOrderAdminViewModel.changeDataState(itemOrder);

            }
        };

         iUpdateState = new IUpdateState() {
             @Override
             public void clickUpdateState(ItemOrder itemOrder , boolean check) {

               if(check)
               {
                   //truyen them cho bottomsheet mot viewmodel de tuong tac voi livedata cua listOrder
                   BottomSheetUpdateStateFragment bottomSheetUpdateStateFragment = new BottomSheetUpdateStateFragment(iGetValueUpdateState, itemOrder);

                   bottomSheetUpdateStateFragment.show(getSupportFragmentManager(), bottomSheetUpdateStateFragment.getTag());
               }
             }
         };


        cartAdapter = new ListOrderAdminAdapter(orderModels,this , iUpdateState);

        binding.rcvAdminListOrder.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        binding.rcvAdminListOrder.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        final Observer<List<OrderModel>> observe = new Observer<List<OrderModel>>() {


            @Override
            public void onChanged(List<OrderModel> orderModels1) {

                orderModels.clear();
                orderModels.addAll(orderModels1);
                cartAdapter.notifyDataSetChanged();

            }
        };

        // dang ky lang nghe data o viewmodel
        listOrderAdminViewModel.getListMutableLiveData().observe(this,observe);


        final Observer<String> stringObserver= new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("kiemtradulieufosaih",s);
                Toast.makeText(listOrderAdminActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        };

        listOrderAdminViewModel.getResultUpdateStateOrder().observe(this,stringObserver);


    }


    @BindingAdapter({"price", "quantity"})
    public static void totalPrice(TextView view, String price , String quantity) {


        DecimalFormat formatter = new DecimalFormat("###,###,###");

        int total = Integer.parseInt(price) * Integer.parseInt(quantity);

        view.setText(price + " x " + quantity  + " = " + String.valueOf(total));

    }


}