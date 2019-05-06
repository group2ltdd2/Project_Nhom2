package com.example.mymusicplayerapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MyActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {
    private ListView lvList;
    private SeekBar sbSeekbar;
    private ImageView ivLove, ivBack, ivPlay, ivNext, ivShuffle, rotate;
    private TextView tvSongName, tvArtist, tvOrder, tvDuration;
    private MediaManager mediaManager;
    private ArrayList<Song> mListSong;
    private SongAdapter adapter;
    private static final int LEVEL_PLAY = 0;
    private static final int LEVEL_PAUSE = 1;
    private int levelPlay = LEVEL_PLAY;
    private static final int LEVEL_ON = 1;
    private static final int LEVEL_OFF = 0;
    private int levelShuffle = LEVEL_OFF;
    private static final int LEVEL_LOVE = 1;
    private static final int LEVEL_UNLOVE = 0;
    private int levelLove = LEVEL_UNLOVE;
    private int progress;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initData();
        initView();
    }

    private void initData() {
        mediaManager = new MediaManager(this);
        mListSong = mediaManager.getmListSong();
        adapter = new SongAdapter(this, mListSong);

    }

    private void initView() {
        lvList = (ListView) findViewById(R.id.lv_list);
        lvList.setOnItemClickListener(this);
        lvList.setAdapter(adapter);
        tvSongName = (TextView) findViewById(R.id.tv_song_name);
        tvArtist = (TextView) findViewById(R.id.tv_artist);
        tvOrder = (TextView) findViewById(R.id.tv_order);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivLove = (ImageView) findViewById(R.id.iv_love);
        ivPlay = (ImageView) findViewById(R.id.iv_play);
        ivNext = (ImageView) findViewById(R.id.iv_next);
        ivShuffle = (ImageView) findViewById(R.id.iv_shuffle);
        sbSeekbar = (SeekBar) findViewById(R.id.sb_seek);
        sbSeekbar.setMax(mediaManager.getCurrentSong().getDuration());
        sbSeekbar.setOnSeekBarChangeListener(this);

        ivBack.setOnClickListener(this);
        ivLove.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivShuffle.setOnClickListener(this);

        rotate = (ImageView) findViewById(R.id.rotate);
        updateSong();
    }

    private void stopAnimation(){
        rotate.animate().cancel();
    }

    private void startAnimation() {
        rotate = findViewById(R.id.rotate);
        Runnable mRun = new Runnable() {
            @Override
            public void run() {
                rotate.animate().rotationBy(360).withEndAction(this).setDuration(5000).setInterpolator(new LinearInterpolator()).start();
            }
        };
        rotate.animate().rotationBy(360).withEndAction(mRun).setDuration(5000).setInterpolator(new LinearInterpolator()).start();
    }

    public void updateSong(){
        Song song = mediaManager.getCurrentSong();
        setInfoSong(song);
        new UpdateSeekbar().execute();
    }

    private void setInfoSong(Song song) {
        tvSongName.setText(song.getName());
        tvArtist.setText(song.getArtist());
        tvOrder.setText(String.valueOf(mediaManager.getIndex()) + "/" + String.valueOf(mediaManager.getmListSong().size()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                doBack();
                break;
            case R.id.iv_love:
                doLove();
                break;
            case R.id.iv_play:
                doPlay();
                break;
            case R.id.iv_next:
                doNext();
                break;
            case R.id.iv_shuffle:
                doShuffle();
                break;
            default:
                break;
        }
    }

    private void doLove() {
        if (levelLove == LEVEL_UNLOVE){
            ivLove.setImageLevel(LEVEL_LOVE);
            levelLove=LEVEL_LOVE;
        }else{
            ivLove.setImageLevel(LEVEL_UNLOVE);
            levelLove=LEVEL_UNLOVE;
        }
    }

    private void doBack() {
        if (mediaManager.back()){
            ivPlay.setImageLevel(LEVEL_PAUSE);
            updateSong();
            startAnimation();
        }
    }

    private void doPlay() {
        if (mediaManager.play()){
            ivPlay.setImageLevel(LEVEL_PAUSE);
            updateSong();
            startAnimation();
        }else {
            ivPlay.setImageLevel(LEVEL_PLAY);
            stopAnimation();
        }
    }

    private void doNext() {
        if (mediaManager.next()){
            ivPlay.setImageLevel(LEVEL_PAUSE);
            updateSong();
            startAnimation();
        }
    }

    private void doShuffle() {
        if (mediaManager.isShuffle()){
            mediaManager.setShuffle(false);
            ivShuffle.setImageLevel(LEVEL_OFF);
            Toast.makeText(this, "Shuffle Off", Toast.LENGTH_SHORT).show();
        }else {
            mediaManager.setShuffle(true);
            ivShuffle.setImageLevel(LEVEL_ON);
            Toast.makeText(this, "Shuffle On", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mediaManager.play(position);
        updateSong();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.progress = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaManager.seek(progress);
        seekBar.setProgress(seekBar.getProgress());
    }

    private class UpdateSeekbar extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            while (mediaManager.isPlaying()){
                try {
                    Thread.sleep(1000);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            tvDuration.setText(mediaManager.getTimeText());
            sbSeekbar.setProgress(mediaManager.getCurrentTime());
        }
    }
}
