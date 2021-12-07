package com.example.test1;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HelloApplication extends Application {
    Player player;
    Image playerImage, cloudImage, islandImage;
    private GraphicsContext gc;
    final int SCREEN_HEIGHT = 720;
    final int SCREEN_WIDTH = 960;
    CloudsInBackground cloudsInBackground;
//    private int coolDownCounterAfterClick = 0;
    private final int FRAME_CHANGE_MILLISECS = 5;
    Islands islands;
    Group root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
//        gc = canvas.getGraphicsContext2D();

        Parent jad =  fxmlLoader.load();
        root = new Group(jad);
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(FRAME_CHANGE_MILLISECS), actionEvent -> showGC()));
        timeline.play();


        //Adding the sky Bg
        Image skyImage = new Image(new FileInputStream("/Users/maksimchowdhary/IdeaProjects/Test1/Assets/skyBgGradient.png"));
        SkyBackground skyBackground = new SkyBackground(skyImage);
        root.getChildren().add(skyBackground.getImageView());

        prepare();

        stage.setTitle("Hello!");
        Scene scene= new Scene(root, 1280, 960);
        scene.setOnMouseClicked(e -> {
            islands.playerMoved();
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

//    public void moveBackClouds()
//    {
//        for(int i = 0 ; i < clouds.size(); i++)
//        {
//            clouds.get(i).moveHorizontally();
//        }
//    }
//
//    public void updateListOfClouds()
//    {
//        if(clouds.isEmpty() || clouds.size() < 5) {
//            Random rand = new Random();
//            int xPos = rand.nextInt(SCREEN_WIDTH - 300, SCREEN_WIDTH - 100);
//            int yPos = rand.nextInt(100, SCREEN_HEIGHT - 100);
//            Cloud cloudInstance = new Cloud(xPos, yPos, cloudImage, gc);
//            clouds.add(cloudInstance);
//        }
//        else
//        {
//            for(int i = 0 ; i < clouds.size(); i++)
//            {
//                Cloud cloud = clouds.get(i);
//                cloud.update();
//                if(cloud.getXPos() < -160)
//                {
//                    Cloud cloudTBD = clouds.get(i);
//                    clouds.remove(i);
//                    cloudTBD = null;
//                }
//            }
//        }
//    }
//
//    public void showClouds()
//    {
//        for(int i = 0 ; i < clouds.size(); i++)
//        {
//            clouds.get(i).showCloud();
//        }
//    }

    public void prepare()
    {
//        clouds = new ArrayList<Cloud>();
        System.out.println("prepare() called\n");

        // adding the clouds to scene
        cloudImage = new Image("file:/Users/maksimchowdhary/IdeaProjects/Test1/Assets/cloudImage.png", 150, 100, false, false);
        cloudsInBackground = new CloudsInBackground(cloudImage);
        cloudsInBackground.addImageViewsToRoot(root.getChildren());

        // adding the islands to scene
        islandImage = new Image("file:/Users/maksimchowdhary/IdeaProjects/Test1/Assets/islandImage.png", 150, 100, false, false);
        islands = new Islands(islandImage, FRAME_CHANGE_MILLISECS);
        islands.addImageViewsToRoot(root.getChildren());

        // adding the player icon to scene
        playerImage = new Image("file:/Users/maksimchowdhary/IdeaProjects/Test1/Assets/playerIcon.png", 75, 75, false, false);
        player = new Player((int) (SCREEN_WIDTH*0.12), (int) (SCREEN_HEIGHT/2), 100, true, playerImage, 20, gc);
        root.getChildren().add(player.getImageView());

    }

    public void showGC()
    {
//        gc.drawImage(backgroundImage, 0, 0);
//        player.showPlayer();
//        updateListOfClouds();
//        showClouds();
        islands.passiveAfterMovement();
//        player.updatePlayerPosition();
//        System.out.println("showGC() called");
    }







}