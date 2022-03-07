package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Islands extends ListMovingObjects {

    int UPPER_FLOOR_ADD_PIXELS = 200;
    int LOWER_FLOOR_ADD_PIXELS = 450;
    final int SCREEN_HEIGHT = 720;
    final int SCREEN_WIDTH = 960;
    private int islandWithWhichCollision = -1;


    public Islands(Image icon, int frameChangeMillisecs, Enemies enemies, Image orcImage)
    {
        super(frameChangeMillisecs);
        BATCH_WIDTH = 300;
        BATCH_HEIGHT = 50;
        CLOUDS_PASSIVE_VELOCITY = 0.1;
        objectsList = new ArrayList<>();
        batchesOfClouds = (int) TOTAL_FRAME_WIDTH/BATCH_WIDTH;

        Random rand = new Random();
        int xRandomization;
        int yRandomization;

        //The first default island that will be ther
        objectsList.add(new Island((int) (SCREEN_WIDTH*0.12 - 100), (int) (SCREEN_HEIGHT/2 + 200), icon,150, 200, true));

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
                    if(rand.nextInt(10) < 4)
                        enemies.getObjectsList().add(new Enemy(xRandomization + 80, (LOWER_FLOOR_ADD_PIXELS+yRandomization-30), 100, true, orcImage, 40));
                }
                else {
                    island = new Island(xRandomization,(UPPER_FLOOR_ADD_PIXELS+ yRandomization), icon, 150, 200, false);
                    if(rand.nextInt(10) < 4)
                        enemies.getObjectsList().add(new Enemy(xRandomization+80, (UPPER_FLOOR_ADD_PIXELS+yRandomization-30), 100, true, orcImage, 40));
                }
                objectsList.add(island);
            }
        }
    }

//    public void playerMoved()
//    {
//        coolDownCounterAfterClick = (int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY);
//        passiveMovementToBeDone = true;
//        for(int i = 0 ; i < objectsList.size(); i++)
//        {
//            Island islandToMove = (Island) objectsList.get(i);
//            TranslateTransition translateTransition1 = new TranslateTransition();
//            translateTransition1.setByX(-(HORIZONTAL_JUMP_LENGTH));
//            translateTransition1.setDuration(Duration.millis((int) (HORIZONTAL_JUMP_LENGTH / HORIZONTAL_JUMP_VELOCITY)));
//            translateTransition1.setCycleCount(1);
//            translateTransition1.setNode(islandToMove.getImageView());
//            translateTransition1.play();
//        }
//    }



    public boolean anyIslandCollidePlayer(Player player, double playerCenterY, double playerCenterX)
    {
        boolean flag = false;

        for(int i = 0; i < objectsList.size(); i++)
        {
            Island island = (Island) objectsList.get(i);
            Bounds boundsIsland = island.getImageView().localToScene(island.getImageView().getBoundsInLocal());
            if((boundsIsland.getMaxY() > playerCenterY && boundsIsland.getMinY() < playerCenterY) && (playerCenterX < boundsIsland.getMaxX() && playerCenterX > boundsIsland.getMinX()))
            {
                if(islandWithWhichCollision == -1)
                {
//                    System.out.println("Collision occurred of player: (" + playerCenterX + "," + playerCenterY + "), island no." + i+": (" + boundsIsland.getMinX() + "," + boundsIsland.getMinY() + ") => ("+boundsIsland.getMaxX()+","+boundsIsland.getMaxY()+")");
                    islandWithWhichCollision = i;
                    player.bounceOffIsland();
                }
                return true;
            }
            else if(islandWithWhichCollision == i)
            {
//                System.out.println("Collision finished");
                islandWithWhichCollision = -1;
            }
        }
        return flag;
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

//    public ArrayList<Position> getEnemyPositions() {
//        ArrayList<Position> positions = new ArrayList<Position>();
//        Random rand = new Random();
//        for(int i = 0 ;i < objectsList.size(); i++)
//        {
//            if(rand.nextInt(10) < 4 )
//            {
//                positions.add(new Position(this.))
//            }
//        }
//        return positions;
//    }


    public class Island extends MovingObject
    {
        private boolean firstFloor;
        private List<Enemy> enemiesOnIsland;

        // has int xPos, int yPos, Image icon from parent - MovingObject
        public Island(int xPos, int yPos, Image icon, int iconHeight,int iconWidth, boolean firstFloor)
        {
            super(xPos, yPos, icon,  iconHeight, iconWidth);
            this.firstFloor = firstFloor;
            enemiesOnIsland = new ArrayList<Enemy>();
            int randNumber = new Random().nextInt();
            if(randNumber > 0 && randNumber < 4)
            {
                for(int i = 0 ; i < randNumber; i++)
                {
//                    enemiesOnIsland =
                }
            }
            else
            {

            }
        }
    }

}


