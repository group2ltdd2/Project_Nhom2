package com.example.mymusicplayerapp.datamodels;

import android.content.Context;
import android.widget.ImageView;

public class PlayVideo {
    public int Id;
    public String NameVideo;
    public Integer Image;
    public String NameAuthor;
    public PlayVideo(int id, String nameVideo,String nameAuthor, Integer image) {
        Id = id;
        NameVideo = nameVideo;
        NameAuthor = nameAuthor;
        Image = image;
    }
}
