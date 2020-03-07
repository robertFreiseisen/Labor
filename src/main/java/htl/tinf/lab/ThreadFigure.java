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
    }

    public void deadLock(){
        right.setFill(color);
        console.appendText(name + " hat den rechten Knopf gedürckt\n");
    }

    public void reset(){
        right.setFill(Color.BLACK);
        left.setFill(Color.BLACK);
    }



}
