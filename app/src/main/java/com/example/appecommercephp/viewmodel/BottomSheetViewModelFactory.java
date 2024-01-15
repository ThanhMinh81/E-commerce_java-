package com.example.appecommercephp.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BottomSheetViewModelFactory implements ViewModelProvider.Factory {

    Context context ;

    public BottomSheetViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        BottomSheetViewModel bottomSheetViewModel = new BottomSheetViewModel(context);
        return (T) bottomSheetViewModel;
    }
}
