package com.example.appecommercephp.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ActivityMain2Binding;
import com.example.appecommercephp.fragment.AccountFragment;
import com.example.appecommercephp.fragment.CardFragment;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.fragment.SaveProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity  {


     ActivityMain2Binding activityMain ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseApp.initializeApp(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        activityMain = ActivityMain2Binding.inflate(getLayoutInflater());
        View view  = activityMain.getRoot();
        setContentView(view);



        if (HomeFragment.emailUser.equals("ko_co"))
                {

//                    Menu menu = activityMain.bottomNavigationView.getMenu();
//                    MenuItem itemToHide = menu.findItem(R.id.nav_login);
//                    itemToHide.setVisible(true);
//                    MenuItem itemToHide2 = menu.findItem(R.id.nav_logout);
//                    itemToHide2.setVisible(false);

                }
                else if (!HomeFragment.emailUser.equals("ko_co"))
                {
//                    Menu menu = activityMain.bottomNavigationView.getMenu();
//                    MenuItem itemToHide = menu.findItem(R.id.nav_logout);
//                    itemToHide.setVisible(true);
//                    MenuItem itemToHide2 = menu.findItem(R.id.nav_login);
//                    itemToHide2.setVisible(false);

                }


        activityMain.bottomNavigationView.setSelectedItemId(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                .commit();


        activityMain.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             switch (item.getItemId()) {
                 case R.id.nav_home:
                     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                             .commit();
                     return true;
                 case R.id.nav_card:
                     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CardFragment())
                             .commit();

                     return true;
                 case R.id.nav_saveProduct:
                     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SaveProductFragment())
                             .commit();

                     return true;

                 case R.id.nav_user:

                     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AccountFragment())
                             .commit() ;
                     // Xử lý khi mục Profile được chọn
                     return true;


             }
             return false;
         }
     });






    }


}