package com.monfort;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player {
    private int x, y;
    private Image image;
    private Item item;

    public Player(String path, int x, int y){
        this.x = x;
        this.y = y;
        createItem(path);

        try{
            image = new Image(new FileInputStream(path));
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Could not load player image");
        }
    }

    public void createItem(String path){
        item = new Item(path, 100, 100, false, "balls");
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image, x, y);
        item.inspect(gc);
    }

    public void testCollision(GraphicsContext gc){
        Rectangle2D myhitBox = new Rectangle2D(x, y, 50, 50);
        if(item.collision(myhitBox)){
            gc.setFill(Paint.valueOf("RED"));
            gc.fillText("hello", 300, 300);
        }
    }

    public void moveUp(){
        y -= 1;
    }

    public void moveDown(){
        y += 1;
    }

    public void moveRight(){
        x += 1;
    }

    public void moveLeft(){
        x -= 1;
    }


}
