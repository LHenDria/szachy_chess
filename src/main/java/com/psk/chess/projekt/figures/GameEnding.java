package com.psk.chess.projekt.figures;

import com.psk.chess.projekt.figures.checks.CheckmateChecker;
import com.psk.chess.projekt.menus.Menus;
import javafx.scene.layout.Pane;

import static com.psk.chess.projekt.Globals.gameWonByBlack;
import static com.psk.chess.projekt.Globals.gameWonByWhite;
import static com.psk.chess.projekt.Globals.gameWonByStalemate;
import static com.psk.chess.projekt.Globals.menu;

/**
 * Klasa która odpowaida za zakańczanie gry i przenoszanie do menu głównego.
 */
public class GameEnding {
    /**
     * Obiekt klasy CheckmateChecker
     */
    private CheckmateChecker checkmateChecker;
    /**
     * Panel gry.
     */
    private Pane pane;

    /**
     * Konstruktor klasy GameEnding. Tylko przypisuje wartości do pól.
     * @param checkmateChecker obiekt klasy CheckmateChecker
     * @param pane panel gry.
     */
    public GameEnding(CheckmateChecker checkmateChecker, Pane pane) {
        this.checkmateChecker = checkmateChecker;
        this.pane = pane;
    }

    /**
     * Metoda sprawdza czy gra się zakończyła i kto wygrał. Jeżeli gra się zakończyła do przeniesie nas do menu głównego i
     * wyczyści panel gry.
     */
    public void checkIfGameEnded() {
        if (gameWonByStalemate) {
            menu = Menus.MAIN_MENU;
            pane.getChildren().clear();
        } else if (gameWonByWhite) {
            menu = Menus.MAIN_MENU;
            pane.getChildren().clear();
        } else if (gameWonByBlack) {
            menu = Menus.MAIN_MENU;
            pane.getChildren().clear();
        }
    }
}
