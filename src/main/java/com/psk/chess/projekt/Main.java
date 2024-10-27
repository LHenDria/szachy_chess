package com.psk.chess.projekt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.psk.chess.projekt.Game.gameInnit;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 512, 512, Color.LIGHTBLUE);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
        gameInnit(pane, scene, stage);
    }

    public static void main(String[] args) {
        launch();
    }
}