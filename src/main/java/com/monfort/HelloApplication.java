package com.monfort;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class HelloApplication extends Application {
    private static Stage window;
    static Scene scene;
    static HashSet<String> currentlyActiveKeys;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 600);
        window = primaryStage;
        window.setTitle("Hello World");
        window.setScene(scene);
        prepareActionHandlers();
        window.show();
    }

    private static void prepareActionHandlers() {
        currentlyActiveKeys = new HashSet<String>();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.add(event.getCode().toString());

            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.remove(event.getCode().toString());

            }
        });
    }

    public static File openSaveDialog(){
        File recordDir = new File(System.getProperty("user.home"), "/DungeonJavaFX/records");
        if(!recordDir.exists()){
            recordDir.mkdirs();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(recordDir);
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All file", "*.*"));
        File file = fileChooser.showSaveDialog(window);
        return file;
    }

    public static File getSave(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        return file;
    }

    public static void main(String[] args) {
        launch();
    }
}