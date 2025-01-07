package com.psk.chess.projekt.figures.checks;

import com.psk.chess.projekt.Mouse;
import com.psk.chess.projekt.MovingFigures;
import com.psk.chess.projekt.figures.*;

import java.util.Arrays;

import static com.psk.chess.projekt.MovingFigures.is_white_turn;
import static com.psk.chess.projekt.MovingFigures.number_of_turns;

public class CheckChecker {
    private FigureNames[][] gameBoard;
    private int kingPosX;
    private int kingPosY;
    private Mouse.MouseCoordinates mouseCoordinatesRelative;
    private MovingFigures.SelectedFigure selectedFigure;
    private FigureNames king;


    public CheckChecker(FigureNames[][] gameBoard, Mouse.MouseCoordinates mouseCoordinatesRelative, MovingFigures.SelectedFigure selectedFigure, FigureNames king) {
        this.gameBoard = gameBoard;
        this.mouseCoordinatesRelative = mouseCoordinatesRelative;
        this.selectedFigure = selectedFigure;
        this.king = king;
    }

    public boolean willMoveGetKingOutOfCheck() {
        boolean[][] dangerFields = new boolean[8][8];
        Check check = new Check(dangerFields);
        FigureNames[][] gameBoardCopy = new FigureNames[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoardCopy[i][j] = gameBoard[i][j];
            }
        }

        boolean blockable = false;
        Movement movement = null;
        switch (selectedFigure.figure) {
            case BLACKPAWN, WHITEPAWN:
                movement = new PawnMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn, true);
                break;
            case WHITEKING:
                movement = new KingMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn, dangerFields, true);
                break;
            case BLACKKING:
                movement = new KingMovement(gameBoard, selectedFigure.figure_y, selectedFigure.figure_x, mouseCoordinatesRelative.y, mouseCoordinatesRelative.x, is_white_turn, dangerFields, true);
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
            if (movement.isMoveLegal()) {
                blockable = true;
            } else {
                blockable = false;
            }
        } catch (NullPointerException e) {
            //System.out.println("CheckChecker.java NullPointerException.");
        }
        checkWhereIsKing();
        if (king == FigureNames.WHITEKING) {
            if (selectedFigure.figure == FigureNames.WHITEKING) {
                return true;
            } else if(blockable) {
                gameBoardCopy[selectedFigure.figure_y][selectedFigure.figure_x] = FigureNames.EMPTY;
                gameBoardCopy[mouseCoordinatesRelative.y][mouseCoordinatesRelative.x] = FigureNames.WHITEPAWN;
            }
            check.fillSafeFieldsWithNotSafeFieldsForWhiteKing(gameBoardCopy);
        }
        if (king == FigureNames.BLACKKING) {
            if (selectedFigure.figure == FigureNames.BLACKKING) {
                return true;
            } else if(blockable) {
                gameBoardCopy[selectedFigure.figure_y][selectedFigure.figure_x] = FigureNames.EMPTY;
                gameBoardCopy[mouseCoordinatesRelative.y][mouseCoordinatesRelative.x] = FigureNames.BLACKPAWN;
            }
            check.fillSafeFieldsWithNotSafeFieldsForBlackKing(gameBoardCopy);
        }
//        System.out.println("danger: ");
//        for (int xi = 0; xi < 8; xi++) {
//            for (int xj = 0; xj < 8; xj++) {
//                System.out.print(dangerFields[xi][xj] ? "🟥" : "🟩");
//            }
//            System.out.println();
//        }

        return !dangerFields[kingPosY][kingPosX];
    }

    private void checkWhereIsKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j] == king) {
                    kingPosX = j;
                    kingPosY = i;
                }
            }
        }
    }
}
