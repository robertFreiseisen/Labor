package htl.tinf.lab;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ThreadFigureSync extends Thread {

    //the 2 circles aka buttons on the table
    private Circle right;
    private Circle left;

    //the console on the bottom
    private TextArea console;
    //individual values of the thread
    private Color color;
    private String name;
    //visualzing the amount of pressed buttons
    private int pressedButtonCount =0;
    private TextArea headText;

    ThreadFigureSync(Circle right, Circle left, TextArea console, Color color, String name) {
    this.right=right;
    this.left=left;
    this.console = console;
    this.color = color;
    this.name = name;
    }

    @Override
    public void run() {
        //TODO  DO SOMETHING SYNCED HERE
        sleep((int)(Math.random() * ((15 - 1) + 1)) + 1);
        if(left.getFill().equals(Color.BLACK)) {
            left.setFill(color);
            console.appendText(name+" Hat den linken Knopf gedrückt\n");

            sleep((int)(Math.random() * ((15 - 1) + 1)) + 1);
            if(right.getFill().equals(Color.BLACK)) {
                right.setFill(color);
                console.appendText(name+" Hat den rechten Knopf gedrückt\n");
            }else {
                console.appendText(name+" Konnte den rechten Knopf NICHT drücken\n");
            }

        }else {
            console.appendText(name+" Konnte den linken Knopf NICHT drücken und gibt auf.\n");
        }


    }

    //set the value of pressed buttons inside the head of the figure
    private void setButtonCount(int pressedButtonCount) {
        //headText.setText(pressedButtonCound+"");
    }

    //will be called when the deadlock button was pressed
    public void deadLock(){
        left.setFill(color);
        console.appendText(name + " hat den linken Knopf gedürckt\n");
    }

    //will be called when the reset button was pressed
    public void reset(){
        right.setFill(Color.BLACK);
        left.setFill(Color.BLACK);
        pressedButtonCount=0;
    }

    private void sleep(int milli){
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
