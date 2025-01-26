package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Klasa, w której znajdują się metody które są wykorzystywane do inicjalizacji gry.
 */
public class GameBoard {
    /**
     * Metoda inicjalizuje najważnisze dla działania programu wartości, które będą wykorzystywane
     * podczas gry oraz tworzy pętlę gry. Te wartości to: gameBoard, czyli zachownica gry oraz figuretextures,
     * czyli macierz z teksturami figur.
     * @param pane panel gry.
     * @param scene scena gry.
     * @param stage okno gry.
     */
    public static void gameInnit(Pane pane, Scene scene, Stage stage) {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        Rectangle[][] figureTextures = new Rectangle[8][8];
        MovingFigures.SelectedFigure selectedFigure = new MovingFigures.SelectedFigure();

        GameLoop gameLoop = new GameLoop(scene, pane, stage, gameBoard, figureTextures, selectedFigure);
        gameLoop.start();
    }
}
