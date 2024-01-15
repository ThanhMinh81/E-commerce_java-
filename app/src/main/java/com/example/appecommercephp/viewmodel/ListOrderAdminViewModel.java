package com.example.appecommercephp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.model.ItemOrder;
import com.example.appecommercephp.model.OrderModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ListOrderAdminViewModel extends ViewModel {

    MutableLiveData<List<OrderModel>> listMutableLiveData = new MutableLiveData<>();

    MutableLiveData<String> resultUpdateStateOrder = new MutableLiveData<>();

    public ListOrderAdminViewModel() {

        // viet mot ham de check xem nguoi dung do co gio hang tuc no co phai la admin khong
        ApiService.API_SERVICE.getListOrders("0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<OrderModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ArrayList<OrderModel> orderModels) {

                        // postValue nay thuc hien ben trong workderThread
                        //nguoc lai setValue thuc hien trong mainThread
                        listMutableLiveData.postValue(orderModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public MutableLiveData<List<OrderModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setListMutableLiveData(MutableLiveData<List<OrderModel>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }


    public MutableLiveData<String> getResultUpdateStateOrder() {
        return resultUpdateStateOrder;
    }

    public void setResultUpdateStateOrder(MutableLiveData<String> resultUpdateStateOrder) {
        this.resultUpdateStateOrder = resultUpdateStateOrder;
    }

    public void changeDataState(ItemOrder itemOrder)
    {
        List<OrderModel> orderModels = new ArrayList<>(listMutableLiveData.getValue());

        Iterator<OrderModel> iterator = orderModels.iterator();

        while (iterator.hasNext()) {
            OrderModel orderModel1 = iterator.next();

            for ( ItemOrder itemOrder1 : orderModel1.getItemOrderList()) {

                if(itemOrder1.getIdItemOrder() == itemOrder.getIdItemOrder())
                {
                    itemOrder1.setStateOrder(itemOrder.getStateOrder());
                    updateStateOrder(itemOrder);
                    break;
                }

            }
            listMutableLiveData.postValue(null);
            listMutableLiveData.postValue(orderModels);



        }
    }

    private void updateStateOrder(ItemOrder itemOrder) {
        ApiService.API_SERVICE.updateStateOrder(itemOrder.getStateOrder(),String.valueOf(itemOrder.getIdItemOrder()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        resultUpdateStateOrder.postValue("Cập nhật trạng thái thành công !");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
