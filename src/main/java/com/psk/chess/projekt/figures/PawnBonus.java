package com.psk.chess.projekt.figures;

import com.psk.chess.projekt.Mouse;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.util.concurrent.CompletableFuture;

import static java.lang.Math.floor;

public class PawnBonus {
    private FigureNames[][] gameBoard;
    private Scene scene;
    Mouse.MouseCoordinates mouse = new Mouse.MouseCoordinates();

    public PawnBonus(FigureNames[][] gameBoard, Scene scene) {
        this.gameBoard = gameBoard;
        this.scene = scene;
    }

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

    private CompletableFuture waitForMousePress() {
        CompletableFuture<Mouse.MouseCoordinates> future = new CompletableFuture<>();
        scene.setOnMousePressed(event -> {
            mouse.x = (int) floor(event.getSceneX());
            mouse.y = (int) floor(event.getSceneY());
            future.complete(mouse);
        });
        return future;
    }
}
