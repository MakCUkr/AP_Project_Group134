package com.example.test1;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.List;

abstract class ListMovingObjects {
    protected List<MovingObject> objectsList;
    protected int TOTAL_FRAME_WIDTH = 100000; // needs to be altered
    protected int BATCH_WIDTH ;
    protected int BATCH_HEIGHT ;
    protected double CLOUDS_PASSIVE_VELOCITY;
    protected ImageView skyImageView;
    protected int batchesOfClouds;


    public void addImageViewsToRoot(ObservableList<Node> children)
    {
        for(int i = 0 ; i < objectsList.size(); i++)
        {
            children.add(objectsList.get(i).getImageView());
        }
    }

//    public ListMovingObjects(List<MovingObject> objectsList, int TOTAL_FRAME_WIDTH, int BATCH_WIDTH, int BATCH_HEIGHT, double CLOUDS_PASSIVE_VELOCITY, ImageView skyImageView, int batchesOfClouds)
//    {
//        this.objectsList = objectsList;
//        this.TOTAL_FRAME_WIDTH = TOTAL_FRAME_WIDTH;
//        this.BATCH_WIDTH = BATCH_WIDTH;
//        this.BATCH_HEIGHT = BATCH_HEIGHT;
//        this.CLOUDS_PASSIVE_VELOCITY = CLOUDS_PASSIVE_VELOCITY;
//        this.skyImageView = skyImageView;
//        this.batchesOfClouds = batchesOfClouds;
//    }


}
