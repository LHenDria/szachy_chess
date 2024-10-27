package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.psk.chess.projekt.SimpleShapes.createSquare;

public class Drawing {
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
