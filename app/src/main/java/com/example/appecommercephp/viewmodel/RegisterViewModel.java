package com.example.appecommercephp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.view.Login;
import com.example.appecommercephp.view.MainActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RegisterViewModel {

    private Context context ;

    public RegisterViewModel(Context context) {
        this.context = context ;
    }

    public void Login(String email , String matKhau)
    {

        Log.d("fsfsfsf",email + matKhau);

    }

    public void registerAccount(String email , String passwords)
    {
        ApiService.API_SERVICE.registerUser(email,passwords)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d)
                    {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody)
                    {

                        if(responseBody.toString().contains("1"))
                        {
                            Toast.makeText(context, "Đăng ký tài khoản thành công , Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, Login.class);
                            context.startActivity(intent);
                        }else if(responseBody.toString().contains("0"))
                        {
                            Toast.makeText(context, "Đăng ký tài khoản thất bại ", Toast.LENGTH_SHORT).show();



                        }else  if(responseBody.toString().contains("-1"))
                        {
                            Toast.makeText(context, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e)
                    {
                    }

                    @Override
                    public void onComplete()
                    {
                    }
                });
    }

    public void login()
    {
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }


}
