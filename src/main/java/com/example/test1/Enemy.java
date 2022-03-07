package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Enemy extends Character {
    TranslateTransition translateTransition;


    Enemy(int xPos, int yPos, int health, boolean alive, Image icon, int playerWidth)
    {
        super(xPos, yPos, health, playerWidth, alive, icon);
//        this.imageViewWidth = playerWidth;
//        this.imageViewHeight = playerWidth;

        this.translateTransition = new TranslateTransition();
        translateTransition = new TranslateTransition();
        translateTransition.setByY(-150);
        translateTransition.setDuration(Duration.millis(this.JUMP_VERTICAL_TIME_ms));
        translateTransition.setCycleCount(10000);
        translateTransition.setNode(this.getImageView());
    }


 
}
