package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.scene.Group;

public class DrawFigures {
    static public void drawFigures(FigureNames[][] gameBoard, Group root) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j] != null) {
                    switch (gameBoard[i][j]) {
                        case WHITEKING:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "whiteking.png", root);
                            break;
                        case WHITEQUEEN:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "whitequeen.png", root);
                            break;
                        case WHITEROOK:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "whiterook.png", root);
                            break;
                        case WHITEBISHOP:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "whitebishop.png", root);
                            break;
                        case WHITEHORSEY:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "whitehorsey.png", root);
                            break;
                        case WHITEPAWN:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "whitepawn.png", root);
                            break;
                        case BLACKKING:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "blackking.png", root);
                            break;
                        case BLACKQUEEN:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "blackqueen.png", root);
                            break;
                        case BLACKROOK:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "blackrook.png", root);
                            break;
                        case BLACKBISHOP:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "blackbishop.png", root);
                            break;
                        case BLACKHORSEY:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "blackhorsey.png", root);
                            break;
                        case BLACKPAWN:
                            SimpleShapes.drawSquareTextured(j * 64, i * 64, 64, 64, "blackpawn.png", root);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
