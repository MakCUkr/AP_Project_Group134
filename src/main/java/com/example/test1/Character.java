package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Character {
    private int xPos;
    private int yPos;
    private int health;
    private int characterWidth;
    private boolean alive;
    private Image icon;
    private ImageView imageView;

    protected final int HORIZONTAL_JUMP_LENGTH = 100;
    protected final int JUMP_VERTICAL_TIME_ms = 300;


    public Character(int xPos, int yPos, int health, int characterWidth, boolean alive, Image icon) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.health = health;
        this.characterWidth = characterWidth;
        this.alive = alive;
        this.icon = icon;

        imageView = new ImageView(icon);
        imageView.setX(xPos);
        imageView.setY(yPos);
        imageView.setFitHeight(characterWidth);
        imageView.setFitWidth(characterWidth);
        imageView.setPreserveRatio(true);
    }

    public ImageView getImageView() {
        return imageView;
    }


    public class MyThread extends Thread {
        TranslateTransition translateTransition;
        ImageView imageView;

        MyThread(TranslateTransition translateTransition, ImageView imageView) {
            this.translateTransition = translateTransition;
            this.imageView = imageView;
        }

        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(JUMP_VERTICAL_TIME_ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            translateTransition = createGravityTranslation();
            translateTransition.play();
        }
    }

    public TranslateTransition createGravityTranslation()
    {
        TranslateTransition translateTransition;
        translateTransition = new TranslateTransition();
        translateTransition.setByY(1200);
        translateTransition.setDuration(Duration.millis(4000));
        translateTransition.setCycleCount(1);
        translateTransition.setNode(this.imageView);
        return translateTransition;
    }


}