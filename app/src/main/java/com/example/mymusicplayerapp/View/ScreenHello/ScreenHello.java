package com.example.mymusicplayerapp.View.ScreenHello;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mymusicplayerapp.R;
import com.example.mymusicplayerapp.View.Home.HomeAcitivity;
import com.example.mymusicplayerapp.R;
import com.example.mymusicplayerapp.View.Home.HomeAcitivity;

public class ScreenHello extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenhello_layout);
        setTitle( "Welcome to project team 2 " );
        //Dùng cài đặt sau 5 giây màn hình tự chuyển
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent activitymoi=new Intent(ScreenHello.this, HomeAcitivity.class);
                    startActivity(activitymoi);
                }
            }
        };
        bamgio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }

}
