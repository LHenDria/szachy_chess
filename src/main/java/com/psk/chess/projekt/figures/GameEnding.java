package com.psk.chess.projekt.figures;

import com.psk.chess.projekt.figures.checks.CheckmateChecker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.psk.chess.projekt.GameLoop.allowFirstCreationOfButtons;
import static com.psk.chess.projekt.GameLoop.menus;
import static com.psk.chess.projekt.MovingFigures.number_of_turns;

public class GameEnding {
    private CheckmateChecker checkmateChecker;
    private Pane pane;

    public GameEnding(CheckmateChecker checkmateChecker, Pane pane) {
        this.checkmateChecker = checkmateChecker;
        this.pane = pane;
    }

    public void checkIfGameEnded() {
        if (checkmateChecker.didStalemate) {
            Text text = new Text();
            text.setText("Stalemate...");
            text.setScaleX(5.0);
            text.setScaleY(5.0);
            text.setTranslateX(250.0);
            text.setTranslateY(250.0);
            text.setFill(Color.BURLYWOOD);
            pane.getChildren().add(text);
            menus = Menus.MAIN_MENU;

            allowFirstCreationOfButtons = true;
            pane.getChildren().clear();
            number_of_turns = 0;
        } else if (checkmateChecker.didWhiteWin) {
            Text text = new Text();
            text.setText("White wins!");
            text.setScaleX(5.0);
            text.setScaleY(5.0);
            text.setTranslateX(250.0);
            text.setTranslateY(250.0);
            text.setFill(Color.TURQUOISE);
            pane.getChildren().add(text);
            menus = Menus.MAIN_MENU;

            allowFirstCreationOfButtons = true;
            pane.getChildren().clear();
            number_of_turns = 0;
        } else if (checkmateChecker.didBlackWin) {
            Text text = new Text();
            text.setText("Black wins!");
            text.setScaleX(5.0);
            text.setScaleY(5.0);
            text.setTranslateX(250.0);
            text.setTranslateY(250.0);
            text.setFill(Color.CRIMSON);
            pane.getChildren().add(text);
            menus = Menus.MAIN_MENU;

            allowFirstCreationOfButtons = true;
            pane.getChildren().clear();
            number_of_turns = 0;
        }
    }
}
