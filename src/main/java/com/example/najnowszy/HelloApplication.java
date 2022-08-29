package com.example.najnowszy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private InfoCenter infoCenter;
    private TileBoard tileBoard;
    @FXML
    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UIConstants.APP_WIDTH,UIConstants.APP_HEIGHT);
        initLayout(root);
        initTileBoard(root);
        initInfoCenter(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void initInfoCenter(BorderPane root) {
        infoCenter = new InfoCenter();
        infoCenter.setStartGameButtonOnAction(startNewGame());
        root.getChildren().add(infoCenter.getStackPane());
    }
    @FXML
    private EventHandler<ActionEvent> startNewGame() {
        return new  EventHandler<ActionEvent>() {
            @FXML
            @Override
            public void handle(ActionEvent actionEvent) {
                infoCenter.hideStartButton();
                infoCenter.updateMessage("");
                tileBoard.startNewGame();
            }
        };
    }

    private void initLayout(BorderPane root) {
        initInfoCenter(root);
        initTileBoard(root);
    }
    private void initLayout1(BorderPane root) {
        initInfoCenter(root);
        initTileBoard(root);
    }

    private void initTileBoard(BorderPane root) {
        tileBoard = new TileBoard(infoCenter);
        root.getChildren().add(tileBoard.getStackPane());

    }
    public static void main(String[] args) {
        launch();
    }
}