package com.example.appecommercephp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.appecommercephp.PaymentActivity;
import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.DetailsProductBinding;
import com.example.appecommercephp.fragment.BottomSheetFragment;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.interfaceView;
import com.example.appecommercephp.model.ProductsData;
import com.example.appecommercephp.viewmodel.DetailsProductViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity  {

    ProductsData productsData;
    DetailsProductBinding detailsProductBinding;

    interfaceView.detailsProduct detailseProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_product);

        detailsProductBinding = DataBindingUtil.setContentView(this, R.layout.details_product);

        productsData = getIntent().getParcelableExtra("Product");


        detailsProductBinding.setProductData(productsData);

        detailsProductBinding.imgBack.setOnClickListener(v -> onBackPressed());

        detailseProduct = new interfaceView.detailsProduct() {
            @Override
            public void showMess(String s) {

                try {
                    Log.d("òihsofsdfsd", "foiadfsdfiasdf");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DetailsActivity.this, "" + s, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("óidhofsfasdfsdf", e.getMessage());
                }
            }
        };


        DetailsProductViewModel detailsProductViewModel =
                new DetailsProductViewModel(productsData.getIdProduct(), DetailsActivity.this, detailseProduct);

        detailsProductBinding.setDetailsViewModel(detailsProductViewModel);

        detailsProductBinding.xemthem.setOnClickListener(v -> {
            if (detailsProductBinding.xemthem.getText().equals("Xem thêm")) {
                detailsProductBinding.tvDescript.setMaxLines(Integer.MAX_VALUE);
                detailsProductBinding.xemthem.setText("Thu gọn");
            } else {
                detailsProductBinding.tvDescript.setMaxLines(2);
                detailsProductBinding.xemthem.setText("Xem thêm");
            }

        });

        detailsProductBinding.chatShop.setOnClickListener(v -> {

//            if (HomeFragment.idUser.length() > 0) {
//                Intent intent = new Intent(DetailsActivity.this, MessagerActivity.class);
//                intent.putExtra("objec", productsData);
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "Vui lòng đăng nhập để xử dụng !", Toast.LENGTH_SHORT).show();
//            }

        });

        detailsProductBinding.btnMuaNgay.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, PaymentActivity.class);
            startActivity(intent);

        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        detailsProductBinding.rcvCMT.setLayoutManager(linearLayoutManager);

        detailsProductBinding.mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d( "efasfasfrror" , HomeFragment.emailUser.toString());

                if(!HomeFragment.emailUser.equals("ko_co"))
                {
                    BottomSheetFragment bottomSheetDialogFragment = new BottomSheetFragment(productsData, DetailsActivity.this);
                    bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                }
                else
                {
                    Toast.makeText(DetailsActivity.this,"Vui lòng đăng nhập !",Toast.LENGTH_SHORT).show();
                }


            }

        });

    }


    @BindingAdapter({"priceProduct"})
    public static void formatPrice(TextView view, String price) {

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        view.setText(formatter.format(Integer.valueOf(price)) + "vnđ");

    }

    @BindingAdapter({"SliderDetails"})
    public static void sliderDetails(ImageSlider imageSlider, ArrayList<String> slideModels) {
        ArrayList<SlideModel> slideModels1 = new ArrayList<>();

        for (String s : slideModels) {
            slideModels1.add(new SlideModel(s, ScaleTypes.FIT));
        }

        imageSlider.setImageList(slideModels1);

    }


    @Override
    public void onBackPressed() {
        // Xử lý logic tại đây
        // Ví dụ: Quay lại màn hình trước đó bằng cách gọi super.onBackPressed()
        super.onBackPressed();
    }


}