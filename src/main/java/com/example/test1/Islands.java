package com.example.test1;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Islands extends ListMovingObjects {
    int UPPER_FLOOR_ADD_PIXELS = 200;
    int LOWER_FLOOR_ADD_PIXELS = 450;
    private final int HORIZONTAL_JUMP_LENGTH = 100;
    private final double HORIZONTAL_JUMP_VELOCITY = 0.5;
    private int  coolDownCounterAfterClick = 0;
    private int frameChangeMillisecs;
    private boolean passiveMovementToBeDone = false;

    public Islands(Image icon, int frameChangeMillisecs)
    {
        this.frameChangeMillisecs = frameChangeMillisecs;
        objectsList = new ArrayList<>();
        BATCH_WIDTH = 300;
        BATCH_HEIGHT = 50;
        CLOUDS_PASSIVE_VELOCITY = 0.1;
        objectsList = new ArrayList<>();
        batchesOfClouds = (int) TOTAL_FRAME_WIDTH/BATCH_WIDTH;

        Random rand = new Random();
        int xRandomization;
        int yRandomization;

        for(int i = 1 ; i < batchesOfClouds; i++)
        {
            for(int j = 0 ; j < 2; j++)
            {
                xRandomization = rand.nextInt(BATCH_WIDTH);
                yRandomization = rand.nextInt(BATCH_HEIGHT);
                xRandomization = BATCH_WIDTH * i + xRandomization;

                Island island;
                // adding a first floor island
                if(j == 0){
                    island = new Island(xRandomization, (LOWER_FLOOR_ADD_PIXELS+yRandomization), icon, 150, 200, true);
                }
                else {
                    island = new Island(xRandomization,(UPPER_FLOOR_ADD_PIXELS+ yRandomization), icon, 150, 200, false);
                }
                objectsList.add(island);
            }
        }
    }

    public void playerMoved()
    {
        coolDownCounterAfterClick = (int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY);
        passiveMovementToBeDone = true;
//        System.out.println("Player Moved");
        for(int i = 0 ; i < objectsList.size(); i++)
        {
            Island islandToMove = (Island) objectsList.get(i);
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setByX(-(HORIZONTAL_JUMP_LENGTH));
            translateTransition1.setDuration(Duration.millis((int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY)));
            translateTransition1.setCycleCount(1);
            translateTransition1.setNode(islandToMove.getImageView());
            translateTransition1.play();
        }
    }

    public void passiveAfterMovement()
    {
        if(passiveMovementToBeDone) {
            if (coolDownCounterAfterClick > 0) {
                coolDownCounterAfterClick -= this.frameChangeMillisecs;
            } else {
                for (int i = 0; i < objectsList.size(); i++) {
                    Island islandToMove = (Island) objectsList.get(i);
                    TranslateTransition translateTransition1 = new TranslateTransition();
                    translateTransition1.setByX(-(HORIZONTAL_JUMP_LENGTH / 3));
                    translateTransition1.setDuration(Duration.millis((int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY)));
                    translateTransition1.setCycleCount(1);
                    translateTransition1.setNode(islandToMove.getImageView());
                    translateTransition1.play();
                }
                passiveMovementToBeDone = false;
            }
        }
    }


    public class Island extends MovingObject
    {
        private boolean firstFloor;


        // has int xPos, int yPos, Image icon from parent - MovingObject
        public Island(int xPos, int yPos, Image icon, int iconHeight,int iconWidth, boolean firstFloor)
        {
            super(xPos, yPos, icon,  iconHeight, iconWidth);

            this.firstFloor = firstFloor;
        }
    }

}


