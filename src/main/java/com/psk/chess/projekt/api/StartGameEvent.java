package com.psk.chess.projekt.api;

import com.psk.chess.projekt.menus.Menus;

import static com.psk.chess.projekt.Globals.menu;

/**
 * Event który miał rozpoczynać grę online.
 */
public class StartGameEvent extends Event {
    public void startGame() {
        menu = Menus.ONLINE_GAME;
    }
}
