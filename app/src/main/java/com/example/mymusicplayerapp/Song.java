package com.example.mymusicplayerapp;

import android.media.MediaPlayer;

public class Song {
    private String name, path, album, artist;
    private int duration;

    public Song(String name, String path, String album, String artist, int duration) {
        this.name = name;
        this.path = path;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }
}
