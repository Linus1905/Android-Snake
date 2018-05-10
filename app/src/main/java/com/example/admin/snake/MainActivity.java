package com.example.admin.snake;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.net.Uri;
import android.widget.TextView;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private  Button hoch;
    private  Button rechts;
    private  Button unten;
    private  Button links;
    private TextView punkteText;
    private TextView punkteTextView;
    private  MediaPlayer mp = new MediaPlayer();
    private TextView highscoreText;
    private TextView highscoreTextView;
    private boolean pause = false;
    private int mediaPlayerStop;

    private SchlangeView schlangeView;
    private SchlangeModel schlangeModel;
    private SchlangeController schlangeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hoch = (Button) findViewById(R.id.buttonhoch);
        rechts = (Button) findViewById(R.id.buttonrechts);
        unten = (Button) findViewById(R.id.buttonunten);
        links = (Button) findViewById(R.id.buttonlinks);
        punkteText = (TextView) findViewById(R.id.textView1);
        punkteTextView = (TextView) findViewById(R.id.textView2);
        highscoreText = (TextView) findViewById(R.id.textView3);
        highscoreTextView = (TextView) findViewById(R.id.textView4);

       schlangeView = new SchlangeView(this);
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(900, 900);
       schlangeView.setLayoutParams(params);
       RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout1);
       schlangeView.setBackgroundColor(Color.YELLOW);
       layout.addView(schlangeView);
    }

    public   Button  getHoch() { return hoch; }
    public  Button getRechts() { return rechts;}
    public  Button getUnten() { return unten; }
    public Button getLinks() { return links; }
    public TextView getPunkteTextView() { return punkteTextView;}
    public  MediaPlayer getMediaPlayer(){ return mp;}
    public  TextView getHighscoreTextView(){return highscoreTextView;}
    public boolean getPause() { return pause;}


    public void initalisiereMediaPlayer() {
        try {
            mp.setDataSource( this,Uri.parse("android.resource://com.example.admin.snake/raw/hintergrund"));
            mp.prepare();
        } catch (Exception e) {e.printStackTrace();}
        mp.start();
        mp.setLooping(true);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        pause = false;
        mp.seekTo(mediaPlayerStop);
        mp.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause = true;
        mp.pause();
        mediaPlayerStop = mp.getCurrentPosition();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }
}
