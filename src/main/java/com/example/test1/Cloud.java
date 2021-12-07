//package com.example.test1;
//
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//
//import java.util.Random;
//
//class Cloud extends MovingObject
//{
//    final int VERTICAL_VIBRATION_SPEED = 1;
//    final int HORIZONTAL_SPEED_ON_CLICK = 70;
//    final int HORIZONTAL_SPEED_PASSIVE = -1;
//    final int SCREEN_WIDTH = 1280;
//    GraphicsContext gc;
//
//    public Cloud(int xPos, int yPos, Image icon,GraphicsContext gc)
//    {
//        super(xPos, yPos, icon, icon, );
//        this.gc = gc;
//    }
//
//    public void moveHorizontally() {
//        xPos -= HORIZONTAL_SPEED_ON_CLICK;
//        this.update();
//    }
//
//    public void update() {
//        Random rand = new Random();
//        this.yPos += (VERTICAL_VIBRATION_SPEED-rand.nextInt(VERTICAL_VIBRATION_SPEED*2));
//        this.xPos += HORIZONTAL_SPEED_PASSIVE;
//
//    }
//
//    public void showCloud()
//    {
//        if(xPos > 0 && xPos < SCREEN_WIDTH)
//        {
//            gc.drawImage(icon, xPos, yPos);
//        }
//    }
//
//    public int getXPos()
//    {
//        return this.xPos;
//    }
//}