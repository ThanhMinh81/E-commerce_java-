package com.example.appecommercephp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appecommercephp.databinding.FragmentAccount2Binding;
import com.example.appecommercephp.view.ShoppingCartActivity;
import com.example.appecommercephp.view.listOrderAdminActivity;


public class AccountFragment extends Fragment {


    FragmentAccount2Binding fragmentAccount ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentAccount = FragmentAccount2Binding.inflate(inflater,container,false);

        enableEditText(false);

        fragmentAccount.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentAccount.updateButton.getText().equals("Cập nhật thông tin"))
                {
                    enableEditText(true);
                }else {
                    enableEditText(false);
                }

            }
        });

        fragmentAccount.showPws.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    fragmentAccount.tvpasswordUser.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    fragmentAccount.tvpasswordUser.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });

        fragmentAccount.viewShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        fragmentAccount.yourShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), listOrderAdminActivity.class);
                startActivity(intent);
            }
        });

        return  fragmentAccount.getRoot();


    }

    private void enableEditText(boolean check) {

        if (!check)
        {
            fragmentAccount.tvemailUser.setEnabled(false);
            fragmentAccount.tvnameUser.setEnabled(false);
            fragmentAccount.tvpasswordUser.setEnabled(false);
            fragmentAccount.updateButton.setText("Cập nhật thông tin");

        }else {
            fragmentAccount.tvemailUser.setEnabled(true);
            fragmentAccount.tvnameUser.setEnabled(true);
            fragmentAccount.tvpasswordUser.setEnabled(true);
            fragmentAccount.updateButton.setText("Lưu thông tin");
        }

    }
}