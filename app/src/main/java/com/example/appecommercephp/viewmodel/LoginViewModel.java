package com.example.appecommercephp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.model.User;
import com.example.appecommercephp.view.Login;
import com.example.appecommercephp.view.MainActivity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginViewModel {

    private Context context ;

    public SharedPreferences pref ;
    SharedPreferences.Editor editor ;

    Login.LoginMessager loginMessager ;

    public LoginViewModel(Login.LoginMessager loginMessager ,  Context context) {

          this.loginMessager = loginMessager ;
          this.context = context ;


              pref   = this.context.getSharedPreferences("User",Context.MODE_PRIVATE);
              editor = pref.edit() ;

    }

    public void loginUser(String email , String passwords)
    {

        ApiService.API_SERVICE.loginUser(email,passwords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<User> users) {

                        User user = users.get(0);

                        editor.putString("name",user.getFullName());
                        editor.putString("avata",user.getImgAvt());
                        editor.putString("email",user.getEmail());
                        editor.putString("pass",user.getPasswords());
                        editor.putString("id",String.valueOf(user.getId()));
                        editor.putString("ownshop",String.valueOf(user.getOwnShop()));

                        editor.apply();

                        loginMessager.toashMessager("thành công");
                        Log.d("kiemtradulieulogin",user.toString());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginMessager.toashMessager("thất bại");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }






}
