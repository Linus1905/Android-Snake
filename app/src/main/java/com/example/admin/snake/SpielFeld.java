package com.example.admin.snake;

import android.content.Context;
import android.content.SharedPreferences;

public class SpielFeld {

    private MainActivity mainActivity;
    private int breite = 30;
    private int höhe = 30;
    private int Feld[][] = new int[breite][höhe];
    private int Punkte= 0;

public SpielFeld(Context context) {
    mainActivity = new MainActivity();
}


    public void HighscoreSchreiben() {
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HIGHSCORE", Punkte);
        editor.commit();
    }

    public  int HighscoreLaden() {
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        return pref.getInt("HIGHSCORE",0);
    }

public int getPunkte() {
    return Punkte;
}

public int getBreite(){
    return breite;
}

public int getHöhe() {
    return höhe;
}

}
