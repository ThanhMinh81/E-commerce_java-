package com.example.appecommercephp.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.model.ProductsData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel {


    public  ObservableArrayList<ProductsData> observableArrayList = new ObservableArrayList<>();

    private ObservableArrayList<ProductsData> observableArrayListLaptopProduct = new ObservableArrayList<>();




    private Disposable disposable ;
    private  Disposable disposable2 ;
    HomeFragment.ClickToDetails clickToDetails ;


    public MainViewModel(HomeFragment.ClickToDetails clickToDetails)
    {
        this.clickToDetails = clickToDetails ;
        ApiService.API_SERVICE.callApi()
//                đăng ký hàm để phát ra data trên thread nào
                .subscribeOn(Schedulers.io())
//                thread mà obsrve quan sát và lắng nghe data dc phát ra từ observerable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductsData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d ;
//                        Log.d("kiemtrathread1", Thread.currentThread().getName().toString());
                    }

                    @Override
                    public void onNext(@NonNull List<ProductsData> productsData) {
                        Log.d("kiemtrathread2", Thread.currentThread().getName().toString());
                       observableArrayList.addAll(productsData);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                      disposable.dispose();
                    }
                });



        ApiService.API_SERVICE.CallGetLaptop().subscribeOn(Schedulers.io())
//                thread mà obsrve quan sát và lắng nghe data dc phát ra từ observerable
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductsData>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposable2 = d ;
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<ProductsData> productsData) {
                      observableArrayListLaptopProduct.addAll(productsData);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                   disposable2.dispose();
                    }
                });



    }





    public HomeFragment.ClickToDetails getClickToDetails() {
        return clickToDetails;
    }

    public void setClickToDetails( HomeFragment.ClickToDetails clickToDetails) {
        this.clickToDetails = clickToDetails;
    }

    public ObservableArrayList<ProductsData> getObservableArrayList() {
        return observableArrayList;
    }

    public void setObservableArrayList(ObservableArrayList<ProductsData> observableArrayList) {
        this.observableArrayList = observableArrayList;
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    public Disposable getDisposable2() {
        return disposable2;
    }

    public void setDisposable2(Disposable disposable2) {
        this.disposable2 = disposable2;
    }

    public ObservableArrayList<ProductsData> getObservableArrayListLaptopProduct() {
        return observableArrayListLaptopProduct;
    }

    public void setObservableArrayListLaptopProduct(ObservableArrayList<ProductsData> observableArrayListLaptopProduct) {
        this.observableArrayListLaptopProduct = observableArrayListLaptopProduct;
    }
}
