package com.example.appecommercephp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.appecommercephp.Adapter.PaymentAdapter;
import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.databinding.ActivityOrderBinding;
import com.example.appecommercephp.model.ItemCartModel;
import com.example.appecommercephp.model.ItemOrder;
import com.example.appecommercephp.model.OrderModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class PaymentActivity extends AppCompatActivity {

    private ActivityOrderBinding activityOrderBinding ;

    private String encodedFunction ;

    private  PaymentAdapter paymentAdapter;

    private  OrderModel orderModel ;

    private  ArrayList<ItemCartModel> receivedList ;

   private Timer timer ;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_order);
         activityOrderBinding = DataBindingUtil.setContentView(this, R.layout.activity_order);
         receivedList = new ArrayList<>();

        timer = new Timer() ;

         /// toolbar & nut back
         setSupportActionBar(activityOrderBinding.toolbarOrder);

         activityOrderBinding.iconBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               onBackPressed();
             }
         });



//        activityOrderBinding.blackScreen.setVisibility(View.GONE);

         // toolbar va nut back

        // thiet lap du lieu cho xml Binding
         orderModel = new OrderModel();

         activityOrderBinding.setOrderModel(orderModel);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            receivedList = bundle.getParcelableArrayList("listObject");

            // Sử dụng receivedList trong Activity của bạn
        }

        if(receivedList  != null)
        {
            // tao du lieu cho list product
            getDataListOrder(receivedList , orderModel);
        }


        paymentAdapter = new PaymentAdapter(this,receivedList);


        Date currentDate = new Date();



        LinearLayoutManager linearLayoutManagerLaptop = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        activityOrderBinding.rcvPayment.setAdapter(paymentAdapter);
        paymentAdapter.notifyDataSetChanged();
        activityOrderBinding.rcvPayment.setLayoutManager(linearLayoutManagerLaptop);






        activityOrderBinding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewGroup parentView = findViewById(R.id.nestedview);

                // Đo chiều cao của view cha
                int parentHeight = parentView.getHeight();

                View overlayView = findViewById(R.id.overlayView);


                ViewGroup.LayoutParams layoutParams = overlayView.getLayoutParams();
                layoutParams.height = parentHeight; // Chiều cao mới là 300 pixels (hoặc dp tùy thuộc vào bạn)
                overlayView.setVisibility(View.VISIBLE);
                overlayView.bringToFront();
                activityOrderBinding.location.setEnabled(false);
                activityOrderBinding.editText.setEnabled(false);
                activityOrderBinding.editText2.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        View overlayView = findViewById(R.id.overlayView);
                        ViewGroup.LayoutParams layoutParams = overlayView.getLayoutParams();
                        // Chiều cao mới là 300 pixels (hoặc dp tùy thuộc vào bạn)
                        overlayView.setVisibility(View.GONE);
                        activityOrderBinding.location.setEnabled(true);
                        activityOrderBinding.editText.setEnabled(true);
                        activityOrderBinding.editText2.setEnabled(true);

                    }

                }, 2000);




                // Sử dụng Gson để chuyển đổi thành JSON
                Gson gson = new Gson();
                String json = gson.toJson(orderModel);


                // In ra chuỗi JSON hoặc gửi nó đến server
                Log.d("JSON", json);


                ApiService.API_SERVICE.addOrder(orderModel)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull ResponseBody responseBody) {

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });





//                String idUser = HomeFragment.idUser ;

                // Tạo Map chứa các dữ liệu cần gửi
//                Map<String, RequestBody> dataMap = new HashMap<>();

// Tạo RequestBody từ dữ liệu trường (idUser)
//                RequestBody idUserRequestBody = RequestBody.create(MediaType.parse("text/plain"), idUser);
//                dataMap.put("id_user", idUserRequestBody);



// Chuyển đối tượng thành chuỗi JSON
//                Gson gson = new Gson();
//                String jsonData = gson.toJson(receivedList);

// Tạo RequestBody từ chuỗi JSON (productsData)
//                RequestBody productsDataRequestBody = RequestBody.create(MediaType.parse("application/json"), jsonData);
//                dataMap.put("productsData", productsDataRequestBody);



            }
        });



    }

    private void getDataListOrder(ArrayList<ItemCartModel> receivedList , OrderModel orderModel) {

        ArrayList<ItemOrder>  orders = new ArrayList<>();

        for (ItemCartModel itemCartModel: receivedList)
        {
//            Log.d("oihoasfoiasdihf",itemCartModel.getIdShop());
             ItemOrder itemOrder = new ItemOrder(
                     1,
                      Integer.parseInt(itemCartModel.getIdProduct()),
                        itemCartModel.getCartQuantity(),
                      itemCartModel.getNameProduct(),
                      itemCartModel.getImgProduct(),
                      itemCartModel.getCartPrice(),
                     "Đang chờ xác nhận" ,
                      itemCartModel.getIdShop(),
                     itemCartModel.getNameShop()

                     );
             orders.add(itemOrder);
        }

        orderModel.setItemOrderList(orders);


    }

    @BindingAdapter({"bind:imgUrl"})
    public static void setProfilePicture(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }

    @BindingAdapter({"bind:price" , "bind:amount"})
    public static   void caculatorPrice(TextView textView , String price , String amount  )
    {

        textView.setText(String.valueOf((Integer.parseInt(price) * Integer.parseInt(amount))));

    }


}