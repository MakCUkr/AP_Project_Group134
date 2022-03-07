package com.example.test1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    Player player;
    Image playerImage, cloudImage, islandImage, orcImage;
    private GraphicsContext gc;
    final int SCREEN_HEIGHT = 720;
    final int SCREEN_WIDTH = 960;
    CloudsInBackground cloudsInBackground;
    private final int FRAME_CHANGE_MILLISECS = 5;
    Islands islands;
    Group root;
    Enemies enemies;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

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
            enemies.playerMoved();
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public void prepare()
    {
        System.out.println("prepare() called\n");

        // adding the clouds to scene
        cloudImage = new Image("file:/Users/maksimchowdhary/IdeaProjects/Test1/Assets/cloudImage.png", 150, 100, false, false);
        cloudsInBackground = new CloudsInBackground(cloudImage);
        cloudsInBackground.addImageViewsToRoot(root.getChildren());

        // creating enemies. To be passed to islands
        enemies = new Enemies(FRAME_CHANGE_MILLISECS);
        orcImage = new Image("file:/Users/maksimchowdhary/IdeaProjects/Test1/Assets/orcImage.png", 75, 75, false, false);

        // adding the islands to scene
        islandImage = new Image("file:/Users/maksimchowdhary/IdeaProjects/Test1/Assets/islandImage.png", 150, 100, false, false);
        islands = new Islands(islandImage, FRAME_CHANGE_MILLISECS, enemies, orcImage);
        islands.addImageViewsToRoot(root.getChildren());

        enemies.addImageViewsToRoot(root.getChildren());

        // adding the player icon to scene
        playerImage = new Image("file:/Users/maksimchowdhary/IdeaProjects/Test1/Assets/playerIcon.png", 75, 75, false, false);
        player = new Player((int) (SCREEN_WIDTH*0.12), (int) (SCREEN_HEIGHT/2), 100, true, playerImage, 50);
        root.getChildren().add(player.getImageView());

        // adding enemy icon
//        enemy = new Enemy((int) (SCREEN_WIDTH*0.12), (int) (SCREEN_HEIGHT/2), 100, true, orcImage, 20, 50, 50);
//        root.getChildren().add(enemy.getImageView());

//         enemies.addAndDisplayEnemies(islands.getEnemyPositions(), root.getChildren());
    }

    public void showGC()
    {
        islands.passiveAfterMovement();
        Bounds boundsInScene = player.getImageView().localToScene(player.getImageView().getBoundsInLocal());
        islands.anyIslandCollidePlayer(player, boundsInScene.getCenterY(), boundsInScene.getCenterX());
        enemies.passiveAfterMovement();
        enemies.anyEnemyCollidePlayer(player, boundsInScene.getCenterY(), boundsInScene.getCenterX());
    }

}