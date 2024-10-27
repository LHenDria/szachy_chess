package com.psk.chess.projekt;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.core.math.FXGLMath.floor;

public class SimpleShapes {
    public static void createSquare(float x, float y, float width, float height, Color color, Pane pane) {
        Rectangle square = new Rectangle(x, y, width, height);
        square.setStroke(color);
        square.setFill(color);
        square.setStrokeWidth(1.0);
        pane.getChildren().add(square);
    }

    public static void createSquareTextured(float x, float y, float width, float height, String texturePath, Pane pane, Rectangle[][] figureTextures) {
        Rectangle square = new Rectangle(x, y, width, height);
        square.setFill(new ImagePattern(new Image(System.getProperty("user.dir") + "/src/main/resources/com/psk/chess/projekt/textures/" + texturePath), 0, 0, 1, 1, true));
        square.setStrokeWidth(1.0);
        pane.getChildren().add(square);
        figureTextures[floor(y / 64)][floor(x / 64)] = square;
    }
}
