package com.example.admin.snake;

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
    //private Schlange schlange;
    private TextView PunkteText;
    private TextView PunkteTextView;
    private  MediaPlayer mp = new MediaPlayer();
    private TextView HighscoreText;
    private TextView HighscoreTextView;
    private Timer timer;
    private boolean Pause = false;
    private int MediaPlayerStop;
    private SchlangeView schlangeView;
    private SchlangeModel schlangeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hoch = (Button) findViewById(R.id.button1);
        rechts = (Button) findViewById(R.id.button2);
        unten = (Button) findViewById(R.id.button3);
        links = (Button) findViewById(R.id.button4);
        PunkteText = (TextView) findViewById(R.id.textView1);
        PunkteTextView = (TextView) findViewById(R.id.textView2);
        HighscoreText = (TextView) findViewById(R.id.textView3);
        HighscoreTextView = (TextView) findViewById(R.id.textView4);

       /* schlange = new Schlange(this);                                                           // 1200
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(900, 900);
        schlange.setLayoutParams(params);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout1);
        schlange.setBackgroundColor(Color.YELLOW);
        layout.addView(schlange);
        timer = schlange.getTimer();
        initalisiereMediaPlayer();
*/    // initalisiereMediaPlayer();
       schlangeModel = new SchlangeModel(this);
       schlangeView = new SchlangeView(this);
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(900, 900);
       schlangeView.setLayoutParams(params);
       RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout1);
       schlangeView.setBackgroundColor(Color.YELLOW);
       layout.addView(schlangeView);
       schlangeView.setOnClickListener(new SchlangeController(this));



    }

    public   Button  getHoch() { return hoch; }
    public  Button getRechts() { return rechts;}
    public  Button getUnten() { return unten; }
    public Button getLinks() { return links; }
    public TextView getPunkteTextView() { return PunkteTextView;}
    public  MediaPlayer getMediaPlayer(){ return mp;}
    public  TextView getHighscoreTextView(){return HighscoreTextView;}
    public boolean getPause() { return Pause;}


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
        Pause = false;
        //mp.seekTo(MediaPlayerStop);
       // mp.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        Pause = true;
       // mp.pause();
      // MediaPlayerStop = mp.getCurrentPosition();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }
}
