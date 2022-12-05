package com.monfort;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class HelloController{

    private AnimationTimer h;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel, savedName;

    @FXML
    private Button editButton, saveCharacterButton;

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Button strengthRollButton, dexterityRollButton, intelligenceRollButton, constitutionRollButton,
            charismaRollButton, wisdomRollButton;
    @FXML
    private Label strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel,
            charismaValueLabel, wisdomValueLabel;

    int x=100, y=100;
    int saveCounter=0;
    boolean changedName = false;
    boolean savedCharacter = false;
    Player player;
    private File characterFile;

    @FXML
    public void initialize(){
        x = 60;
        y = 150;
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(font);
        nameField.setEditable(false);
        nameField.setVisible(false);
        saveCharacterButton.setVisible(false);

        try{
            characterFile = new File("character.deg");
            if(!characterFile.exists()){
                characterFile.createNewFile();
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        player = new Player("src/main/resources/com/monfort/guy.png",100,100);

        h = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0,0,gameCanvas.getWidth(),gameCanvas.getHeight());

                if(!savedCharacter){
                    gc.setFill( Color.RED );
                    gc.setStroke( Color.BLACK );
                    gc.setLineWidth(2);
                    gc.fillText( "No Character", x, y );
                    gc.fillText( "Create Character", x, y+100 );
                }
                else{
                    movement();
                    gc.setFill( Color.BISQUE );
                    gc.fillRect(0,0, gameCanvas.getWidth(),gameCanvas.getHeight());
                    player.draw(gc);
                    player.testCollision(gc);
                }
            }
        };
        h.start();
    }

    @FXML
    protected void save() throws FileNotFoundException {
        File file = HelloApplication.openSaveDialog();
        if(file != null){
            file = new File(file.getAbsolutePath() + ".deg");
            Formatter output = new Formatter(file);
            output.format("%s,%s,%s,%s,%s,%s,%s", nameField.getText(), strengthValueLabel.getText(), dexterityValueLabel.getText(),
                    constitutionValueLabel.getText(), intelligenceValueLabel.getText(), wisdomValueLabel.getText(), charismaValueLabel.getText());
            output.close();
        }
    }

    @FXML
    protected void open() throws FileNotFoundException {
        File file = HelloApplication.getSave();
        File f = new File(file.getAbsolutePath());
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();
        ArrayList<String> stuff = new ArrayList<>();
        while(line.contains(",")){
            stuff.add(line.substring(0, line.indexOf(",")).trim());
            line = line.substring(line.indexOf(",") + 1);
        }
        savedCharacter = true;
        nameField.setText(stuff.get(0));
        savedName.setText(stuff.get(0));
        nameLabel.setText(stuff.get(0));
        strengthValueLabel.setText(stuff.get(1));
        dexterityValueLabel.setText(stuff.get(2));
        constitutionValueLabel.setText(stuff.get(3));
        intelligenceValueLabel.setText(stuff.get(4));
        wisdomValueLabel.setText(stuff.get(5));
        charismaValueLabel.setText(stuff.get(6));
        System.out.println(stuff);
    }

    protected void movement(){
        if(HelloApplication.currentlyActiveKeys.contains(KeyCode.A.toString())){
            player.moveLeft();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.D.toString())) {
            player.moveRight();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.S.toString())) {
            player.moveDown();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.W.toString())) {
            player.moveUp();
        }
    }


    @FXML
    protected void editName(){
        String buttonText = editButton.getText();
        if(buttonText.equals("Edit")) {
            editButton.setText("Save");
            editButton.setLayoutX(300);
            nameLabel.setVisible(false);
            nameField.setVisible(true);
            nameField.setEditable(true);
        }
        else if(buttonText.equals("Save")) {
            editButton.setText("Edit");
            editButton.setLayoutX(300);
            nameLabel.setVisible(true);
            nameField.setVisible(false);
            nameField.setEditable(false);
            nameLabel.setText(nameField.getText());
            savedName.setText(nameLabel.getText());
            if(!changedName){
                saveCounter++;
            }
            changedName = true;
        }
    }

    protected int rolld20() {
        return (int)(1 + Math.random() * 20);
    }

    @FXML
    protected void onSaveButtonClicked() {
        saveCharacterButton.setVisible(false);
        strengthRollButton.setVisible(false);
        dexterityRollButton.setVisible(false);
        intelligenceRollButton.setVisible(false);
        constitutionRollButton.setVisible(false);
        charismaRollButton.setVisible(false);
        wisdomRollButton.setVisible(false);
        editButton.setVisible(false);
        savedCharacter = true;
    }

    @FXML
    protected void onRollButton(ActionEvent event) {
        Button b = (Button)event.getSource();

        if(b == strengthRollButton && b.isVisible()) {
            strengthValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == dexterityRollButton) {
            dexterityValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == constitutionRollButton) {
            constitutionValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == intelligenceRollButton) {
            intelligenceValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == wisdomRollButton) {
            wisdomValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == charismaRollButton) {
            charismaValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }

        if (saveCounter >= 7){
            saveCharacterButton.setVisible(true);
        }
    }//end onRollButton

}