package com.example.admin.snake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.TextView;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import java.util.TimerTask;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;

import java.util.Timer;


public class Schlange extends SurfaceView implements View.OnClickListener,SurfaceHolder.Callback {

    private int maxLänge =1000;
    private int aktLängeSchlange = 1;

    private Richtung richtung;
    private int KoordX[] = new int[maxLänge];
    private int KoordY[] = new int[maxLänge];

    private int breite = 30;
    private int höhe = 30;
    private int Feld[][] = new int[breite][höhe];
    private static int Punkte = 0;

    private MainActivity activity ;
    //private Timer timer = new Timer();
    private Timer timer;
    private TimerTask timertask;
    private int FutterX, FutterY;
    private TextView PunkteTextView;
    private MediaPlayer mp = new MediaPlayer();
    private TextView HighscoreTextView ;

    private Button hoch;
    private Button rechts;
    private Button unten;
    private Button links;
    private boolean Pause =false;

    public Schlange(Context context) {
        super(context);
        activity = (MainActivity) context;
        Pause = activity.getPause();

        hoch = activity.getHoch();
        links = activity.getLinks();
        rechts = activity.getRechts();
        unten = activity.getUnten();
        HighscoreTextView = activity.getHighscoreTextView();
        PunkteTextView = activity.getPunkteTextView();

        hoch.setOnClickListener(this);
        rechts.setOnClickListener(this);
        links.setOnClickListener(this);
        unten.setOnClickListener(this);
        StartPunktSchlange();
        zufälligerApfel();
        PunkteTextView.setText("0");
        HighscoreTextView.setText(""+HighscoreLaden());
        //timer.schedule(new BewegungSchlange(), 0,125);
                bewegungSchlange();
        }

 class BewegungSchlange extends TimerTask {

    public void run() {

          activity.runOnUiThread(new TimerTask() {

              public void run() {
                  for (int i = aktLängeSchlange; i > 0; i--) {
                      KoordX[i] = KoordX[i - 1];
                      KoordY[i] = KoordY[i - 1];
                  }

                  RichtungÄndern();
                  essen();
                  invalidate();

              }
          });
       }
      }


      public void bewegungSchlange() {
          timer = new Timer();

              timertask = new TimerTask() {

                  @Override
                  public void run() {
                      Pause= activity.getPause();
                      activity.runOnUiThread(new TimerTask() {

                          @Override
                          public void run() {
                              if (Pause == false) {

                                  for (int i = aktLängeSchlange; i > 0; i--) {
                                      KoordX[i] = KoordX[i - 1];
                                      KoordY[i] = KoordY[i - 1];
                                  }
                                  RichtungÄndern();
                                  essen();
                                  invalidate();
                              }
                            }
                          });
                  }
              };
              timer.schedule(timertask, 1000, 150);
          }


public void StartPunktSchlange() {
        KoordX[0] = (int)(Math.random()*(breite-2))+1;
        KoordY[0] = (int)(Math.random()*(höhe-2))+1;
}

    public void RichtungÄndern() {
if(richtung!=null) {
    switch (richtung) {
        case Hoch:
            KoordY[0] = KoordY[0] - 1;
            break;
        case Unten:
            KoordY[0] = KoordY[0] + 1;
            break;
        case Rechts:
            KoordX[0] = KoordX[0] + 1;
            break;
        case Links:
            KoordX[0] = KoordX[0] - 1;
            break;
    }
  }
}

public static int getPunkte() {
        return Punkte;
}

public  static void setPunkte() {
        Punkte=0;
}

    public void essen() {
        if(KoordX[0]==FutterX && KoordY[0]==FutterY) {
            try {
                mp.setDataSource( getContext(), Uri.parse("android.resource://com.example.admin.snake/raw/apfel"));
                mp.prepare();
            } catch (Exception e) {e.printStackTrace();}
            mp.start();

            try {
                mp.setDataSource(getContext(), Uri.parse("android.resource://com.example.admin.snake/raw/hintergrund"));
            mp.setLooping(true);
            } catch(Exception ex) {ex.printStackTrace();}

            aktLängeSchlange= aktLängeSchlange+1;
            Punkte = Punkte +1;
            zufälligerApfel();
            PunkteTextView.setText(""+Punkte);
            invalidate();
        }
    }

    public void HighscoreSchreiben() {
        SharedPreferences pref = getContext().getSharedPreferences("GAME",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HIGHSCORE", Punkte);
        editor.commit();
    }

    public  int HighscoreLaden() {
        SharedPreferences pref = getContext().getSharedPreferences("GAME",0);
        return pref.getInt("HIGHSCORE",0);
    }

    public void zufälligerApfel() {
        FutterX = (int) (Math.random()*(breite-2));
        FutterY = (int) (Math.random()*(höhe-2));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                richtung = Richtung.Hoch;
                break;
            case R.id.button2:
                richtung = Richtung.Rechts;
                break;
            case R.id.button3:
                richtung = Richtung.Unten;
                break;
            case R.id.button4:
                richtung = Richtung.Links;
                break;
        }
    }

    public boolean GameOver() {

        for(int i=1; i<aktLängeSchlange; i++) {
            if(KoordX[0]==KoordX[i] && KoordY[0] == KoordY[i])	{
                timer.cancel();
                return true;
            }
        }
        if(KoordX[0]<0 || KoordX[0]>29 || KoordY[0]<0 ||KoordY[0]>29 ) {

            timer.cancel();
            return	true;
        }
        return false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

    @Override
    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint p = new Paint();


            p.setColor(Color.GREEN);
            canvas.drawRect(FutterX * 30, FutterY * 30, FutterX * 30 + 30, FutterY * 30 + 30, p);

            p.setColor(Color.RED);
            canvas.drawRect(KoordX[0] * 30, KoordY[0] * 30, KoordX[0] * 30 + 30, KoordY[0] * 30 + 30, p); // Kopf
            p.setColor(Color.BLUE);

            for (int i = 1; i < aktLängeSchlange; i++) { // Rumpf
                canvas.drawRect(KoordX[i] * 30, KoordY[i] * 30, KoordX[i] * 30 + 30, KoordY[i] * 30 + 30, p);
            }

        if(GameOver()==true) {

            if(Punkte>HighscoreLaden()) {
                HighscoreSchreiben();
              }

              Intent intent = new Intent(getContext(), GameOverScreen.class);
              getContext().startActivity(intent);
        }
    }

public Timer getTimer() {
        return timer;
}

}




