package com.example.appecommercephp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.appecommercephp.Adapter.MainAdapter;
import com.example.appecommercephp.databinding.FragmentHomeBinding;
import com.example.appecommercephp.model.ProductsData;
import com.example.appecommercephp.view.DetailsActivity;
import com.example.appecommercephp.viewmodel.MainViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public  String uid = "abcfsaf";

     FragmentHomeBinding fragmentHomeBinding ;

     public static String emailUser = "";
     public static String idUser = "";
     public static String idShopOwn  = "" ;



     public static SharedPreferences preferences ;

    public interface  ClickToDetails
    {
        void Click(ProductsData productsData);
    }

      ClickToDetails clickToDetails ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);

        clickToDetails = new ClickToDetails() {
            @Override
            public void Click(ProductsData productsData) {

                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("Product",productsData);
                startActivity(intent);

            }
        };


       preferences = getActivity().getSharedPreferences("User",Context.MODE_PRIVATE);
        emailUser  = preferences.getString("email","ko_co");

        idUser = preferences.getString("id","ko_co_id");

        idShopOwn = preferences.getString("ownshop","0");

        Log.d("àoasfoascoaicoawee",idUser + ""  + emailUser);


        MainViewModel mainViewModel = new MainViewModel(clickToDetails);

        fragmentHomeBinding.setMainViewModel(mainViewModel);
        fragmentHomeBinding.imageSlider.setImageList(CreateSliderHome());

//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        fragmentHomeBinding.rcvHome.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManagerLaptop = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        fragmentHomeBinding.rcvLaptop.setLayoutManager(linearLayoutManagerLaptop);


        return fragmentHomeBinding.getRoot();

    }

    private ArrayList<SlideModel> CreateSliderHome() {
        ArrayList<SlideModel> strings = new ArrayList<>();

        strings.add(new SlideModel("https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/2024/01/banner/720x220-min-720x220-1.png", ScaleTypes.FIT));

        strings.add(new SlideModel("https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/2024/01/banner/Redmi-Watch4-720-220-720x220-1.png", ScaleTypes.FIT));

        strings.add(new SlideModel("https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/2024/01/banner/Lap-tet-720-220-720x220.png", ScaleTypes.FIT));

        strings.add(new SlideModel("https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/2023/03/banner/chuyentrang-HP-390x210-1.png", ScaleTypes.FIT));
        return strings;
    }

    @BindingAdapter({"listProduct" , "clickMe"})
    public static void getDATa(RecyclerView view, ObservableArrayList<ProductsData> productsData , ClickToDetails clickToDetails )
    {
        MainAdapter mainAdapter = new MainAdapter(productsData , clickToDetails);
        view.setAdapter(mainAdapter);
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
//        String b = preferences.getString("checkGetFavourite","no");
        SharedPreferences.Editor editor=  preferences.edit() ;
        editor.remove("checkGetFavourite");
        editor.putString("checkGetFavourite","no");

        editor.apply();

    }



}