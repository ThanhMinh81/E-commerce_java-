package com.example.appecommercephp.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

public class HomeFragmentViewModel {
    private Context context ;
    public HomeFragmentViewModel(Context context) {

        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("User",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=  sharedPreferences.edit() ;
        editor.putString("checkGetFavourite","yes");
        editor.apply();
        DetailsProductViewModel detailsProductViewModel = new DetailsProductViewModel();
        detailsProductViewModel.getAllProductFavourite();

    }

}


