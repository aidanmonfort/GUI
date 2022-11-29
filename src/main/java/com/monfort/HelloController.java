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
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

        final Image image;
        try{
            image = new Image(new FileInputStream("src/main/resources/com/monfort/guy.png"));
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Could not find file");
        }

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
                    gc.drawImage(image, x,y);
                }
            }
        };
        h.start();
    }

    @FXML
    protected void movement(){
        if(HelloApplication.currentlyActiveKeys.contains(KeyCode.A.toString())){
            moveLeft();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.D.toString())) {
            moveRight();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.S.toString())) {
            moveDown();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.W.toString())) {
            moveUp();
        }
    }

    private void moveUp(){
        y -= 1;
    }

    private void moveDown(){
        y += 1;
    }

    private void moveRight(){
        x += 1;
    }

    private void moveLeft(){
        x -= 1;
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