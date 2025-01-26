package com.psk.chess.projekt;

import com.psk.chess.projekt.api.Member;
import com.psk.chess.projekt.figures.EnPassantStates;
import com.psk.chess.projekt.mouse.MousePos;
import com.psk.chess.projekt.figures.FigureNames;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;

import static com.psk.chess.projekt.Drawing.*;
import static com.psk.chess.projekt.Globals.*;
import static com.psk.chess.projekt.MovingFigures.selectFigure;
import static com.psk.chess.projekt.menus.CreateMenuButtons.*;

/**
 * Klasa w której znajdują się metody do obsługi pętli gry oraz poprawnej rozgrywki.
 */
public class GameLoop extends AnimationTimer {
    /**
     * Koordynaty myszy w relacji do pól szachownicy.
     */
    private MousePos.MouseCoordinatesRelative mouseCoordinatesRelative = new MousePos.MouseCoordinatesRelative();
    /**
     * Koordynaty myszy.
     */
    private MousePos.MouseCoordinates mouseCoordinates = new MousePos.MouseCoordinates();
    /**
     * Scena gry.
     */
    private Scene scene;
    /**
     * Panel gry.
     */
    private Pane pane;
    /**
     * Okno gry.
     */
    private Stage stage;
    /**
     * Szachownica gry.
     */
    private FigureNames[][] gameBoard;
    /**
     * Tekstury figur.
     */
    private Rectangle[][] figureTextures;

    /**
     * Konstruktor klasy GameLoop. Wywołuje on metodę selectFigure() która jest wymaga podczas gry.
     * @param scene scena gry.
     * @param pane panel gry.
     * @param stage okno gry.
     * @param gameBoard szachownica gry.
     * @param figureTextures tekstury figur.
     * @param selectedFigure informacje o wybranej figurze.
     */
    public GameLoop(Scene scene, Pane pane, Stage stage, FigureNames[][] gameBoard, Rectangle[][] figureTextures, MovingFigures.SelectedFigure selectedFigure) {
        this.scene = scene;
        this.pane = pane;
        this.stage = stage;
        this.gameBoard = gameBoard;
        this.figureTextures = figureTextures;

        if(member == Member.CLIENT) {
            gameBoard = client.getGameBoard();
        }
        selectFigure(pane, scene, gameBoard, mouseCoordinates, mouseCoordinatesRelative, selectedFigure);
    }

    /**
     * Metoda która ustawi szachownicę gry do takiej jaka powinna być na początku gry.
     * @param gameBoard szachownica gry.
     */
    public static void gameBoardSetToBasic(FigureNames[][] gameBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = FigureNames.EMPTY;
            }
        }
        gameBoard[0][0] = FigureNames.BLACKROOK;
        gameBoard[0][1] = FigureNames.BLACKHORSEY;
        gameBoard[0][2] = FigureNames.BLACKBISHOP;
        gameBoard[0][3] = FigureNames.BLACKQUEEN;
        gameBoard[0][4] = FigureNames.BLACKKING;
        gameBoard[0][5] = FigureNames.BLACKBISHOP;
        gameBoard[0][6] = FigureNames.BLACKHORSEY;
        gameBoard[0][7] = FigureNames.BLACKROOK;

        gameBoard[7][0] = FigureNames.WHITEROOK;
        gameBoard[7][1] = FigureNames.WHITEHORSEY;
        gameBoard[7][2] = FigureNames.WHITEBISHOP;
        gameBoard[7][3] = FigureNames.WHITEQUEEN;
        gameBoard[7][4] = FigureNames.WHITEKING;
        gameBoard[7][5] = FigureNames.WHITEBISHOP;
        gameBoard[7][6] = FigureNames.WHITEHORSEY;
        gameBoard[7][7] = FigureNames.WHITEROOK;

        for (int i = 0; i < 8; i++) {
            gameBoard[1][i] = FigureNames.BLACKPAWN;
            gameBoard[6][i] = FigureNames.WHITEPAWN;
        }
    }

    /**
     * Metoda która odpowiada za odświeżanie różnych elementów które mogą się pojawić w czasie gry offline.
     */
    private void offlineGame() {
        updateFiguresOnBoard(gameBoard, figureTextures, pane);
        for (int i = 0; i < 8; i++) {
            if (gameBoard[0][i] == FigureNames.WHITEPAWN || gameBoard[7][i] == FigureNames.BLACKPAWN) {
                drawPromotionFigures(gameBoard, pane);
            }
        }
    }

    /**
     * Pętla gry. Odpowiada za to w którym menu się znajdujemy oraz wywoływanie odpowiednich metod do odświeżania ekranu gry.
     * @param now czas gry.
     */
    @Override
    public void handle(long now) {
        MousePos.getMouseCoordsRelativeToChessBoard(scene, mouseCoordinates, mouseCoordinatesRelative);
        if(!showEndGameText) {
            pane.getChildren().clear();
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("GameLoop thread interrupted");
            }
            showEndGameText = false;
        }
        createChessBoard(pane);
        switch (menu) {
            case MAIN_MENU:
                createMainMenuButtons(pane);
                drawEndGameText(pane);
                if(gameWonByWhite || gameWonByBlack || gameWonByStalemate) {
                    showEndGameText = true;
                    gameWonByWhite = false;
                    gameWonByBlack = false;
                    gameWonByStalemate = false;
                    number_of_turns = 0;
                    is_white_turn = true;
                    Arrays.fill(whiteEnPassantStates, EnPassantStates.NOTELIGABLE);
                    Arrays.fill(blackEnPassantStates, EnPassantStates.NOTELIGABLE);
                }
                break;
            case OFFLINE_GAME:
                offlineGame();
                break;
            case ONLINE_MENU:
                createOnlineMenuButtons(pane);
                break;
            case ONLINE_GAME:
                offlineGame();
                break;
        }
        pane.layout();
    }
}
