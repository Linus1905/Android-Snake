package com.example.admin.snake;

/*
create random food
 */

public class Futter {

    private int futterX;
    private int futterY;
    private final int feldHöhe = 30;
    private final int feldBreite = 30;

    public void zufälligesEssen() {
        futterX= (int) (Math.random() * (feldBreite-2)+1);
        futterY= (int) (Math.random()* (feldHöhe-2)+1);
    }

    public int getFutterX() {
        return futterX;
    }

    public int getFutterY() {
        return futterY;
    }

}
