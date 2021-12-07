package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CloudsInBackground extends ListMovingObjects {
//    List<Cloud> clouds;
//    ImageView skyImageView;
//    private int batchesOfClouds;

    public CloudsInBackground(Image cloudImage)
    {
        BATCH_WIDTH = 400;
        BATCH_HEIGHT = 900;
        CLOUDS_PASSIVE_VELOCITY = 0.1;
        objectsList = new ArrayList<>();
        batchesOfClouds = (int) TOTAL_FRAME_WIDTH/BATCH_WIDTH;

//        Random rand = new Random(BATCH_WIDTH/2);
        Random rand = new Random();
        int xRandomization;
        int yRandomization;

        for(int i = 0 ; i < batchesOfClouds; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                xRandomization = rand.nextInt(BATCH_WIDTH);
                yRandomization = rand.nextInt(BATCH_HEIGHT);
                xRandomization = BATCH_WIDTH*i+xRandomization;

                Cloud cloud = new Cloud(xRandomization, yRandomization, cloudImage, 200, 400);
                cloud.constantLeftMovement(xRandomization);
                objectsList.add(cloud);
            }
        }
    }

//    public void addImageViewsToRoot(ObservableList<Node> children)
//    {
//        for(int i = 0 ; i < objectsList.size(); i++)
//        {
//            children.add(objectsList.get(i).getImageView());
//        }
//    }


    class Cloud extends MovingObject {
        public Cloud(int x, int y, Image image, int iconHeight,int iconWidth)
        {
            super(x,y, image, iconHeight,iconWidth);
//            this.xPos = x;
//            this.yPos = y;
//            imageView = new ImageView(image);
//            imageView.setX(x);
//            imageView.setY(y);
//            imageView.setFitHeight(200);
//            imageView.setFitWidth(400);
//            imageView.setPreserveRatio(true);
//            translate = new TranslateTransition();
        }

        public void constantLeftMovement(int currentPos)
        {
            int distanceToMove =  currentPos+2000;
            translate.setByX(-(distanceToMove));
            this.translate.setDuration(Duration.millis((int) distanceToMove/CLOUDS_PASSIVE_VELOCITY));
            this.translate.setCycleCount(1);
            this.translate.setNode(this.getImageView());
            this. translate.play();
        }
    }
}
