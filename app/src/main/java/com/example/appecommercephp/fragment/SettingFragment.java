package com.example.appecommercephp.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.FragmentSettingBinding;
import com.example.appecommercephp.sendnotifire;
import com.example.appecommercephp.view.Login;


public class SettingFragment extends Fragment {

    FragmentSettingBinding fragmentSettingBinding;
    private NotificationManager notificationManager ;

    static String CHANNEL_ID = "1";

    com.example.appecommercephp.sendnotifire Sendnotifire;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

             fragmentSettingBinding = FragmentSettingBinding.inflate(inflater, container, false);

             Sendnotifire = new sendnotifire();

              fragmentSettingBinding.btnClick.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      Sendnotifire.execute();

                  }
              });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("channel1", "Channel 1", NotificationManager.IMPORTANCE_HIGH);
                NotificationManager manager = this.getContext().getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
           return fragmentSettingBinding.getRoot() ;

    }

    public void notificatonButton()
    {
            final  String CHANNEL_ID = "channel1";

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.img);

        Intent intent = new Intent(getActivity(), Login.class);
        PendingIntent playContentIntent = PendingIntent.getActivity(getContext(),0,intent,0);


        Intent intentCancel = new Intent(getActivity(), Login.class);
        PendingIntent playCancelIntern = PendingIntent.getActivity(getContext(),0,intent,0);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getContext(), "channel1")
                .setSmallIcon(R.drawable.baseline_notifications_none_24)
                .setContentTitle("My notification")
                .setContentText("This is a notification.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle("Custom Notification Tutorial")
                        .bigText("giờ người tìm kiếm những đắm đuối xa hoa với đời"))
                .setLargeIcon(largeIcon)
                .addAction(R.mipmap.ic_launcher,"Play",playContentIntent)
                .addAction(R.mipmap.ic_launcher,"Cancel",playCancelIntern)
                .setColor(Color.GREEN)
                .setPriority(NotificationCompat.PRIORITY_HIGH);




        NotificationManager notificationManager1  = this.getContext().getSystemService(NotificationManager.class);
        notificationManager1.notify(1,builder.build());


    }

}