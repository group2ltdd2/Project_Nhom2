package com.example.mymusicplayerapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mymusicplayerapp.adapter.VideoAdapter;
import com.example.mymusicplayerapp.datamodels.PlayVideo;
import com.example.mymusicplayerapp.adapter.VideoAdapter;
import com.example.mymusicplayerapp.datamodels.PlayVideo;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
    ListView listView;
    VideoAdapter videoAdapter;
    ArrayList<PlayVideo> arrayList;
    int currentid = 0;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setTitle( "Video player" );
        setContentView( R.layout.layout_video );
        ImportData();
        init();
        VideoView videoView =(VideoView)findViewById(R.id.videoview);
        //Set MediaController  to enable play, pause, forward, etc options.
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        //Location of Media File
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aodm);
        //Starting VideView By Setting MediaController and URI
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        final TextView txtTile  = (TextView) findViewById( R.id.titleVideo);
        final TextView txtAuthor = (TextView) findViewById( R.id.author);
        final VideoView videoView1 = (VideoView) findViewById( R.id.videoview);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        txtTile.setText( "Anh ở đây mà" );
                        txtAuthor.setText( "Đức Phúc" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aodm) );
                        break;
                    case 1:
                        txtTile.setText( "Anh ở đâu thế" );
                        txtAuthor.setText( "AMEE x B RAY" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aodt) );
                        break;
                    case 2:
                        txtTile.setText( "Cuộc vui cô đơn" );
                        txtAuthor.setText( "Lê Bảo Bình" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cvcd) );
                        break;
                    case 3:
                        txtTile.setText( "Xin một lần ngoại lệ" );
                        txtAuthor.setText( "Trịnh Đình Quang x Keyo" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.xmlnl) );
                        break;
                    case 4:
                        txtTile.setText( "Anh ở đây mà" );
                        txtAuthor.setText( "Đức Phúc" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aodm) );
                        break;
                    case 5:
                        txtTile.setText( "Anh ở đâu thế" );
                        txtAuthor.setText( "AMEE x B RAY" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aodt) );
                        break;
                    case 6:
                        txtTile.setText( "Cuộc vui cô đơn" );
                        txtAuthor.setText( "Lê Bảo Bình" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cvcd) );
                        break;
                    case 7:
                        txtTile.setText( "Xin một lần ngoại lệ" );
                        txtAuthor.setText( "Trịnh Đình Quang x Keyo" );
                        videoView1.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.xmlnl) );
                        break;                    default:


                }
                videoView1.setMediaController( new MediaController( VideoActivity.this ) );
                videoView1.requestFocus();
                videoView1.start();
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
        arrayList.add( new PlayVideo(++currentid,"Cuộc vui cô đơn","Lê Bảo Bình", R.drawable.video_cvcd) );
        arrayList.add( new PlayVideo(++currentid,"Xin một lần ngoại lệ","Trịnh Đình Quang x Keyo", R.drawable.xmnl) );

    }
    private void ImportData(){
        listView = (ListView) findViewById( R.id.lvideo);
        arrayList = new ArrayList<PlayVideo>(  );
        videoAdapter = new VideoAdapter( VideoActivity.this, arrayList);
        listView.setAdapter( videoAdapter);
    }
}




































