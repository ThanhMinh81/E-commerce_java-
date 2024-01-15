package com.example.appecommercephp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ActivityLoginBinding;
import com.example.appecommercephp.model.User;
import com.example.appecommercephp.viewmodel.DetailsProductViewModel;
import com.example.appecommercephp.viewmodel.LoginViewModel;

public class Login extends AppCompatActivity {

    public interface LoginMessager{
        void toashMessager(String s);
    }

    ActivityLoginBinding activityLoginBinding ;
    Login.LoginMessager loginMessager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginMessager = new LoginMessager() {
            @Override
            public void toashMessager(String s) {
                if(s.contains("thành công"))
                {

                    Toast.makeText(Login.this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(Login.this, "Đăng nhập thất bại , Vui lòng thử lại !", Toast.LENGTH_SHORT).show();
                }
            }
        };

        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        User user = new User();

        LoginViewModel loginViewModel = new LoginViewModel(loginMessager ,this);

        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setUser(user);


    }
}