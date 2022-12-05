package com.monfort;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Prop {
    private int x, y;
    private boolean collideable;
    Image image;
    Rectangle2D hitBox;

    public Prop(String path, int x, int y, boolean collideable) {
        this.x = x;
        this.y = y;
        this.collideable = collideable;
        hitBox = new Rectangle2D(x, y, 50,50);

        try{
            image = new Image(new FileInputStream(path));
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Could not load player image");
        }
    }

    public boolean collision(Rectangle2D other){
        return hitBox.intersects(other);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCollideable() {
        return collideable;
    }

    public void setCollideable(boolean collideable) {
        this.collideable = collideable;
    }
}
