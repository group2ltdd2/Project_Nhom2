package com.example.mymusicplayerapp.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.mymusicplayerapp.MyActivity;
import com.example.mymusicplayerapp.R;
import com.example.mymusicplayerapp.VideoListActivity;
import com.example.mymusicplayerapp.R;
import com.example.mymusicplayerapp.VideoListActivity;

public class HomeAcitivity extends AppCompatActivity {
    private ImageButton imgBtnCallListVideo;
    private ImageButton imgBtnCallListMusic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        setTitle( "App Media" );
        imgBtnCallListVideo = (ImageButton) findViewById(R.id.btnVideoView);
        imgBtnCallListVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAcitivity.this, VideoListActivity.class);
                startActivity(intent);
            }
        });

        imgBtnCallListMusic = (ImageButton) findViewById(R.id.btnMucsicView);
        imgBtnCallListMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAcitivity.this, MyActivity.class);
                startActivity(intent);
            }
        });
    }
}
