package com.psk.chess.projekt;

import javafx.scene.Scene;

import static java.lang.Math.floor;

public class Mouse {
    static class MouseCoordinates {
        int x;
        int y;
    }

    public static void getMouseCoordsRelativeToChessBoard(Scene scene, MouseCoordinates mouseCoordinates) {
        scene.setOnMouseMoved(event -> {
            mouseCoordinates.x = (int) floor(event.getSceneX()/64);
            mouseCoordinates.y = (int) floor(event.getSceneY()/64);
        });
    }
}
