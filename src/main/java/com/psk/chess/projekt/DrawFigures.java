package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class DrawFigures {
    static public void updateFiguresOnBoard(FigureNames[][] gameBoard, Rectangle[][] figureTextures, Group root) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (gameBoard[i][j]) {
                    case WHITEKING:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whiteking.png", root, figureTextures);
                        break;
                    case WHITEQUEEN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitequeen.png", root, figureTextures);
                        break;
                    case WHITEROOK:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whiterook.png", root, figureTextures);
                        break;
                    case WHITEBISHOP:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitebishop.png", root, figureTextures);
                        break;
                    case WHITEHORSEY:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitehorsey.png", root, figureTextures);
                        break;
                    case WHITEPAWN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "whitepawn.png", root, figureTextures);
                        break;
                    case BLACKKING:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackking.png", root, figureTextures);
                        break;
                    case BLACKQUEEN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackqueen.png", root, figureTextures);
                        break;
                    case BLACKROOK:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackrook.png", root, figureTextures);
                        break;
                    case BLACKBISHOP:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackbishop.png", root, figureTextures);
                        break;
                    case BLACKHORSEY:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackhorsey.png", root, figureTextures);
                        break;
                    case BLACKPAWN:
                        SimpleShapes.createSquareTextured(j * 64, i * 64, 64, 64, "blackpawn.png", root, figureTextures);
                        break;
                    case EMPTY:
                        root.getChildren().remove(figureTextures[i][j]);
                        figureTextures[i][j] = null;
                    default:
                        break;
                }
            }
        }
    }
}
