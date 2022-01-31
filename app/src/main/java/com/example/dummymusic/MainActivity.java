package com.example.dummymusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    ImageButton pp;
    MediaPlayer mediaPlayer;
    TextView displaying;
    public boolean play = false;
    int selected = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displaying = findViewById(R.id.displaying);
        mediaPlayer = MediaPlayer.create(this,R.raw.music1);
        pp = findViewById(R.id.playpause);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration()/1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer!=null && b){
                    mediaPlayer.seekTo(i*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        Handler mHandler = new Handler();
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

    }

    public void playmusic(View view){
        play = !play;
        if(play){
            mediaPlayer.start();
            pp.setImageResource(android.R.drawable.ic_media_pause);
        }
        else{
            mediaPlayer.pause();
            pp.setImageResource(android.R.drawable.ic_media_play);
        }
    }
    public void selectedplus(View view) throws IOException {
        if(selected==5){
            selected=1;
        }
        else{
            selected++;
        }
        try {
            Resources res = getResources(); //resource handle
            String url = "music" +selected;
            InputStream is = getClass().getClassLoader().getResourceAsStream("raw/");
            Integer resIdSound = res.getIdentifier (url,  "raw", this.getPackageName());
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, resIdSound);
                seekBar.setMax(mediaPlayer.getDuration()/1000);
                int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                seekBar.setProgress(mCurrentPosition);
            } mediaPlayer.start();
            if(selected==1){
                displaying.setText("Playing : Default Music");
            }
            if(selected==2){
                displaying.setText("Playing : Life Goes on by Oliver Tree");
            }
            if(selected==3){
                displaying.setText("Playing : Happier by Marshmello ft. Bestille");
            }
            if(selected==4){
                displaying.setText("Playing : Hari Puttar Theme by Mahesh Raghwan");
            }if(selected==5){
                displaying.setText("Playing : The Nights by Avicci");
            }

        } catch(Exception e) { e.printStackTrace(); }
    }
    public void selectedminus(View view) throws IOException {
        if(selected==1){
            selected=5;
        }
        else{
            selected--;
        }
        try {
            Resources res = getResources(); //resource handle
            String url = "music" +selected;
            InputStream is = getClass().getClassLoader().getResourceAsStream("raw/");
            Integer resIdSound = res.getIdentifier (url,  "raw", this.getPackageName());
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, resIdSound);
                seekBar.setMax(mediaPlayer.getDuration()/1000);
                int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                seekBar.setProgress(mCurrentPosition);
            } mediaPlayer.start();
            if(selected==1){
                displaying.setText("Playing : Default Music");
            }
            if(selected==2){
                displaying.setText("Playing : Life Goes on by Oliver Tree");
            }
            if(selected==3){
                displaying.setText("Playing : Happier by Marshmello ft. Bestille");
            }
            if(selected==4){
                displaying.setText("Playing : Hari Puttar Theme by Mahesh Raghwan");
            }if(selected==5){
                displaying.setText("Playing : The Nights by Avicci");
            }
        } catch(Exception e) { e.printStackTrace(); }

    }
}