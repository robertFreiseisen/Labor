package htl.tinf.lab;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Collection;

public class ThreadFigure  extends Thread {

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

    public double elapsedTime;

    ThreadFigure(Circle right, Circle left, TextArea console, Color color,String name) {
    this.right=right;
    this.left=left;
    this.console = console;
    this.color = color;
    this.name = name;
    }


    @Override
    public void run() {
        sleep(1);
        if(left.getFill()==Color.BLACK) {
            left.setFill(color);
            elapsedTime = (System.currentTimeMillis() - MainController.previousTime);
            if(elapsedTime>10)
            console.appendText("* "+name + " hat den linken Knopf gedürckt.                             *" + elapsedTime+"ms\n");
            pressedButtonCount++;
            sleep(1);

            if(right.getFill()==Color.BLACK) {
                left.setFill(color);
                elapsedTime = (System.currentTimeMillis() - MainController.previousTime);
                console.appendText("* "+name + " hat den rechten Knopf gedürckt.                            *"+ elapsedTime+"ms\n");
                pressedButtonCount++;
                sleep(1);
            }else {
                elapsedTime = (System.currentTimeMillis() - MainController.previousTime);
                console.appendText("* "+name + " konnte den rechten Knopf nicht mehr drücken.               *"+ elapsedTime+"ms\n");
                sleep(1);
            }
        }else {
            elapsedTime = (System.currentTimeMillis() - MainController.previousTime);
            console.appendText("* "+name + " konnte den linken Knopf nicht drücken. "+ elapsedTime+"ms\n");
            sleep(1);
        }
        setButtonCount(pressedButtonCount);
    }

    //set the value of pressed buttons inside the head of the figure
    private void setButtonCount(int pressedButtonCount) {
        //headText.setText(pressedButtonCound+"");
    }

    //will be called when the deadlock button was pressed
    public void deadLock(){
        left.setFill(color);
        sleep(1);
        elapsedTime = (System.currentTimeMillis() - MainController.previousTime);
        console.appendText(name + " hat den linken Knopf gedürckt                               *"+ elapsedTime +"ms\n");
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
