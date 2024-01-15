package com.example.appecommercephp.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.model.ProductsData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class BottomSheetViewModel extends ViewModel   implements ViewModelProvider.Factory
{
//    public  ObservableField<String> stringObservableField = new ObservableField<String>()  ;


    private MutableLiveData<String> stringMutableLiveData ;
    private MutableLiveData<ArrayList<ProductsData>>  arrayListMutableLiveData ;

//    private ObservableField<Boolean> booleanObservableField = new ObservableField<>();

    private Context context ;

    public BottomSheetViewModel(Context context) {
        this.context = context;
        stringMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getStringMutableLiveData() {
        return stringMutableLiveData;
    }

    public void setStringMutableLiveData(MutableLiveData<String> stringMutableLiveData) {
        this.stringMutableLiveData = stringMutableLiveData;
    }


    public void addToCart(ProductsData productsData)
    {
        String idUser = HomeFragment.idUser ;


        // Tạo Map chứa các dữ liệu cần gửi
        Map<String, RequestBody> dataMap = new HashMap<>();

// Tạo RequestBody từ dữ liệu trường (idUser)
        RequestBody idUserRequestBody = RequestBody.create(MediaType.parse("text/plain"), idUser);
        dataMap.put("id_user", idUserRequestBody);

// Chuyển đối tượng thành chuỗi JSON
        Gson gson = new Gson();
        String jsonData = gson.toJson(productsData);

// Tạo RequestBody từ chuỗi JSON (productsData)
        RequestBody productsDataRequestBody = RequestBody.create(MediaType.parse("application/json"), jsonData);
        dataMap.put("productsData", productsDataRequestBody);


        ApiService.API_SERVICE.addProductCart(dataMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {

                        String result = null;
                        try {
                            result = responseBody.string();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(result.contains("1"))
                        {
                            stringMutableLiveData.setValue("Thành công");
                        }else if(result.contains("-1")) {
                            stringMutableLiveData.setValue("Thất bại");
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("òdsfsdfsaf",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void incrementQuantity(ProductsData productsData , boolean data)
    {
        if(data == false)
        {
//             thực hiện phép trừ
            if(Integer.parseInt(productsData.getQuantityProduct()) > 1)
            {
                int quantityCurrent = Integer.parseInt(productsData.getQuantityProduct()) ;
                quantityCurrent -= 1 ;
                Log.d("Fsfsafa","Fsfasf");
                productsData.setQuantityProduct(String.valueOf(quantityCurrent));

            }else if(Integer.parseInt(productsData.getQuantityProduct()) <= 1) {

                Log.d("Kiemtradulieubehon0", productsData.getQuantityProduct());

            }
        }else if( data == true ) {
//            thực hiện phép cộng
            int current = Integer.parseInt(productsData.getQuantityProduct());
            current += 1;
            productsData.setQuantityProduct(String.valueOf(current));

        }
    }


    public void getQuantityCart(String idProduct , String idUser)
    {
        ApiService.API_SERVICE.getProductQuantityCart(idProduct, idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<String> strings) {
                        Log.d("fsdfsfsdfa",HomeFragment.idUser + " ");

//                        stringObservableField.set(strings.get(0).toString());

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
