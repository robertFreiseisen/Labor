package htl.tinf.lab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainController implements Initializable {

    @FXML
    Button button_algoRandom;
    @FXML
    Button stop;
    @FXML
    Button button_algoSoluotion;
    @FXML
    Button button_algoDeadLock;

    @FXML
    ImageView bluePhilosopher;
    @FXML
    ImageView greenPhilosopher;
    @FXML
    ImageView yellowPhilosopher;
    @FXML
    ImageView purplePhilosopher;
    @FXML
    ImageView redPhilosopher;

    @FXML
    Circle buttonTop;
    @FXML
    Circle buttonRight;
    @FXML
    Circle buttonLeft;
    @FXML
    Circle buttonDownLeft;
    @FXML
    Circle buttonDownRight;
    @FXML
    TextArea console;

    ThreadFigure red;
    ThreadFigure yellow;
    ThreadFigure purple;
    ThreadFigure green;
    ThreadFigure blue;
    ThreadFigure[] threads;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bluePhilosopher.setImage(startImage(new File("images/blue.jpeg")));
        greenPhilosopher.setImage(startImage(new File("images/green.jpeg")));
        yellowPhilosopher.setImage(startImage(new File("images/yellow.jpeg")));
        purplePhilosopher.setImage(startImage(new File("images/purple.jpeg")));
        redPhilosopher.setImage(startImage(new File("images/red.jpeg")));

        console.setEditable(false);
        console.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        console.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        //shuffle thread array
        threads = createThreads();
        shuffleArray(threads);


        stop.setOnAction(a -> {
         reset();
        });

        button_algoSoluotion.setOnAction(a -> {
                reset();
        });

        button_algoRandom.setOnAction(a -> {
            reset();
            shuffleArray(threads);
            //make random shuffled thread array

            for (ThreadFigure thread :threads) {
                thread.run();
            }



        });

        button_algoDeadLock.setOnAction(a -> {
            reset();
            for (ThreadFigure thread :threads) {
                thread.deadLock();
            }
            console.appendText("=================\n" +
                    "Deadlock : Red,Yellow,Purple,Green,Blue warten.");
        });

    }

    private Image startImage(File file){
        Image image = new Image(file.toURI().toString());
        return image;
    }

    private ThreadFigure[] createThreads(){
        /*
        ThreadFigure red;
        ThreadFigure yellow;
        ThreadFigure purple;
        ThreadFigure green;
        ThreadFigure blue;
        */

        red = new ThreadFigure(buttonLeft,buttonTop,console,Color.RED,"Red");
        yellow =new ThreadFigure(buttonTop,buttonRight,console, Color.YELLOW,"Yellow");
        purple = new ThreadFigure(buttonRight,buttonDownRight,console,Color.PURPLE,"Purple");
        green = new ThreadFigure(buttonDownRight,buttonDownLeft,console,Color.LIGHTGREEN,"Green");
        blue = new ThreadFigure(buttonDownLeft,buttonLeft,console,Color.BLUE,"Blue");

       return new ThreadFigure[]{red, yellow, purple, green, blue};
    }

    private void reset(){
        for (ThreadFigure thread :threads) {
            thread.reset();
        }
        console.setText("");
    }

    static void shuffleArray(ThreadFigure[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            ThreadFigure a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}

