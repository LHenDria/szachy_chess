package com.psk.chess.projekt;

import javafx.scene.Scene;

import static java.lang.Math.floor;

public class Mouse {
    public static class MouseCoordinates {
        public int x;
        public int y;
    }

    public static void getMouseCoordsRelativeToChessBoard(Scene scene, MouseCoordinates mouseCoordinates) {
        scene.setOnMouseMoved(event -> {
            mouseCoordinates.x = (int) floor(event.getSceneX()/64);
            mouseCoordinates.y = (int) floor(event.getSceneY()/64);
        });
        if(mouseCoordinates.x > 7) {
            mouseCoordinates.x = 7;
        }
        if(mouseCoordinates.y > 7) {
            mouseCoordinates.y = 7;
        }
        if(mouseCoordinates.x < 0) {
            mouseCoordinates.x = 0;
        }
        if(mouseCoordinates.y < 0) {
            mouseCoordinates.y = 0;
        }
    }
}
