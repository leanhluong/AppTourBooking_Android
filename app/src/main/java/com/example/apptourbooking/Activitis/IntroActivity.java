package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbEndpoint;
import android.os.Bundle;
import android.view.View;

import com.example.apptourbooking.R;

public class IntroActivity extends AppCompatActivity {

    private static final int NOTICIFITION_ID = 1;
    ConstraintLayout cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        cs = (ConstraintLayout) findViewById(R.id.constraintlayoutIntro);
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);

//                sendNoticification();
            }
        });

    }

//    private void sendNoticification(){
//
////        Bitmap bitmap = new BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle("Thông báo")
//                .setContentText("Đây là thông báo")
//                .setSmallIcon(R.drawable.ic_noticication)
////                .setLargeIcon(bitmap)
//                .build();
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if(notificationManager != null){
//            notificationManager.notify(NOTICIFITION_ID, notification);
//        }
//    }


}