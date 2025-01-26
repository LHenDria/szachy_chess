package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.psk.chess.projekt.Globals.*;
import static com.psk.chess.projekt.SimpleShapes.createSquare;
import static com.psk.chess.projekt.menus.GameEndText.drawEndText;

/**
 * Klasa która przechowuje metody odpowiedzialne za rysowanie elementów na ekranie.
 */
public class Drawing {
    /**
     * Metoda wywoła metody, które narysują na ekranie menu z figurami podczas promocji pionka, kiedy pionek dojdzie do końca planszy.
     * @param gameBoard szachownica gry.
     * @param pane panel gry.
     */
    public static void drawPromotionFigures(FigureNames[][] gameBoard,Pane pane) {
        SimpleShapes.createSquare(128, 224, 64, 64, Color.WHITESMOKE, pane);
        SimpleShapes.createSquare(192, 224, 64, 64, Color.WHITESMOKE, pane);
        SimpleShapes.createSquare(256, 224, 64, 64, Color.WHITESMOKE, pane);
        SimpleShapes.createSquare(320, 224, 64, 64, Color.WHITESMOKE, pane);

        for (int i = 0; i < 8; i++) {
            if (gameBoard[0][i] == FigureNames.WHITEPAWN) {
                SimpleShapes.createSquareTexturedPromotion(128, 224, 64, 64, "whitebishop.png", pane);
                SimpleShapes.createSquareTexturedPromotion(192, 224, 64, 64, "whitehorsey.png", pane);
                SimpleShapes.createSquareTexturedPromotion(256, 224, 64, 64, "whiterook.png", pane);
                SimpleShapes.createSquareTexturedPromotion(320, 224, 64, 64, "whitequeen.png", pane);
            }
            if (gameBoard[7][i] == FigureNames.BLACKPAWN) {
                SimpleShapes.createSquareTexturedPromotion(128, 224, 64, 64, "blackbishop.png", pane);
                SimpleShapes.createSquareTexturedPromotion(192, 224, 64, 64, "blackhorsey.png", pane);
                SimpleShapes.createSquareTexturedPromotion(256, 224, 64, 64, "blackrook.png", pane);
                SimpleShapes.createSquareTexturedPromotion(320, 224, 64, 64, "blackqueen.png", pane);
            }
        }
    }

    /**
     * Metoda narysuje na ekranie szachownicę.
     * @param pane panel gry.
     */
    public static void createChessBoard(Pane pane) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                createSquare(j*128,i*128+64,64,64, Color.CORNFLOWERBLUE, pane);
            }
            for (int j = 0; j < 4; j++) {
                createSquare(j*128+64,i*128,64,64, Color.CORNFLOWERBLUE, pane);
            }
        }
    }

    /**
     * Metoda wywoła metody, które narysuja na ekranie kto wygrał grę,
     * @param pane panel gry.
     */
    public static void drawEndGameText(Pane pane) {
        if(gameWonByWhite) {
            drawEndText(pane, "White wins!", 5, 5, 250, 250, Color.BURLYWOOD);
        }
        if(gameWonByBlack) {
            drawEndText(pane, "Black wins!", 5, 5, 250, 250, Color.TURQUOISE);
        }
        if (gameWonByStalemate) {
            drawEndText(pane, "Stalemate...", 5, 5, 250, 250, Color.CRIMSON);
        }
    }

    /**
     * Metoda, która narysuje na ekranie figury i będzie je aktualizować.
     * @param gameBoard szachownica gry.
     * @param figureTextures macierz z teksturami figur.
     * @param pane panel gry.
     */
    static public void updateFiguresOnBoard(FigureNames[][] gameBoard, Rectangle[][] figureTextures, Pane pane) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (gameBoard[i][j]) {
                    case WHITEKING:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whiteking.png", pane, figureTextures);
                        break;
                    case WHITEQUEEN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitequeen.png", pane, figureTextures);
                        break;
                    case WHITEROOK:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whiterook.png", pane, figureTextures);
                        break;
                    case WHITEBISHOP:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitebishop.png", pane, figureTextures);
                        break;
                    case WHITEHORSEY:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitehorsey.png", pane, figureTextures);
                        break;
                    case WHITEPAWN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitepawn.png", pane, figureTextures);
                        break;
                    case BLACKKING:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackking.png", pane, figureTextures);
                        break;
                    case BLACKQUEEN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackqueen.png", pane, figureTextures);
                        break;
                    case BLACKROOK:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackrook.png", pane, figureTextures);
                        break;
                    case BLACKBISHOP:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackbishop.png", pane, figureTextures);
                        break;
                    case BLACKHORSEY:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackhorsey.png", pane, figureTextures);
                        break;
                    case BLACKPAWN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackpawn.png", pane, figureTextures);
                        break;
                    case EMPTY:
                        pane.getChildren().remove(figureTextures[i][j]);
                        figureTextures[i][j] = null;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
