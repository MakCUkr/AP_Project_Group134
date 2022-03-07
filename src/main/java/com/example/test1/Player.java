package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Player extends Character {
    TranslateTransition translateTransition;

    public Player(int xPos, int yPos, int health, boolean alive, Image icon, int playerWidth) {
        super( xPos,  yPos,  health,  playerWidth,  alive,  icon);
        System.out.println("Player created");

        translateTransition = this.createGravityTranslation();
        translateTransition.play();
    }

//    public void updatePlayerPosition()
//    {
//        if (alive)
//        {
//            imageView.setX(xPos);
//            imageView.setY(yPos);
//        }
//    }


    public void bounceOffIsland() {
        translateTransition.stop();
        TranslateTransition translateTransition1 = new TranslateTransition();
        translateTransition1.setByY(-150);
        translateTransition1.setDuration(Duration.millis(this.JUMP_VERTICAL_TIME_ms));
        translateTransition1.setCycleCount(1);
        translateTransition1.setNode(this.getImageView());
        translateTransition1.play();

        MyThread myThread = new MyThread(this.translateTransition, this.getImageView());
        myThread.start();
    }

}
