package htl.tinf.lab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.layout.AnchorPane;
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
    AnchorPane philoPane;

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

    //normal Threads no sync
    ThreadFigure red;
    ThreadFigure yellow;
    ThreadFigure purple;
    ThreadFigure green;
    ThreadFigure blue;
    ThreadFigure[] threads;

    //Synced Threads
    ThreadFigureSync[] threadsSync;
    ThreadFigureSync redSync;
    ThreadFigureSync yellowSync;
    ThreadFigureSync purpleSync;
    ThreadFigureSync greenSync;
    ThreadFigureSync blueSync;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consoleHeader();
        bluePhilosopher.setImage(startImage(new File("images/blue.jpeg")));
        greenPhilosopher.setImage(startImage(new File("images/green.jpeg")));
        yellowPhilosopher.setImage(startImage(new File("images/yellow.jpeg")));
        purplePhilosopher.setImage(startImage(new File("images/purple.jpeg")));
        redPhilosopher.setImage(startImage(new File("images/red.jpeg")));

        console.setEditable(false);
        console.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        console.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Consolas; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00; ");

        //shuffle thread array
        threads = createThreads();
        threadsSync = createSyncedThreads();
        shuffleThreads(threads);
        shuffleSyncedThreads(threadsSync);

        stop.setOnAction(a -> {
            reset();
            consoleHeader();
        });

        button_algoSoluotion.setOnAction(a -> {
            reset();
            //console.setText("Mhh, die Threads haben bisher noch keine Friedliche\nLösung gefunden dieses Problem zu lösen.\n");
            shuffleSyncedThreads(threadsSync);
            // TODO: 08/03/2020

            for (ThreadFigureSync thread : threadsSync) {
                thread.run();
            }


            console.appendText("============================================================================\n\n\n");
        });

        button_algoRandom.setOnAction(a -> {
          rndm();

        });

        button_algoDeadLock.setOnAction(a -> {
            reset();
            consoleHeader();
            shuffleThreads(threads);
            for (ThreadFigure thread : threads) {
                thread.left();
            }

            shuffleThreads(threads);
            for (ThreadFigure thread : threads) {
                thread.right();
            }

            waitAllThreads();
            console.appendText("============================================================================\n" +
                    "Deadlock : Red,Yellow,Purple,Green,Blue warten.");
        });

    }

    private void rndm(){
        reset();
        consoleHeader();
        shuffleThreads(threads);
        //make random shuffled thread array

        for (ThreadFigure thread : threads) {
            thread.run();
        }

        boolean everyThreadPressed=true;
        for (ThreadFigure thread : threads) {
            if(!(thread.getButtonCount()==1)) {
                everyThreadPressed=false;
            }
        }
        if(everyThreadPressed) {
            console.appendText("============================================================================\n" +
                    "Deadlock : Red,Yellow,Purple,Green,Blue warten.\n\n\n");
        }else {
        }


        console.appendText("============================================================================\n\n\n");
    }

    private ThreadFigureSync[] createSyncedThreads() {
        redSync = new ThreadFigureSync(buttonLeft, buttonTop, console, Color.RED, "Red   :");
        yellowSync = new ThreadFigureSync(buttonTop, buttonRight, console, Color.YELLOW, "Yellow:");
        purpleSync = new ThreadFigureSync(buttonRight, buttonDownRight, console, Color.PURPLE, "Purple:");
        greenSync = new ThreadFigureSync(buttonDownRight, buttonDownLeft, console, Color.LIGHTGREEN, "Green :");
        blueSync = new ThreadFigureSync(buttonDownLeft, buttonLeft, console, Color.BLUE, "Blue  :");

        return new ThreadFigureSync[]{redSync, yellowSync, purpleSync, greenSync, blueSync};
    }

    private Image startImage(File file) {
        Image image = new Image(file.toURI().toString());
        return image;
    }

    private ThreadFigure[] createThreads() {
        /*
        ThreadFigure red;
        ThreadFigure yellow;
        ThreadFigure purple;
        ThreadFigure green;
        ThreadFigure blue;
        */

        red = new ThreadFigure(buttonLeft, buttonTop, console, Color.RED, "Red   :");
        yellow = new ThreadFigure(buttonTop, buttonRight, console, Color.YELLOW, "Yellow:");
        purple = new ThreadFigure(buttonRight, buttonDownRight, console, Color.PURPLE, "Purple:");
        green = new ThreadFigure(buttonDownRight, buttonDownLeft, console, Color.LIGHTGREEN, "Green :");
        blue = new ThreadFigure(buttonDownLeft, buttonLeft, console, Color.BLUE, "Blue  :");

        return new ThreadFigure[]{red, yellow, purple, green, blue};
    }

    private void reset() {
        for (ThreadFigure thread : threads) {
            thread.reset();
        }
        console.setText("");
    }

    private void shuffleThreads(ThreadFigure[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            ThreadFigure a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private void shuffleSyncedThreads(ThreadFigureSync[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            ThreadFigureSync a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void consoleHeader() {
        console.appendText("Research Button Problem\n");
        console.appendText("***********************\n");
    }


    private void waitAllThreads() {
        for (ThreadFigure thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleep(int milli){
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

