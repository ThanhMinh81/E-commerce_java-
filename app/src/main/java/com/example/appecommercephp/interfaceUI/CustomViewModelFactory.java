package com.example.appecommercephp.interfaceUI;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appecommercephp.viewmodel.SaveProductViewModel;

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    public CustomViewModelFactory()
    {
        Log.d("fsafsaf","Fsadfasfas");
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        Log.d("fsafsaf939895395y89","Fsadfasfas");
        if (modelClass.isAssignableFrom(SaveProductViewModel.class)) {
            return (T) new SaveProductViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
