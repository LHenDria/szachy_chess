package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Klasa w której znajdują się metody które są wykorzystywane do inicjalizacji gry.
 */
public class GameBoard {
    /**
     * Metoda gameInnit() inicjalizuje najważnisze dla działania programu wartości które będą wykorzystywane
     * podczas gry, oraz tworzy pętlę gry. Te wartości to:
     * gameBoard - macierz 8x8 typów FigureNames, jest to odzwierciedlenie szachownicy,
     * figureTextures - macierz 8x8 objektów klasy Rectangle, wykorzystywane do poprawnego usuwania elementów.
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
