package com.example.appecommercephp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShoppingViewModel extends ViewModel {

      MutableLiveData<List<OrderModel>> mutableLiveData  = new MutableLiveData<List<OrderModel>>();



    public ShoppingViewModel()
    {

        ApiService.API_SERVICE.getListShoppingOrder("0")
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<OrderModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ArrayList<OrderModel> orderModels) {


                        Log.d("ốiafas",orderModels.get(0).getItemOrderList().get(0).getIdShop() + "");

                        mutableLiveData.postValue(orderModels);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.d("kiemtraduusdifhiashdfa",e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public MutableLiveData<List<OrderModel>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<List<OrderModel>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }




}
