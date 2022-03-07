package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

class ListMovingObjects {
    protected List<MovingObject> objectsList;

    protected int TOTAL_FRAME_WIDTH = 100000; // needs to be altered
    protected int BATCH_WIDTH ;
    protected int BATCH_HEIGHT ;
    protected double CLOUDS_PASSIVE_VELOCITY;
    protected ImageView skyImageView;
    protected int batchesOfClouds;
    protected int  coolDownCounterAfterClick = 0;
    protected int frameChangeMillisecs;
    protected final int HORIZONTAL_JUMP_LENGTH = 100;
    protected boolean passiveMovementToBeDone = false;
    protected final double HORIZONTAL_JUMP_VELOCITY = 0.5;



    ListMovingObjects(int frameChangeMillisecs)
    {
        this.objectsList = new ArrayList<MovingObject>();
        this.frameChangeMillisecs = frameChangeMillisecs;
    }

    public void addImageViewsToRoot(ObservableList<Node> children)
    {
        for(int i = 0 ; i < objectsList.size(); i++)
        {
            children.add(objectsList.get(i).getImageView());
        }
    }


    public void playerMoved() {
        coolDownCounterAfterClick = (int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY);
        passiveMovementToBeDone = true;
        for (int i = 0; i < objectsList.size(); i++) {
            Islands.Island islandToMove = (Islands.Island) objectsList.get(i);
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setByX(-(HORIZONTAL_JUMP_LENGTH));
            translateTransition1.setDuration(Duration.millis((int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY)));
            translateTransition1.setCycleCount(1);
            translateTransition1.setNode(islandToMove.getImageView());
            translateTransition1.play();
        }
    }
}
