package com.example.admin.snake;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SchlangeController implements View.OnClickListener {

    private MainActivity mainActivity;
    private Richtung richtung;
    private Button hoch;
    private Button rechts;
    private Button unten;
    private Button links;

    public SchlangeController(Context context) {
        mainActivity = (MainActivity) context;
        hoch = mainActivity.getHoch();
        links = mainActivity.getLinks();
        rechts = mainActivity.getRechts();
        unten = mainActivity.getUnten();

        hoch.setOnClickListener(this);
        rechts.setOnClickListener(this);
        links.setOnClickListener(this);
        unten.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                richtung = Richtung.Hoch;
                Log.v("Richtung",""+richtung);

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

    public Richtung getRichtung() {
        return richtung;
    }
}
