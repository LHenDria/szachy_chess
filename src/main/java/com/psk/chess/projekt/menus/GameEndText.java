package com.psk.chess.projekt.menus;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Klasa, które przechowuje metody do rysowania tekstu na koniec gry.
 */
public class GameEndText {
    /**
     * Metoda stworzy napis o określonym rozmiarze, pozycji, kolorze z określonym tekstem.
     * @param pane panel gry.
     * @param side tekst, który zostanie wyświetlony.
     * @param scaleX skala napisu na osi X.
     * @param scaleY skala napisu na osi Y.
     * @param transX przesunięcie napisu na osi X.
     * @param transY przesunięcie napisu na osi Y.
     * @param color kolor napisu.
     */
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
