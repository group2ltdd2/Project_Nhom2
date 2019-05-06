package com.example.mymusicplayerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymusicplayerapp.R;
import com.example.mymusicplayerapp.datamodels.PlayVideo;

import java.util.ArrayList;

public class VideoAdapter extends BaseAdapter {
    Context context;
    ArrayList<PlayVideo> playVideoList;

    public VideoAdapter(Context context, ArrayList<PlayVideo> playVideoList) {
        this.context = context;
        this.playVideoList = playVideoList;
    }

    @Override
    public int getCount() {
        return playVideoList.size();
    }

    @Override
    public Object getItem(int i) {
        return playVideoList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate( R.layout.item_listview,null);
        TextView textNameVideo = view.findViewById( R.id.nameVideo);
        TextView txtNameAuthor = view.findViewById( R.id.nameAuthor );
        ImageView imgVideo = (ImageView) view.findViewById( R.id.imgVideo );

        PlayVideo video = playVideoList.get( i );
        textNameVideo.setText( video.NameVideo );
        txtNameAuthor.setText( video.NameAuthor );
        imgVideo.setImageResource( video.Image );
        return view;
    }
}



















