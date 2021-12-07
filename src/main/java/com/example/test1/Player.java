package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {
    private int xPos, yPos, health, playerWidth;
    private boolean alive;
    private Image icon;
    private ImageView imageView;
    GraphicsContext gc;
    TranslateTransition translateTransition;
    private final int PLAYER_ICON_HEIGHT = 50;
    private final int PLAYER_JUMPS_CYCLE_COUNT = 10000;
    private final int HORIZONTAL_JUMP_LENGTH = 100;
    private final double HORIZONTAL_JUMP_VELOCITY = 0.5;


    public Player(int xPos, int yPos, int health, boolean alive, Image icon, int playerWidth, GraphicsContext gc) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.health = health;
        this.alive = alive;
        this.playerWidth = playerWidth;
        this.icon = icon;
        this.gc = gc;

        imageView = new ImageView(icon);
        imageView.setX(xPos);
        imageView.setY(yPos);
        imageView.setFitHeight(PLAYER_ICON_HEIGHT);
        imageView.setFitWidth(PLAYER_ICON_HEIGHT);
        imageView.setPreserveRatio(true);

        translateTransition = new TranslateTransition();
        translateTransition.setByY(100);
        translateTransition.setDuration(Duration.millis(600));
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(PLAYER_JUMPS_CYCLE_COUNT);
        translateTransition.setNode(this.imageView);
        translateTransition.play();
    }

//    public void playerMove()
//    {
//        System.out.println("Player Moved");
//        TranslateTransition translateTransition1 = new TranslateTransition();
//        translateTransition1.setByX(HORIZONTAL_JUMP_LENGTH);
//        translateTransition1.setDuration(Duration.millis((int) HORIZONTAL_JUMP_LENGTH/HORIZONTAL_JUMP_VELOCITY));
//        translateTransition1.setCycleCount(1);
//        translateTransition1.setNode(this.imageView);
//        translateTransition1.play();
//    }


    public void updatePlayerPosition()
    {
        if (alive)
        {
//            gc.drawImage(icon, xPos, yPos);
            imageView.setX(xPos);
            imageView.setY(yPos);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}
