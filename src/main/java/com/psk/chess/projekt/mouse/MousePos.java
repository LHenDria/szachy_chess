package com.psk.chess.projekt.mouse;

import javafx.scene.Scene;

import static java.lang.Math.floor;

/**
 * Klasa która przechowuje klasy i metody związane z obsługą myszy.
 */
public class MousePos {
    /**
     * Klasa, która przechowuje w sobie informację o pozycji myszy w relacji do szachownicy.
     */
    public static class MouseCoordinatesRelative {
        /**
         * Pozycja myszy w relacji z szachownicą na osi X.
         */
        public int x;
        /**
         * Pozycja myszy w relacji z szachownicą na osi Y.
         */
        public int y;
    }
    /**
     * Klasa, która przechowuje w sobie informację o pozycji myszy.
     */
    public static class MouseCoordinates {
        /**
         * Pozycja myszy na osi x.
         */
        public int x;
        /**
         * Pozycja myszy na osi Y.
         */
        public int y;
    }

    /**
     * Metoda tworzy słuchacza, który ma za zadanie aktualizować pozycję myszy z relacją do szachownicy i pozycję myszy bez relacji.
     * Jeżeli pozycja myszy z relacją do szachownicy wyjdzie poza zakres szachownicy 8*8 to zostanie ucięta.
     * @param scene scena gry.
     * @param mouseCoordinates koordynaty myszy.
     * @param mouseCoordinatesRelative koordynaty myszy w relacji do szachownicy.
     */
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
