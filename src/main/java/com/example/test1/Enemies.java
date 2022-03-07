package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemies {
    protected ArrayList<Enemy> objectsList;
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
    private int enemyWithWhichCollision = -1;

    public Enemies(int frameChangeMillisecs)
    {
        objectsList = new ArrayList<>();
        this.frameChangeMillisecs = frameChangeMillisecs;
//        this.icon = icon;
    }

//    public void addAndDisplayEnemies(ArrayList<Position> positions, ObservableList<Node> rootChildren)
//    {
//        for(int i = 0 ; i < positions.size(); i++)
//        {
//            Position position = positions.get(i);
//            Enemy enemy = new Enemy(position.x , position.y,  100, true, icon, 75);
//            objectsList.add(enemy);
//        }
//        addImageViewsToRoot(rootChildren);
//    }

    public void addImageViewsToRoot(ObservableList<Node> rootChildren)
    {
        for(int i = 0 ; i < objectsList.size(); i++)
        {
            rootChildren.add(objectsList.get(i).getImageView());
        }
    }

    public List<Enemy> getObjectsList() {
        return objectsList;
    }

    public void passiveAfterMovement()
    {
        if(passiveMovementToBeDone) {
            if (coolDownCounterAfterClick > 0) {
                coolDownCounterAfterClick -= this.frameChangeMillisecs;
            } else {
                for (int i = 0; i < objectsList.size(); i++) {
                    Enemy enemyToMove = (Enemy) objectsList.get(i);
                    TranslateTransition translateTransition1 = new TranslateTransition();
                    translateTransition1.setByX(-(HORIZONTAL_JUMP_LENGTH / 3));
                    translateTransition1.setDuration(Duration.millis((int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY)));
                    translateTransition1.setCycleCount(1);
                    translateTransition1.setNode(enemyToMove.getImageView());
                    translateTransition1.play();
                }
                passiveMovementToBeDone = false;
            }
        }
    }

    public void playerMoved() {
        coolDownCounterAfterClick = (int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY);
        passiveMovementToBeDone = true;
        for (int i = 0; i < objectsList.size(); i++) {
            Enemy enemyToMove = (Enemy) objectsList.get(i);
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setByX(-(HORIZONTAL_JUMP_LENGTH));
            translateTransition1.setDuration(Duration.millis((int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY)));
            translateTransition1.setCycleCount(1);
            translateTransition1.setNode(enemyToMove.getImageView());
            translateTransition1.play();
        }
    }

    public boolean anyEnemyCollidePlayer(Player player, double playerCenterY, double playerCenterX)
    {
        boolean flag = false;

        for(int i = 0; i < objectsList.size(); i++)
        {
            Enemy enemy = (Enemy) objectsList.get(i);
            Bounds boundsIsland = enemy.getImageView().localToScene(enemy.getImageView().getBoundsInLocal());
            if((boundsIsland.getMaxY() > playerCenterY && boundsIsland.getMinY() < playerCenterY) && (playerCenterX < boundsIsland.getMaxX() && playerCenterX > boundsIsland.getMinX()))
            {
                if(enemyWithWhichCollision == -1)
                {
                    System.out.println("Collision occurred of player: (" + playerCenterX + "," + playerCenterY + "), enemy no." + i+": (" + boundsIsland.getMinX() + "," + boundsIsland.getMinY() + ") => ("+boundsIsland.getMaxX()+","+boundsIsland.getMaxY()+")");
                    enemyWithWhichCollision = i;
                    player.bounceOffIsland();
                }
                return true;
            }
            else if(enemyWithWhichCollision == i)
            {
                System.out.println("Collision finished");
                enemyWithWhichCollision = -1;
            }
        }
        return flag;
    }
}
