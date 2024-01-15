package com.example.appecommercephp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ActivityRegisterBinding;
import com.example.appecommercephp.model.User;
import com.example.appecommercephp.viewmodel.RegisterViewModel;

public class Register extends AppCompatActivity {

   ActivityRegisterBinding activityRegisterBinding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        activityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        LoadingAlert loadingAlert = new LoadingAlert( Register.this);

        RegisterViewModel registerViewModel = new RegisterViewModel(this);

        User user = new User();
        activityRegisterBinding.setUser(user);
        activityRegisterBinding.setRegisterViewModel(registerViewModel);



    }
}
