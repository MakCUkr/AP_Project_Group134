package com.example.test1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SkyBackground {
    private ImageView imageView;
    private final int FRAME_HEIGHT = 960;
    private final int FRAME_WIDTH = 1280;


    public SkyBackground(Image image) {
        imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(FRAME_HEIGHT);
        imageView.setFitWidth(FRAME_WIDTH);
//        imageView.setPreserveRatio(true);
    }

    public ImageView getImageView() {
        return imageView;
    }
}

