package com.example.appecommercephp.fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.appecommercephp.databinding.FragmentAboutBinding;


public class AboutFragment extends Fragment {

    String tokenB = "cDcsqckETbKsp8ilfLorG7:APA91bEQE1j-V4FvRq5K5l9NOM-bxV43zx8bLRG034sJPAM_pyTe7N7dtOiiDQD6VidRcODYaPU5KpwKDeJgPQoXDH12MMyGb2L3mBNvGSG4DqHALm0kRD-lTmhJkZOr-tzuwQY93vEt";
    String title = "Thông báo từ thiết bị A";
    String message = "Nội dung thông báo";

    FragmentAboutBinding fragmentAboutBinding ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentAboutBinding = FragmentAboutBinding.inflate(inflater,container,false);

        fragmentAboutBinding.btnClickkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return  fragmentAboutBinding.getRoot();



    }

}