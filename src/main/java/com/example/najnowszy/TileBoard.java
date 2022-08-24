package com.example.najnowszy;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileBoard {

    private InfoCenter infoCenter;
    private StackPane  pane;
    private Tile[][]  tiles =new Tile [3][3];
    private Line winningLine;

    private char playerTurn = 'X';
    private boolean isAndOfGame = false;

    public TileBoard(InfoCenter infoCenter) {
        this.infoCenter = infoCenter;
        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH,UIConstants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(UIConstants.APP_WIDTH/2);
        pane.setTranslateY((UIConstants.TILE_BOARD_HEIGHT/2)+UIConstants.INFO_CENTER_HEIGHT);

        addAllTiles();
        winningLine = new Line();
        pane.getChildren().add(winningLine);
    }
    @FXML
    public void startNewGame() {
        isAndOfGame=false;
        playerTurn='X';
        for (int row=0; row<3;row++) {
            for(int col =0; col<3;col++){
                tiles[row][col].setValue("");
            }
        }
        winningLine.setVisible(false);
    }

    @FXML
    public void changePlayerTurn() {
        if (playerTurn == 'X') {
            playerTurn = 'O';
        } else {
            playerTurn = 'X';
        }
        infoCenter.updateMessage("Player "+playerTurn+"'s turn");
    }
    @FXML
    public String getPlayerTurn() {
        return String.valueOf(playerTurn);
    }
    @FXML
    private void addAllTiles() {
        for (int row=0; row<3;row++) {
            for(int col =0; col<3;col++){
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((col*100)-100);
                tile.getStackPane().setTranslateY((row*100)-100);
                pane.getChildren().add(tile.getStackPane());
                tiles [row][col] = tile;
            }
        }
    }
    @FXML

    public StackPane getStackPane() {
        return pane;
    }
    private class Tile {

        private StackPane pane;
        private Label label;
        @FXML
        public  void checkForWinner() {
            checkRowsForWinner();
            checkColsForWinner();
            checkTopLeftToBottomRightForWinner();
            checkTopRightToBottomLeftForWinner();
            checkForStalemate();
        }
        @FXML
        private void checkRowsForWinner() {
            for (int row = 0; row < 3; row++) {
                if (tiles[row][0].getValue().equals(tiles[row][1].getValue()) &&
                        (tiles[row][0].getValue().equals(tiles[row][2].getValue()) &&
                                !tiles[row][0].getValue().isEmpty())) {
                    String winner = tiles[row][0].getValue();
                    endGame(winner, new WinningTiles(tiles[row][0],tiles[row][1],tiles[row][2]));
                    return;
                }
            }
        }
        @FXML
        private void checkColsForWinner() {
            if(!isAndOfGame) {
                for (int col = 0; col < 3; col++) {
                    if (tiles[0][col].getValue().equals(tiles[1][col].getValue()) &&
                            (tiles[0][col].getValue().equals(tiles[2][col].getValue()) &&
                                    !tiles[0][col].getValue().isEmpty())) {
                        String winner = tiles[0][col].getValue();
                        endGame(winner, new WinningTiles(tiles[0][col],tiles[1][col],tiles[2][col]));
                        return;
                    }
                }
            }

        }
        @FXML
        private void checkTopLeftToBottomRightForWinner() {
            if (!isAndOfGame) {
                if (tiles[0][0].getValue().equals(tiles[1][1].getValue())&&
                        (tiles[0][0].getValue().equals(tiles[2][2].getValue())&&
                                !tiles[0][0].getValue().isEmpty())) {
                    String winner = tiles[0][0].getValue();
                    endGame(winner, new WinningTiles(tiles[0][0],tiles[1][1],tiles[2][2]));
                    return;
                }
            }
        }
        @FXML
        private void checkTopRightToBottomLeftForWinner() {
            if (!isAndOfGame) {
                if (tiles[0][2].getValue().equals(tiles[1][1].getValue())&&
                        (tiles[0][2].getValue().equals(tiles[2][0].getValue())&&
                                !tiles[0][2].getValue().isEmpty())) {
                    String winner = tiles[0][2].getValue();
                    endGame(winner, new WinningTiles(tiles[0][2],tiles[1][1],tiles[2][0]));
                    return;
                }
            }

        }
        @FXML
        private void checkForStalemate() {
            if (!isAndOfGame) {
                for (int row=0; row<3;row++) {
                    for (int col=0;col<3;col++) {
                        if(tiles[row][col].getValue().isEmpty()) {
                            return;
                        }
                    }
                }
                isAndOfGame=true;
                infoCenter.updateMessage("Stalemate...");
                infoCenter.showStartButton();
            }
        }
        @FXML
        private void endGame(String winner, WinningTiles winningTiles) {
            isAndOfGame=true;
            drawWinningLine(winningTiles);
            infoCenter.updateMessage("Player "+winner+" wins!");
            infoCenter.showStartButton();
        }
        private class WinningTiles {
            Tile start;
            Tile middle;
            Tile end;

            public WinningTiles(Tile start, Tile middle, Tile end) {
                this.start = start;
                this.middle = middle;
                this.end = end;
            }
        }
        @FXML
        private void drawWinningLine(WinningTiles winningTiles) {
            winningLine.setStartX(winningTiles.start.getStackPane().getTranslateX());
            winningLine.setStartY(winningTiles.start.getStackPane().getTranslateY());
            winningLine.setEndX(winningTiles.end.getStackPane().getTranslateX());
            winningLine.setEndY(winningTiles.end.getStackPane().getTranslateY());
            winningLine.setTranslateX(winningTiles.middle.getStackPane().getTranslateX());
            winningLine.setTranslateY(winningTiles.middle.getStackPane().getTranslateY());
            winningLine.setVisible(true);
        }

        public Tile() {
            pane = new StackPane();
            pane.setMinSize(100,100);

            Rectangle border = new Rectangle();
            border.setHeight(100);
            border.setWidth(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {if (label.getText().isEmpty()&& !isAndOfGame){
                label.setText(getPlayerTurn());
                changePlayerTurn();
                checkForWinner();}
            });
        }
        @FXML
        public StackPane getStackPane() {
            return pane;
        }
        @FXML
        private String getValue() {
            return label.getText();
        }
        @FXML
        public void setValue(String value) {
            label.setText(value);
        }
    }

}
