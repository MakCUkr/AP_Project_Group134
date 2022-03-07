package com.example.test1;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class MovingObject
{
    protected int xPos, yPos;
    protected Image icon;
    protected ImageView imageView;
    protected final int iconHeight;
    protected final int iconWidth;
    protected TranslateTransition translate;



    public MovingObject(int xPos, int yPos, Image icon, int iconHeight,int iconWidth) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.icon = icon;
        this.iconHeight = iconHeight;
        this.iconWidth = iconWidth;

        imageView = new ImageView(icon);
        imageView.setX(xPos);
        imageView.setY(yPos);
        imageView.setFitHeight(iconHeight);
        imageView.setFitWidth(iconWidth);
        imageView.setPreserveRatio(true);
        translate = new TranslateTransition();
    }

    public ImageView getImageView() {
        return imageView;
    }


}