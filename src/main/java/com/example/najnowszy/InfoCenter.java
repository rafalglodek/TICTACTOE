package com.example.najnowszy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class InfoCenter {
    private StackPane pane;
    private Label message;
    private Button startGameButton;
    @FXML
    public StackPane getStackPane() {
        return pane;
    }
    public InfoCenter() {
        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        pane.setTranslateX(UIConstants.APP_WIDTH/2);
        pane.setTranslateY(UIConstants.INFO_CENTER_HEIGHT/2);

        message = new Label("Tick-Tack-Toe");
        message.setMinSize(UIConstants.APP_WIDTH, UIConstants.INFO_CENTER_HEIGHT);
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20);
        pane.getChildren().add(message);
        startGameButton = new Button("Start new game");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(20);
        pane.getChildren().add(startGameButton);
    }
    @FXML
    public void hideStartButton() {
        startGameButton.setVisible(true);
    }
    @FXML
    public void  updateMessage (String message) {
        this.message.setText(message);
    }
    @FXML
    public  void showStartButton() {
        startGameButton.setVisible(true);
    }
    @FXML
    public  void showStartButton1() {
        startGameButton.setVisible(true);
    }
    @FXML
    public void setStartGameButtonOnAction(EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }

}
