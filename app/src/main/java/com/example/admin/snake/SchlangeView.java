package com.example.admin.snake;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

public class SchlangeView extends SurfaceView{

    private int aktLängeSchlange;
    private int[] koordX = new int[1000];
    private int[] koordY = new int[1000];

    private SchlangeModel schlangeModel;

    public SchlangeView(Context context) {
    super(context);
     schlangeModel = new SchlangeModel(context);
     aktLängeSchlange = schlangeModel.getAktLängeSchlange();
     koordX = schlangeModel.getKoordX();
     koordY = schlangeModel.getKoordY();;

     schlangeModel.bewegungSchlange();
     invalidate();
    }


    @Override
    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint p = new Paint();

        //p.setColor(Color.GREEN);
        //canvas.drawRect(FutterX*30, FutterY*30,FutterX*30+30,FutterY*30+30,p );


            p.setColor(Color.RED);
            canvas.drawRect(koordX[0] * 30, koordY[0] * 30, koordX[0] * 30 + 30, koordY[0] * 30 + 30, p); // Kopf
            p.setColor(Color.BLUE);

            for (int i = 1; i < aktLängeSchlange; i++) { // Rumpf
                canvas.drawRect(koordX[i] * 30, koordY[i] * 30, koordX[i] * 30 + 30, koordY[i] * 30 + 30, p);
            }

       /* if(GameOver()==true) {

            if(Punkte>HighscoreLaden()) {
                HighscoreSchreiben();
            }

            Intent intent = new Intent(getContext(), GameOverScreen.class);
            getContext().startActivity(intent);
        }
    */
    }

}
