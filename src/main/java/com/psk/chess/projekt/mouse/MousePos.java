package com.psk.chess.projekt.mouse;

import javafx.scene.Scene;

import static java.lang.Math.floor;

public class MousePos {
    public static class MouseCoordinatesRelative {
        public int x;
        public int y;
    }

    public static class MouseCoordinates {
        public int x;
        public int y;
    }

    public static void getMouseCoordsRelativeToChessBoard(Scene scene, MouseCoordinates mouseCoordinates, MouseCoordinatesRelative mouseCoordinatesRelative) {
        scene.setOnMouseMoved(event -> {
            mouseCoordinates.x = (int) floor(event.getSceneX());
            mouseCoordinates.y = (int) floor(event.getSceneY());
            mouseCoordinatesRelative.x = (int) floor(event.getSceneX() / 64);
            mouseCoordinatesRelative.y = (int) floor(event.getSceneY() / 64);
        });
        if (mouseCoordinatesRelative.x > 7) {
            mouseCoordinatesRelative.x = 7;
        }
        if (mouseCoordinatesRelative.y > 7) {
            mouseCoordinatesRelative.y = 7;
        }
        if (mouseCoordinatesRelative.x < 0) {
            mouseCoordinatesRelative.x = 0;
        }
        if (mouseCoordinatesRelative.y < 0) {
            mouseCoordinatesRelative.y = 0;
        }
    }
}
