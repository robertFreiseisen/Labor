package htl.tinf.lab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import javafx.scene.shape.Circle;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bluePhilosopher.setImage(startImage(new File("images/blue.jpeg")));
        greenPhilosopher.setImage(startImage(new File("images/green.jpeg")));
        yellowPhilosopher.setImage(startImage(new File("images/yellow.jpeg")));
        purplePhilosopher.setImage(startImage(new File("images/purple.jpeg")));
        redPhilosopher.setImage(startImage(new File("images/red.jpeg")));

        stop.setOnAction(a -> {

        });

        button_algoSoluotion.setOnAction(a -> {

        });

        button_algoRandom.setOnAction(a -> {

        });

        button_algoDeadLock.setOnAction(a -> {

        });

    }

    private Image startImage(File file){
        Image image = new Image(file.toURI().toString());
        return image;
    }



}
