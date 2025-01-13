package com.psk.chess.projekt.figures;

import com.psk.chess.projekt.figures.checks.CheckmateChecker;
import com.psk.chess.projekt.menus.Menus;
import javafx.scene.layout.Pane;

import static com.psk.chess.projekt.Globals.gameWonByBlack;
import static com.psk.chess.projekt.Globals.gameWonByWhite;
import static com.psk.chess.projekt.Globals.gameWonByStalemate;
import static com.psk.chess.projekt.Globals.menu;

public class GameEnding {
    private CheckmateChecker checkmateChecker;
    private Pane pane;

    public GameEnding(CheckmateChecker checkmateChecker, Pane pane) {
        this.checkmateChecker = checkmateChecker;
        this.pane = pane;
    }

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
