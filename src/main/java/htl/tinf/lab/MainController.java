package htl.tinf.lab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

        createThreads();


        red.run();
        yellow.run();
        purple.run();
        green.run();
        blue.run();

        stop.setOnAction(a -> {
         reset();
        });

        button_algoSoluotion.setOnAction(a -> {
                reset();
        });

        button_algoRandom.setOnAction(a -> {
            reset();
        });

        button_algoDeadLock.setOnAction(a -> {
            reset();
            for (ThreadFigure thread :threads) {
                thread.deadLock();
            }
        });

    }

    private Image startImage(File file){
        Image image = new Image(file.toURI().toString());
        return image;
    }

    private void createThreads(){
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

        threads= new ThreadFigure[]{red, yellow, purple, green, blue};
    }

    private void reset(){
        for (ThreadFigure thread :threads) {
            thread.reset();
        }
        console.setText("");
    }


}
