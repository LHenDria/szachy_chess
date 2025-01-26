package com.psk.chess.projekt.figures;

import com.psk.chess.projekt.mouse.MousePos;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.util.concurrent.CompletableFuture;

import static java.lang.Math.floor;

/**
 * Klasa, która odpowiada za promocję pionków.
 */
public class PawnBonus {
    /**
     * Szachownica gry.
     */
    private FigureNames[][] gameBoard;
    /**
     * Scena gry.
     */
    private Scene scene;
    /**
     * Pozycja myszy w relacji do szachownicy.
     */
    MousePos.MouseCoordinatesRelative mouse = new MousePos.MouseCoordinatesRelative();

    /**
     * Konstruktor klasy PawnBonus. Tylko przypisuje polom wartości.
     * @param gameBoard
     * @param scene
     */
    public PawnBonus(FigureNames[][] gameBoard, Scene scene) {
        this.gameBoard = gameBoard;
        this.scene = scene;
    }

    /**
     * Metoda sprawdzi, czy pionek doszedł do końca planszy. Po czym zacznie asynchronicznie czekać do momentu, w którym gracz
     * wybierze figurę.
     */
    public void promotePawn() {
        for (int i = 0; i < 8; i++) {
            if (gameBoard[0][i] == FigureNames.WHITEPAWN) {
                int finalI = i;
                waitForMousePress().thenAccept(MouseCoordinates -> {
                    Platform.runLater(() -> {
                        if (mouse.x > 128 && mouse.x < 192 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[0][finalI] = FigureNames.WHITEBISHOP;
                        } else if (mouse.x > 192 && mouse.x < 256 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[0][finalI] = FigureNames.WHITEHORSEY;
                        } else if (mouse.x > 256 && mouse.x < 320 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[0][finalI] = FigureNames.WHITEROOK;
                        } else if (mouse.x > 320 && mouse.x < 384 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[0][finalI] = FigureNames.WHITEQUEEN;
                        } else {
                            gameBoard[0][finalI] = FigureNames.WHITEQUEEN;
                        }
                    });
                });
            }
            if (gameBoard[7][i] == FigureNames.BLACKPAWN) {
                int finalI = i;
                waitForMousePress().thenAccept(MouseCoordinates -> {
                    Platform.runLater(() -> {
                        if (mouse.x > 128 && mouse.x < 192 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[7][finalI] = FigureNames.BLACKBISHOP;
                        } else if (mouse.x > 192 && mouse.x < 256 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[7][finalI] = FigureNames.BLACKHORSEY;
                        } else if (mouse.x > 256 && mouse.x < 320 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[7][finalI] = FigureNames.BLACKROOK;
                        } else if (mouse.x > 320 && mouse.x < 384 && mouse.y > 224 && mouse.y < 288) {
                            gameBoard[7][finalI] = FigureNames.BLACKQUEEN;
                        } else {
                            gameBoard[7][finalI] = FigureNames.BLACKQUEEN;
                        }
                    });
                });
            }
        }
    }

    /**
     * Metoda, która czeka do momentu, w którym zostanie naciśnięty przycisk myszy. Po czym ustawia pozycję zmiennej mouse
     * na tą, w której został naciśnięty.
     * @return zwraca CompletableFuture.
     */
    private CompletableFuture waitForMousePress() {
        CompletableFuture<MousePos.MouseCoordinatesRelative> future = new CompletableFuture<>();
        scene.setOnMousePressed(event -> {
            mouse.x = (int) floor(event.getSceneX());
            mouse.y = (int) floor(event.getSceneY());
            future.complete(mouse);
        });
        return future;
    }
}
