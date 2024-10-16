package com.psk.chess.projekt;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class SimpleShapes {
    public static void drawSquare(float x, float y, float width, float height, Color color, Group root) {
        Rectangle square = new Rectangle(x, y, width, height);
        square.setStroke(color);
        square.setFill(color);
        square.setStrokeWidth(1.0);
        root.getChildren().addAll(square);
    }

    public static void drawSquareTextured(float x, float y, float width, float height, String texturePath, Group root) {
        Rectangle square = new Rectangle(x, y, width, height);
        square.setFill(new ImagePattern(new Image(System.getProperty("user.dir") + "/src/main/resources/com/psk/chess/projekt/textures/" + texturePath), 0, 0, 1, 1, true));
        square.setStrokeWidth(1.0);
        root.getChildren().addAll(square);
    }
}
