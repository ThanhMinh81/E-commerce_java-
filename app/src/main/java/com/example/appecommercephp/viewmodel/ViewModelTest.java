package com.example.appecommercephp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelTest extends ViewModel {

    public MutableLiveData<Integer> count = new MutableLiveData<>(0) ;
    Integer init = count.getValue();


    public ViewModelTest( ) {

    }

    public void increment()
    {
        int c=   init++;
         count.postValue(c);
    }



}
