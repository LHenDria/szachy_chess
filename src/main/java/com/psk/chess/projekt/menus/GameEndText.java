package com.psk.chess.projekt.menus;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameEndText {
    public static void drawEndText(Pane pane, String side, double scaleX, double scaleY, double transX, double transY, Color color) {
        Text text = new Text();
        text.setText(side);
        text.setScaleX(scaleX);
        text.setScaleY(scaleY);
        text.setTranslateX(transX);
        text.setTranslateY(transY);
        text.setFill(color);
        pane.getChildren().add(text);
    }
}
