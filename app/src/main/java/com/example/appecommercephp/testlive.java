package com.example.appecommercephp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.appecommercephp.viewmodel.ViewModelTest;

public class testlive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlive);

        ViewModelTest viewModelTest = new ViewModelTest();

        Button button = this.<Button>findViewById(R.id.btnClicckc);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             viewModelTest.increment();
            }
        });


        final Observer<Integer> observe = new Observer<Integer>() {


            @Override
            public void onChanged(Integer integer) {
                Log.d("fdsiofasdfhoasd",integer + " ");
            }
        };

        viewModelTest.count.observe(this,observe);



    }
}