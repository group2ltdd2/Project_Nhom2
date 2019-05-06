package com.example.mymusicplayerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mymusicplayerapp.adapter.VideoAdapter;
import com.example.mymusicplayerapp.datamodels.PlayVideo;

import java.util.ArrayList;

public class VideoListActivity extends AppCompatActivity {
    private Button btnCallVideo;
    ListView listView;
    VideoAdapter videoAdapter;
    ArrayList<PlayVideo> arrayList;
    int currentid = 0;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setTitle( "Video List" );
        setContentView( R.layout.layout_list_video );
        ImportData();
        final TextView txtTile = (TextView) findViewById( R.id.titleVideo);
        init();
        final TextView txtAuthor = (TextView) findViewById( R.id.author);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(VideoListActivity.this, VideoActivity.class);
                startActivity(i);
            }
        } );
    }
    private void init(){
        arrayList.add( new PlayVideo(++currentid,"Anh ở đây mà","Đức Phúc", R.drawable.video_aodm) );
        arrayList.add( new PlayVideo(++currentid,"Anh ở đâu thế","AMEE x B RAY", R.drawable.video_adodt) );
        arrayList.add( new PlayVideo(++currentid,"Cuộc vui cô đơn","Lê Bảo Bình", R.drawable.video_cvcd) );
        arrayList.add( new PlayVideo(++currentid,"Xin một lần ngoại lệ","Trịnh Đình Quang x Keyo", R.drawable.xmnl) );
        arrayList.add( new PlayVideo(++currentid,"Anh ở đây mà","Đức Phúc", R.drawable.video_aodm) );
        arrayList.add( new PlayVideo(++currentid,"Anh ở đâu thế","AMEE x B RAY", R.drawable.video_adodt) );

    }
    private void ImportData(){
        listView = (ListView) findViewById( R.id.lvideo);
        arrayList = new ArrayList<PlayVideo>(  );
        videoAdapter = new VideoAdapter( VideoListActivity.this, arrayList);
        listView.setAdapter( videoAdapter);
    }
}




































