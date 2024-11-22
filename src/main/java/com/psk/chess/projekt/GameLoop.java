package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.psk.chess.projekt.Drawing.*;
import static com.psk.chess.projekt.MovingFigures.selectFigure;


public class GameLoop extends AnimationTimer {
    private Mouse.MouseCoordinates mouseCoordinatesRelative = new Mouse.MouseCoordinates();
    private Scene scene;
    private Pane pane;
    private Stage stage;
    private FigureNames[][] gameBoard;
    private Rectangle[][] figureTextures;
    private MovingFigures.SelectedFigure selectedFigure;

    public GameLoop(Scene scene, Pane pane, Stage stage, FigureNames[][] gameBoard, Rectangle[][] figureTextures, MovingFigures.SelectedFigure selectedFigure) {
        this.scene = scene;
        this.pane = pane;
        this.stage = stage;
        this.gameBoard = gameBoard;
        this.figureTextures = figureTextures;
        selectFigure(pane, scene, gameBoard, mouseCoordinatesRelative, selectedFigure);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                System.out.println(selectedFigure.figure_x + "," + selectedFigure.figure_y + ", " + selectedFigure.figure);
            }
            if (event.getCode() == KeyCode.F2) {
                System.out.println(mouseCoordinatesRelative.x + "," + mouseCoordinatesRelative.y);
            }
            if (event.getCode() == KeyCode.F3) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(gameBoard[i][j] + " ");
                    }
                    System.out.println();
                }
            }
        });
    }

    @Override
    public void handle(long now) {
        pane.getChildren().clear();
        Mouse.getMouseCoordsRelativeToChessBoard(scene, mouseCoordinatesRelative);
        createChessBoard(pane);
        updateFiguresOnBoard(gameBoard, figureTextures, pane);
        pane.layout();
    }
}
