package com.psk.chess.projekt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.psk.chess.projekt.GameBoard.gameInnit;

/**
 * Klasa main od której rozpoczyna się działanie programu.
 */
public class Main extends Application {
    /**
     * Metoda start() tworzy okno gry, panel oraz scenę.
     * @param stage okno gry tworzone przez framework JavaFX.
     * @throws IOException wyjątek wyrzucany przez framework JavaFX.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 512, 512, Color.LIGHTBLUE);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
        gameInnit(pane, scene, stage);
    }

    /**
     * Metoda main() uruchamia metodę launch() z klasy Application.
     * @param args argumenty dla metody main().
     */
    public static void main(String[] args) {
        launch();
    }
}