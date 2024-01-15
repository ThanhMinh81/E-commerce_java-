package com.example.appecommercephp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.model.ProductsData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class SaveProductViewModel extends ViewModel {

//    private  ObservableArrayList<ProductsData> productsDataObservableArrayList = new ObservableArrayList<>();

    private MutableLiveData<List<ProductsData>>   listMutableLiveData     ;

    private MutableLiveData<String> resultSave = new MutableLiveData<>();

    private Disposable disposable;


    public SaveProductViewModel() {

        Log.d("fshfsahfoashhf3573","fasfasfafasfasfdas");
//        if (listMutableLiveData == null)
//        {
            listMutableLiveData = new MutableLiveData<List<ProductsData>>();
            getDataListSave();
//        }

    }


    public void getDataListSave() {


        ApiService.API_SERVICE.getListSaveProduct(HomeFragment.idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductsData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<ProductsData> productsData) {

                        listMutableLiveData.postValue(null);

                        listMutableLiveData.postValue(productsData);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });


    }


    public void removeProductSave(String idProduct) {


        ApiService.API_SERVICE.updateFavourite(HomeFragment.idUser, idProduct, "Delete")
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
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {


                        List<ProductsData> productsData = new ArrayList<>(listMutableLiveData.getValue());

                        Iterator<ProductsData> iterator = productsData.iterator();

                        while (iterator.hasNext()) {
                            ProductsData product = iterator.next();
                            if (String.valueOf(product.getIdProduct()).equals(idProduct))
                            {
                                iterator.remove();
                                resultSave.postValue("Bỏ lưu sản phẩm thành công !");
                                break;
                            }
                        }

                        listMutableLiveData.postValue(null);

                        listMutableLiveData.postValue(productsData);

                    }

                });


    }




    public MutableLiveData<List<ProductsData>> getListMutableLiveData() {

        return listMutableLiveData;

    }

    public void setListMutableLiveData(List<ProductsData> listMutableLiveData) {
        this.listMutableLiveData.postValue(listMutableLiveData);
    }

    public MutableLiveData<String> getResultSave() {
        return resultSave;
    }

    public void setResultSave(MutableLiveData<String> resultSave) {
        this.resultSave = resultSave;
    }


}
