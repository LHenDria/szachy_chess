package com.psk.chess.projekt;

import com.psk.chess.projekt.api.Member;
import com.psk.chess.projekt.mouse.MousePos;
import com.psk.chess.projekt.figures.*;
import com.psk.chess.projekt.figures.checks.Check;
import com.psk.chess.projekt.figures.checks.CheckChecker;
import com.psk.chess.projekt.figures.checks.CheckmateChecker;
import com.psk.chess.projekt.menus.Menus;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Arrays;

import static com.psk.chess.projekt.GameLoop.gameBoardSetToBasic;
import static com.psk.chess.projekt.Globals.*;

/**
 * Klasa, która odpowiada za główną logikę gry oraz za poruszanie się figur.
 */
public class MovingFigures {
    static {
        Arrays.fill(whiteEnPassantStates, EnPassantStates.NOTELIGABLE);
        Arrays.fill(blackEnPassantStates, EnPassantStates.NOTELIGABLE);
    }

    /**
     * Klasa zawiera informacje o wybranej figurze.
     */
    public static class SelectedFigure {
        public FigureNames figure = FigureNames.EMPTY;
        public int figure_x = -1;
        public int figure_y = -1;
    }

    /**
     * Metoda, która sprawdzi z ilości tur czy jest tura białego, czy czarnego.
     */
    private static void changeTurns() {
        if (number_of_turns % 2 == 0) {
            is_white_turn = true;
        } else {
            is_white_turn = false;
        }
    }

