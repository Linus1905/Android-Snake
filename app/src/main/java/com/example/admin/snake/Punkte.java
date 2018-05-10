package com.example.admin.snake;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class Punkte {

    private int highscore;
    private static int punkte;
    private MainActivity mainActivity;

    public Punkte(Context context) {
     mainActivity = (MainActivity) context;
    }

    public void schreibeHighscore() {
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HIGHSCORE", highscore);
        editor.commit();
    }

    public int ladeHighscore() {
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        return pref.getInt("HIGHSCORE",0);
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public  static int getPunkte() {
        return punkte;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getHighscore() {
        return  highscore;
    }

    public static void resetPunkte() {
        punkte =0;
    }
}