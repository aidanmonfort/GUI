package com.monfort;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Item extends Prop{
    String description;

    public Item(String path, int x, int y, boolean collideable, String description) {
        super(path, x, y, collideable);
        this.description = description;
    }

    public void inspect(GraphicsContext gc){
        gc.drawImage(image, 100, 100);
        gc.fillText("Hello", 100, 100);
    }
}
