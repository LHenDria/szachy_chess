package com.psk.chess.projekt.menus;

import javafx.scene.layout.Pane;

import static com.psk.chess.projekt.SimpleShapes.createSquareTexturedPromotion;

/**
 * Klasa przechowuje metody, które służą do tworzenia przycisków menu.
 */
public class CreateMenuButtons {
    /**
     * Metoda tworzy przyciski dla menu głównego.
     * @param pane panel gry.
     */
    public static void createMainMenuButtons(Pane pane) {
        createSquareTexturedPromotion(64, 128, 320, 64, "button_offline.png" , pane);
        createSquareTexturedPromotion(64, 224, 320, 64, "button_online.png" , pane);
        createSquareTexturedPromotion(64, 320, 320, 64, "button_quit_game.png" , pane);
    }
    /**
     * Metoda tworzy przyciski dla menu online.
     * @param pane panel gry.
     */
    public static void createOnlineMenuButtons(Pane pane) {
        createSquareTexturedPromotion(64, 128, 320, 64, "button_join_game.png" , pane);
        createSquareTexturedPromotion(64, 224, 320, 64, "button_create_game.png" , pane);
        createSquareTexturedPromotion(64, 400, 320, 64, "button_back.png" , pane);
    }
}
