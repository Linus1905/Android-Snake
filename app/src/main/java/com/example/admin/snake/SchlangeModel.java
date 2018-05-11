package com.example.admin.snake;

import android.content.Context;
import java.util.LinkedList;


import android.graphics.Point;
import android.util.Log;

/*

 */

public class SchlangeModel {

   private LinkedList<Point> snake = new LinkedList<Point>();
   private SchlangeController schlangeController;
   private final int feldHöhe=30;
   private final int feldBreite=30;
   private Futter futter;
   private int futterX;
   private int futterY;
   private int aktuellePunkte =0;
   private Punkte punkte;


   public SchlangeModel(Context context) {
       futter = new Futter();
       futter.zufälligesEssen();
       startPunktSchlange();
       punkte = new Punkte(context);
   }

    public void startPunktSchlange() {
       int randomX = (int) (Math.random() * (feldBreite-2)+1);
       int randomY = (int) (Math.random()* (feldHöhe-2)+1);
       snake.addFirst(new Point(randomX,randomY));
   }

    public void bewegungSchlange(int richtungX, int richtungY) {

        for (int i = snake.size() - 1; i > 0; i--) {
           Point tmp = snake.get(i);
            tmp.set(snake.get(i-1).x,snake.get(i-1).y);
        }

        snake.getFirst().x += richtungX;
        snake.getFirst().y += richtungY;
        essen();
   }

   public void essen() {
       futterX = futter.getFutterX();
       futterY = futter.getFutterY();

       if (snake.getFirst().x == futterX && snake.getFirst().y == futterY) { // eat Apple
           snake.addLast(new Point(snake.getLast().x-1,snake.getLast().y));
           futter.zufälligesEssen();
           aktuellePunkte = aktuellePunkte+1;
           punkte.setPunkte(aktuellePunkte);
       }
   }

   public boolean gameOver() {

        for(int i=1; i<snake.size(); i++) {
                if (snake.getFirst().x == snake.get(i).x && snake.getFirst().y == snake.get(i).y) {
                    return true;
                }
            }

        if (snake.getFirst().x < 0 || snake.getFirst().x > 29 || snake.getFirst().y < 0 || snake.getFirst().y > 29) {
            return true;
        }
   return false;
    }

    public Punkte getPunkte() {
       return punkte;
    }

    public Futter getFutter() {
       return futter;
    }

    public LinkedList<Point> getSnake() {
        return snake;
    }

}
