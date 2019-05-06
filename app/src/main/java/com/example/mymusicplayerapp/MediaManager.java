package com.example.mymusicplayerapp;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MediaManager {
    private static final int IDLE = 0;
    private static final int PLAYING = 1;
    private static final int PAUSED = 2;
    private static final int STOPPED = 3;
    private int state = IDLE;
    private MediaPlayer mPlayer;
    private Context mContext;
    private ArrayList<Song> mListSong;
    private int mIndex;

    public boolean isShuffle() {
        return isShuffle;
    }

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    private boolean isShuffle;

    public MediaManager(Context context){
        mContext = context;
        initData();
    }

    public ArrayList<Song> getmListSong() {
        return mListSong;
    }

    private void initData(){
        mPlayer = new MediaPlayer();
        mListSong = new ArrayList<>();

        Uri audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String projection[] = new String[]{
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.DURATION
        };

        String where = MediaStore.Audio.AudioColumns.DISPLAY_NAME+" LIKE '%.mp3'";

        Cursor c = mContext.getContentResolver().query(audioUri, projection, where, null, null);
        if (c == null){
            return;
        }

        c.moveToFirst();

        int indexTitle = c.getColumnIndex(projection[0]);
        int indexData = c.getColumnIndex(projection[1]);
        int indexAlbum = c.getColumnIndex(projection[2]);
        int indexArtist = c.getColumnIndex(projection[3]);
        int indexDuration = c.getColumnIndex(projection[4]);

        String name, path, album, artist;
        int duration;

        while(!c.isAfterLast()){
            name = c.getString(indexTitle);
            path = c.getString(indexData);
            album = c.getString(indexAlbum);
            artist = c.getString(indexArtist);
            duration = c.getInt(indexDuration);
            mListSong.add(new Song(name, path, album, artist, duration));
            c.moveToNext();

        }
        c.close();
    }

    public void play(int position){
        mIndex = position;
        stop();
        play();
    }


    public boolean play(){
        try {
            if (state == IDLE || state == STOPPED){
                Song song = mListSong.get(mIndex);
                mPlayer.setDataSource(song.getPath());
                mPlayer.prepare();
                mPlayer.start();
                state = PLAYING;
                return true;
            } else if (state == PLAYING){
                mPlayer.pause();
                state = PAUSED;
                return false;
            } else {
                mPlayer.start();
                state = PLAYING;
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void stop(){
        if (state == PLAYING || state == PAUSED){
            mPlayer.stop();
            mPlayer.reset();
            state = STOPPED;
        }
    }

    public boolean back(){
        if (mIndex == 0){
            mIndex = mListSong.size();
        }
        mIndex--;
        stop();
        return play();
    }

    public boolean next(){
        if (isShuffle){
            mIndex = new Random().nextInt(mListSong.size());
        }else {
            mIndex = (mIndex+1) % mListSong.size();
        }
        stop();
        return play();
    }

    public Song getCurrentSong() {
        return getmListSong().get(mIndex);
    }

    public int getIndex() {
        return mIndex;
    }

    public boolean isPlaying() {
        return state == PLAYING || state == PAUSED;
    }

    public String getTimeText() {
        int currentTime = mPlayer.getCurrentPosition();
        int totalTime = mListSong.get(mIndex).getDuration();
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(currentTime) + "/" + dateFormat.format(totalTime);
    }

    public int getCurrentTime() {
        return mPlayer.getCurrentPosition();
    }

    public void seek(int progress) {
        mPlayer.seekTo(progress);
    }
}
