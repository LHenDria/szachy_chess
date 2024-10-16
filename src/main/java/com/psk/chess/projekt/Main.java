package com.psk.chess.projekt;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.psk.chess.projekt.Game.gameInnit;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 512, 512, Color.LIGHTBLUE);
        stage.setTitle("Chess");
        stage.setScene(scene);
        gameInnit(root);
    }

    public static void main(String[] args) {
        launch();
    }
}