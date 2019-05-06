package com.example.mymusicplayerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Song> mListSong;
    public SongAdapter(Context context, ArrayList<Song> listSong){
        this.mListSong = listSong;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListSong.size();
    }

    @Override
    public Song getItem(int position) {
        return mListSong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_song_listview, parent, false);
            viewHolder.tvSongName = (TextView) convertView.findViewById(R.id.tv_songname);
            viewHolder.tvArtist = (TextView) convertView.findViewById(R.id.tv_artist);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Song song = mListSong.get(position);
        viewHolder.tvSongName.setText(song.getName());
        viewHolder.tvArtist.setText(song.getArtist());
        return convertView;
    }

    private class ViewHolder{
        TextView tvSongName;
        TextView tvArtist;
    }
}
