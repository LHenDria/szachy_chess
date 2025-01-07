package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.*;
import com.psk.chess.projekt.figures.checks.Check;
import com.psk.chess.projekt.figures.checks.CheckChecker;
import com.psk.chess.projekt.figures.checks.CheckmateChecker;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public class MovingFigures {
    public static int number_of_turns = 0;
    public static boolean is_white_turn = true;
    public static EnPassantStates[] whiteEnPassantStates = new EnPassantStates[8];
    public static EnPassantStates[] blackEnPassantStates = new EnPassantStates[8];

    // blok statyczny pog
    static {
        Arrays.fill(whiteEnPassantStates, EnPassantStates.NOTELIGABLE);
        Arrays.fill(blackEnPassantStates, EnPassantStates.NOTELIGABLE);
    }

    public static class SelectedFigure {
        public FigureNames figure = FigureNames.EMPTY;
        public int figure_x = -1;
        public int figure_y = -1;
    }

    private static void changeTurns() {
        if (number_of_turns % 2 == 0) {
            is_white_turn = true;
        } else {
            is_white_turn = false;
        }
    }

    public static void selectFigure(Pane pane, Scene scene, FigureNames[][] gameBoard, Mouse.MouseCoordinates mouseCoordinatesRelative, SelectedFigure selectedFigure, Menus menus) {
        scene.setOnMouseClicked(event -> {
            if (selectedFigure.figure_x == -1 && selectedFigure.figure_y == -1 && selectedFigure.figure == FigureNames.EMPTY) {
                selectedFigure.figure_x = mouseCoordinatesRelative.x;
                selectedFigure.figure_y = mouseCoordinatesRelative.y;
                selectedFigure.figure = gameBoard[selectedFigure.figure_y][selectedFigure.figure_x];
                System.out.println("Selected " + selectedFigure.figure);
            } else {
                moveFigure(pane, scene, gameBoard, mouseCoordinatesRelative, selectedFigure, menus);
            }
        });
    }

    public static void moveFigure(Pane pane, Scene scene, FigureNames[][] gameBoard, Mouse.MouseCoordinates mouseCoordinatesRelative, SelectedFigure selectedFigure, Menus menus) {
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
