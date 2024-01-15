package com.example.appecommercephp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.appecommercephp.R;

public class LoadingAlert {

    Activity activity ;
    AlertDialog dialog ;

    public LoadingAlert(Activity activity) {
        this.activity = activity;

    }

    void startAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.dialog_layout,null));

        builder.setCancelable(true);

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


    }

    void closeAlertDialog()
    {
        dialog.dismiss();
    }


}
