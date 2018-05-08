package com.example.admin.snake;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;
import android.util.Log;

public class GameOverScreen extends AppCompatActivity implements View.OnTouchListener{

    private ZeichenView zw ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        zw = new ZeichenView(this);
        layout.setOnTouchListener(this);
        layout.addView(zw);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int aktion = event.getAction();

        if(aktion==MotionEvent.ACTION_DOWN) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
       return true;
        }

        return false;
    }

class ZeichenView extends View {

        ZeichenView(Context context) {
            super(context);
        }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(200);
        canvas.drawText("Game Over",50,400,p);
        p.setTextSize(75);
        canvas.drawText("Deine Punkte:"+Schlange.getPunkte(),300,600,p);
        Schlange.setPunkte();
        }
   }
}
