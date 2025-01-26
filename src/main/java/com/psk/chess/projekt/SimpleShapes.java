package com.psk.chess.projekt;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.core.math.FXGLMath.floor;

/**
 * Klasa zawiera w sobie metody do rysowania prostych kształtów.
 */
public class SimpleShapes {
    /**
     * Metoda rysuje kwadrat.
     * @param x pozycja startowa kwadratu na osi X.
     * @param y pozycja startowa kwadratu na osi Y.
     * @param width szerokość kwadratu.
     * @param height wysokosć wkadratu.
     * @param color kolor kwadratu.
     * @param pane panel gry.
     */
    public static void createSquare(float x, float y, float width, float height, Color color, Pane pane) {
        Rectangle square = new Rectangle(x, y, width, height);
        square.setStroke(color);
        square.setFill(color);
        square.setStrokeWidth(1.0);
        pane.getChildren().add(square);
    }

    /**
     * Metoda, która ma za zadanie narysować kwadrat z teksturą.
     * @param x pozycja startowa kwadratu na osi X.
     * @param y pozycja startowa kwadratu na osi Y.
     * @param width szerokość kwadratu.
     * @param height wysokosć wkadratu.
     * @param texturePath ścieżka do kwadratu.
     * @param pane panel gry.
     * @param figureTextures macierz tesktur szachownicy.
     */
    public static void createSquareTextured(float x, float y, float width, float height, String texturePath, Pane pane, Rectangle[][] figureTextures) {
        Rectangle square = new Rectangle(x, y, width, height);
        square.setFill(new ImagePattern(new Image(System.getProperty("user.dir") + "/src/main/resources/com/psk/chess/projekt/textures/" + texturePath), 0, 0, 1, 1, true));
        square.setStrokeWidth(1.0);
        pane.getChildren().add(square);
        figureTextures[floor(y / 64)][floor(x / 64)] = square;
    }

    /**
     * Metoda, która ma za zadanie narysować kwadrat z teksturą bez macierzy tekstur szachownicy.
     * @param x pozycja startowa kwadratu na osi X.
     * @param y pozycja startowa kwadratu na osi Y.
     * @param width szerokość kwadratu.
     * @param height wysokosć wkadratu.
     * @param texturePath ścieżka do kwadratu.
     * @param pane panel gry.
     */
    public static void createSquareTexturedPromotion(float x, float y, float width, float height, String texturePath, Pane pane) {
        Rectangle square = new Rectangle(x, y, width, height);
        square.setFill(new ImagePattern(new Image(System.getProperty("user.dir") + "/src/main/resources/com/psk/chess/projekt/textures/" + texturePath), 0, 0, 1, 1, true));
        square.setStrokeWidth(1.0);
        pane.getChildren().add(square);
    }
}