    /**
     * Metoda tworzy słuchacza, który jest aktywowana za każdym kliknięciem kursora.
     * Po kliknięciu zostanie sprawdzone, w którym menu jesteśmy.
     * Jeżeli jesteśmy w menu głównym to będziemy mogli naciskać na przyciski, żeby wejść do innych menu lub wyjść z gry.
     * Jeżeli jesteśmy w menu online to będziemy mogli wybrać czy chcemy stworzyć serwer czy dołączyc do serwera jako klient.
     * Jeżeli jesteśmy w grze online to zostanie wysłany sygnał do klienta i zostanie ustawiona szachownica gry.
     * Jeżeli jesteśmy w grze offline to gra będzie sprawdzać czy wybralismy figurę. Jeżeli ją wybraliśmy to zostanie wywołana metoda
     * moveFigure żeby wykonać ten ruch.
     * @param pane panel gry.
     * @param scene scena gry.
     * @param gameBoard szachownica gry.
     * @param mouseCoordinates koordynaty myszy.
     * @param mouseCoordinatesRelative koordynaty myszy w relacji do szachownicy.
     * @param selectedFigure informacje o wybranej figurze.
     */
    public static void selectFigure(Pane pane, Scene scene, FigureNames[][] gameBoard, MousePos.MouseCoordinates mouseCoordinates, MousePos.MouseCoordinatesRelative mouseCoordinatesRelative, SelectedFigure selectedFigure) {
        scene.setOnMouseClicked(event -> {
            switch (menu) {
                case ONLINE_GAME:
                    if(member == Member.CLIENT) {
                        client.onMessage("");
                    }
                    if(member == Member.SERVER) {
                        server.setGameBoard(gameBoard);
                    }
                    break;
                case ONLINE_MENU:
                    if (mouseCoordinates.x >= 64 && mouseCoordinates.x <= 384) {
                        if (mouseCoordinates.y >= 128 && mouseCoordinates.y <= 192) {
                            if(!client.getDidJoinRoom()) {
                                client.connect();
                            } else {
                                client.onMessage("");
                                menu = Menus.ONLINE_GAME;
                            }
                        }
                        if (mouseCoordinates.y >= 224 && mouseCoordinates.y <= 384) {
                            gameBoardSetToBasic(gameBoard);
                            server.start();
                        }
                        if (mouseCoordinates.y >= 400 && mouseCoordinates.y <= 464) {
                            menu = Menus.MAIN_MENU;
                        }
                    }
                    break;
                case MAIN_MENU:
                    if (mouseCoordinates.x >= 64 && mouseCoordinates.x <= 384) {
                        if (mouseCoordinates.y >= 128 && mouseCoordinates.y <= 192) {
                            gameBoardSetToBasic(gameBoard);
                            menu = Menus.OFFLINE_GAME;
                        }
                        if (mouseCoordinates.y >= 224 && mouseCoordinates.y <= 384) {
                            menu = Menus.ONLINE_MENU;
                        }
                        if (mouseCoordinates.y >= 320 && mouseCoordinates.y <= 384) {
                            System.exit(0);
                        }
                    }
                    break;
                case OFFLINE_GAME:
                    if (selectedFigure.figure_x == -1 && selectedFigure.figure_y == -1 && selectedFigure.figure == FigureNames.EMPTY) {
                        selectedFigure.figure_x = mouseCoordinatesRelative.x;
                        selectedFigure.figure_y = mouseCoordinatesRelative.y;
                        selectedFigure.figure = gameBoard[selectedFigure.figure_y][selectedFigure.figure_x];
                        System.out.println("Selected " + selectedFigure.figure);
                    } else {
                        moveFigure(pane, scene, gameBoard, mouseCoordinatesRelative, selectedFigure);
                    }
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Metoda ta ma za zadanie wykonywać ruchy gracza, a dokładniej ma sprawdzać, czy ruch, który został wykonany jest legalny,
     * sprawdzać, czy król jest w szachu, oraz sposobu na wyciągnięcie go z niego, promocję pionków, zmianę tur, i ostatecznie
     * sprawdzenia, czy gra dobiegła końca.
     * @param pane panel gry.
     * @param scene scena gry.
     * @param gameBoard szachownica gry.
     * @param mouseCoordinatesRelative koordynaty myszy w relacji do szachownicy.
     * @param selectedFigure wybrana figura.
     */
    public static void moveFigure(Pane pane, Scene scene, FigureNames[][] gameBoard, MousePos.MouseCoordinatesRelative mouseCoordinatesRelative, SelectedFigure selectedFigure) {
        changeTurns();
        Movement movement = null;
        boolean[][] dangerFieldsWhite = new boolean[8][8];
        boolean[][] dangerFieldsBlack = new boolean[8][8];
        Check check_white = new Check(dangerFieldsWhite);
        Check check_black = new Check(dangerFieldsBlack);
        check_white.fillSafeFieldsWithNotSafeFieldsForWhiteKing(gameBoard);
        check_black.fillSafeFieldsWithNotSafeFieldsForBlackKing(gameBoard);

        CheckChecker whiteKingCheckChecker = new CheckChecker(gameBoard, mouseCoordinatesRelative, selectedFigure, FigureNames.WHITEKING);
        CheckChecker blackKingCheckChecker = new CheckChecker(gameBoard, mouseCoordinatesRelative, selectedFigure, FigureNames.BLACKKING);

        switch (selectedFigure.figure) {
            case BLACKPAWN, WHITEPAWN:
                movement = new PawnMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn, true);
                break;
            case WHITEKING:
                movement = new KingMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn, dangerFieldsWhite, true);
                break;
            case BLACKKING:
                movement = new KingMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn, dangerFieldsBlack, true);
                break;
            case BLACKROOK, WHITEROOK:
                movement = new RookMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn);
                break;
            case BLACKBISHOP, WHITEBISHOP:
                movement = new BishopMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn);
                break;
            case BLACKQUEEN, WHITEQUEEN:
                movement = new QueenMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn);
                break;
            case BLACKHORSEY, WHITEHORSEY:
                movement = new HorseyMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn);
                break;
            default:
                break;
        }
        try {
            if (movement.isMoveLegal() && (whiteKingCheckChecker.willMoveGetKingOutOfCheck() || blackKingCheckChecker.willMoveGetKingOutOfCheck())) {
                gameBoard[selectedFigure.figure_y][selectedFigure.figure_x] = FigureNames.EMPTY;
                gameBoard[mouseCoordinatesRelative.y][mouseCoordinatesRelative.x] = selectedFigure.figure;
                number_of_turns++;
                System.out.println("Moved figure.");
            } else {
                System.out.println("Invalid move.");
            }
        } catch (NullPointerException e) {
            System.out.println("MovingFigures.java NullPointerException.");
        }
        check_white.fillSafeFieldsWithNotSafeFieldsForWhiteKing(gameBoard);
        check_black.fillSafeFieldsWithNotSafeFieldsForBlackKing(gameBoard);

        PawnBonus pawnBonus = new PawnBonus(gameBoard, scene);
        pawnBonus.promotePawn();

        CheckmateChecker checkmateChecker = new CheckmateChecker(gameBoard, mouseCoordinatesRelative, dangerFieldsWhite, dangerFieldsBlack);
        checkmateChecker.lookForCheckmates();

        GameEnding gameEnding = new GameEnding(checkmateChecker, pane);

        if (number_of_turns % 2 == 0) {
            System.out.println("WHITE TURN");
        } else {
            System.out.println("BLACK TURN");
        }

        selectedFigure.figure_x = -1;
        selectedFigure.figure_y = -1;
        selectedFigure.figure = FigureNames.EMPTY;

        gameEnding.checkIfGameEnded();
    }
}
