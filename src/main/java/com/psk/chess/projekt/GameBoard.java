package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameBoard {
    private static void gameBoardSetToBasic(FigureNames[][] gameBoard) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                gameBoard[i][j] = FigureNames.EMPTY;
            }
        }
/*
        gameBoard[1][3] = FigureNames.WHITEBISHOP;
        gameBoard[5][7] = FigureNames.WHITEROOK;
*/
        gameBoard[0][0] = FigureNames.BLACKROOK;
        gameBoard[0][1] = FigureNames.BLACKHORSEY;
        gameBoard[0][2] = FigureNames.BLACKBISHOP;
        gameBoard[0][3] = FigureNames.BLACKQUEEN;
        gameBoard[0][4] = FigureNames.BLACKKING;
        gameBoard[0][5] = FigureNames.BLACKBISHOP;
        gameBoard[0][6] = FigureNames.BLACKHORSEY;
        gameBoard[0][7] = FigureNames.BLACKROOK;

        gameBoard[7][0] = FigureNames.WHITEROOK;
        gameBoard[7][1] = FigureNames.WHITEHORSEY;
        gameBoard[7][2] = FigureNames.WHITEBISHOP;
        gameBoard[7][3] = FigureNames.WHITEQUEEN;
        gameBoard[7][4] = FigureNames.WHITEKING;
        gameBoard[7][5] = FigureNames.WHITEBISHOP;
        gameBoard[7][6] = FigureNames.WHITEHORSEY;
        gameBoard[7][7] = FigureNames.WHITEROOK;

        for (int i = 0; i < 8; i++) {
            gameBoard[1][i] = FigureNames.BLACKPAWN;
            gameBoard[6][i] = FigureNames.WHITEPAWN;
        }
    }
    public static void gameInnit(Pane pane, Scene scene, Stage stage) {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        Rectangle[][] figureTextures = new Rectangle[8][8];
        MovingFigures.SelectedFigure selectedFigure = new MovingFigures.SelectedFigure();
        gameBoardSetToBasic(gameBoard);
        GameLoop gameLoop = new GameLoop(scene, pane, stage, gameBoard, figureTextures, selectedFigure);
        gameLoop.start();
    }
}
