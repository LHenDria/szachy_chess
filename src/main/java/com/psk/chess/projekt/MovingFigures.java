package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MovingFigures {
    static class SelectedFigure {
        public FigureNames figure = FigureNames.EMPTY;
        public int figure_x = -1;
        public int figure_y = -1;
    }

    public static void selectFigure(Pane pane, Scene scene, FigureNames[][] gameBoard, Mouse.MouseCoordinates mouseCoordinatesRelative, SelectedFigure selectedFigure) {
        scene.setOnMouseClicked(event -> {
            if (selectedFigure.figure_x == -1 && selectedFigure.figure_y == -1 && selectedFigure.figure == FigureNames.EMPTY) {
                selectedFigure.figure_x = mouseCoordinatesRelative.x;
                selectedFigure.figure_y = mouseCoordinatesRelative.y;
                selectedFigure.figure = gameBoard[selectedFigure.figure_y][selectedFigure.figure_x];
                System.out.println("Selected " + selectedFigure.figure);
            } else {
                moveFigure(pane, gameBoard, mouseCoordinatesRelative, selectedFigure);
            }
        });
    }

    public static void moveFigure(Pane pane, FigureNames[][] gameBoard, Mouse.MouseCoordinates mouseCoordinatesRelative, SelectedFigure selectedFigure) {
        Movement movement = null;
        if(selectedFigure.figure == FigureNames.BLACKPAWN || selectedFigure.figure == FigureNames.WHITEPAWN) {
            movement = new PawnMovement();
        }
        if(selectedFigure.figure == FigureNames.BLACKKING || selectedFigure.figure == FigureNames.WHITEKING) {
            movement = new KingMovement();
        }
        if(selectedFigure.figure == FigureNames.BLACKROOK || selectedFigure.figure == FigureNames.WHITEROOK) {
            movement = new RookMovement();
        }
        try {
            if (movement.isMoveLegal(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x)) {
                gameBoard[selectedFigure.figure_y][selectedFigure.figure_x] = FigureNames.EMPTY;
                gameBoard[mouseCoordinatesRelative.y][mouseCoordinatesRelative.x] = selectedFigure.figure;
                System.out.println("Moved figure.");
            } else {
                System.out.println("Invalid move.");
            }
        } catch (NullPointerException e) {
            System.out.println("MovingFigures.java NullPointerException.");
        }
        selectedFigure.figure_x = -1;
        selectedFigure.figure_y = -1;
        selectedFigure.figure = FigureNames.EMPTY;
    }
}
