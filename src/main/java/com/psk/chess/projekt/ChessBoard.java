package com.psk.chess.projekt;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import static com.psk.chess.projekt.SimpleShapes.createSquare;

public class ChessBoard {
    public static void createChessBoard(Group root) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                createSquare(j*128,i*128+64,64,64, Color.CORNFLOWERBLUE, root);
            }
            for (int j = 0; j < 4; j++) {
                createSquare(j*128+64,i*128,64,64, Color.CORNFLOWERBLUE, root);
            }
        }
    }
}
