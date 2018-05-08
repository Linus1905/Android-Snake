package com.example.admin.snake;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Timer;
import java.util.TimerTask;

/*
Das Model der Schlange
 */

public class SchlangeModel implements SurfaceHolder.Callback {

   private Timer timer;
   private boolean pause;
   private TimerTask timertask;
   private  int aktLängeSchlange = 1;
   private static final int maxlänge = 1000;
   private static int[] koordX = new int[maxlänge];
   private static int[] koordY = new int[maxlänge];
   private MainActivity mainActivity;
   private Richtung richtung;


   private SchlangeController schlangeController;
   private SpielFeld spielFeld;
   private  SchlangeView schlangeView;

   public SchlangeModel(Context context) {
       mainActivity = (MainActivity) context;
       schlangeController = new SchlangeController(context);
       spielFeld = new SpielFeld(context);
       startPunktSchlange();
       bewegungSchlange();
   }

    public void startPunktSchlange() {
        koordX[0] = (int)(Math.random()*(spielFeld.getBreite()-2)+1);
        koordY[0] = (int)(Math.random()*(spielFeld.getHöhe()-2)+1);

        Log.v("KoodinateX",""+koordX[0]);
        Log.v("KoodinateY",""+koordY[0]);
   }

    public void bewegungSchlange() {
        timer = new Timer();

        timertask = new TimerTask() {

            @Override
            public void run() {
                pause= mainActivity.getPause();
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {
                        if (pause == false) {

                            for (int i = aktLängeSchlange; i > 0; i--) {
                                koordX[i] = koordX[i - 1];
                                koordY[i] = koordY[i - 1];
                                Log.v("Koodinate",""+koordX[i]);
                               Log.v("Koodinate",""+koordY[i]);
                            }
                            RichtungÄndern();
                            essen();

                        }
                    }
                });
            }
        };

        timer.schedule(timertask, 0, 150);
    }

    public void RichtungÄndern() {

        richtung = schlangeController.getRichtung();
        if (richtung != null) {
            switch (richtung) {
                case Hoch:
                    koordY[0] = koordY[0] - 1;
                    Log.v("KoorinateY", "" + koordX[0]);
                    Log.v("KoodinateX", "" + koordY[0]);
                    break;
                case Unten:
                    koordY[0] = koordY[0] + 1;
                    break;
                case Rechts:
                    koordX[0] = koordX[0] + 1;
                    break;
                case Links:
                    koordX[0] = koordX[0] - 1;
                    break;
            }
        }
    }

    public void essen() {
        //if(koordX[0]==FutterX && KoordY[0]==FutterY) {

            aktLängeSchlange= aktLängeSchlange+1;
            //Punkte = Punkte +1;
            //zufälligerApfel();
            //PunkteTextView.setText(""+Punkte);
            //invalidate();
       // }
    }

    public boolean GameOver() {

        for(int i=1; i<aktLängeSchlange; i++) {
            if(koordX[0]==koordX[i] && koordY[0] == koordY[i])	{
                timer.cancel();
                return true;
            }
        }
        if(koordX[0]<0 || koordX[0]>29 || koordY[0]<0 ||koordY[0]>29 ) {

            timer.cancel();
            return	true;
        }
        return false;
    }

    public int getAktLängeSchlange() {
       return aktLängeSchlange;
    }

    public static int[] getKoordX() {
       return koordX;
    }

    public static int[] getKoordY() {
       return koordY;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}
}
