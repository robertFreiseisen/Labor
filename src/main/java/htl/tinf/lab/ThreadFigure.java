package htl.tinf.lab;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Collection;

public class ThreadFigure  extends Thread {

    private Circle right;
    private Circle left;
    private TextArea console;
    private Color color;
    private String name;

    ThreadFigure(Circle right, Circle left, TextArea console, Color color,String name) {
    this.right=right;
    this.left=left;
    this.console = console;
    this.color = color;
    this.name = name;
    }

    @Override
    public void run() {
        /*
        //create a random time when the figure wants to press the buttons
        int starttime = (int) (Math.random()*((3000-1000)+1))+1000;
        int timeBetweenPresses =1000;


        //sleep the waiting time
        try {
            Thread.sleep(starttime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //press the left button
        if (!(left.getFill() == Color.BLACK)) {
            //button already pressed from other figure
            console.appendText(name +" kann den linken Knopf nicht drücken");
        }else {
            left.setFill(color);
            console.appendText(name + " hat den linken Knopf gedürckt\n");
        }
        //wait
        try {
            Thread.sleep(timeBetweenPresses);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //press the right button
        if (!(right.getFill() == Color.BLACK)) {
            //button already pressed from other figure
            console.appendText(name +" kann den rechten Knopf nicht drücken");
        }else {
            right.setFill(color);
            console.appendText(name + " hat den rechten Knopf gedürckt\n");
        }

         */
        if(right.getFill()==Color.BLACK) {
            right.setFill(color);
            console.appendText(name + " hat den rechten Knopf gedürckt.\n");

            if(left.getFill()==Color.BLACK) {
                left.setFill(color);
                console.appendText(name + " hat den linken Knopf gedürckt.\n");
            }
        }else {
            console.appendText(name + " konnte den rechten Knopf nicht drücken.\n");
        }


    }

    public void deadLock(){
        left.setFill(color);
        console.appendText(name + " hat den linken Knopf gedürckt\n");
    }

    public void reset(){
        right.setFill(Color.BLACK);
        left.setFill(Color.BLACK);
    }



}
