package com.psk.chess.projekt.figures.checks;

import com.psk.chess.projekt.mouse.MousePos;
import com.psk.chess.projekt.MovingFigures;
import com.psk.chess.projekt.figures.*;

import static com.psk.chess.projekt.Globals.gameWonByBlack;
import static com.psk.chess.projekt.Globals.gameWonByWhite;
import static com.psk.chess.projekt.Globals.gameWonByStalemate;

public class CheckmateChecker {
    private int amountOfLegalMovesForWhite = 0;
    private int amountOfLegalMovesForBlack = 0;
    private FigureNames[][] originalGameBoard;
    private MousePos.MouseCoordinatesRelative mouseCoordinatesRelative;
    private boolean[][] whiteDangerFields;
    private boolean[][] blackDangerFields;
    private int kingPosX = 0;
    private int kingPosY = 0;

    public CheckmateChecker(FigureNames[][] gameBoard, MousePos.MouseCoordinatesRelative mouseCoordinatesRelative, boolean[][] whiteDangerFields, boolean[][] blackDangerFields) {
        this.originalGameBoard = gameBoard;
        this.mouseCoordinatesRelative = mouseCoordinatesRelative;
        this.whiteDangerFields = whiteDangerFields;
        this.blackDangerFields = blackDangerFields;
    }

    public void lookForCheckmates() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (originalGameBoard[i][j] != FigureNames.EMPTY) {
                    checkAllLegalMoves(originalGameBoard[i][j], i, j);
                }
            }
        }
//        System.out.println(FigureNames.WHITEKING + ":");
//        for (int xi = 0; xi < 8; xi++) {
//            for (int xj = 0; xj < 8; xj++) {
//                System.out.print(whiteDangerFields[xi][xj] ? "🟥" : "🟩");
//            }
//            System.out.println();
//        }
//        System.out.println(FigureNames.BLACKKING + ":");
//        for (int xi = 0; xi < 8; xi++) {
//            for (int xj = 0; xj < 8; xj++) {
//                System.out.print(blackDangerFields[xi][xj] ? "🟥" : "🟩");
//            }
//            System.out.println();
//        }


        System.out.println("Black: " + amountOfLegalMovesForBlack);
        System.out.println("White: " + amountOfLegalMovesForWhite);
        if(this.amountOfLegalMovesForWhite == 0) {
            gameWonByBlack = true;
        }
        if(this.amountOfLegalMovesForBlack == 0) {
            gameWonByWhite = true;
        }
        checkForKing(FigureNames.WHITEKING);
        if(this.amountOfLegalMovesForWhite == 0 && !whiteDangerFields[kingPosY][kingPosX]) {
            gameWonByStalemate = true;
        }
        checkForKing(FigureNames.BLACKKING);
        if(this.amountOfLegalMovesForBlack == 0 && !blackDangerFields[kingPosY][kingPosX]) {
            gameWonByStalemate = true;
        }
    }

    private void checkForKing(FigureNames king) {
        for (kingPosY = 0; kingPosY < 8; kingPosY++) {
            for (kingPosX = 0; kingPosX < 8; kingPosX++) {
                if (originalGameBoard[kingPosY][kingPosX] == king) {
                    return;
                }
            }
        }
    }

    private void checkAllLegalMoves(FigureNames figure, int fig_y, int fig_x) {
        Movement movement = null;
        boolean[][] dangerFieldsWhite = new boolean[8][8];
        boolean[][] dangerFieldsBlack = new boolean[8][8];
        Check check_white = new Check(dangerFieldsWhite);
        Check check_black = new Check(dangerFieldsBlack);
        check_white.fillSafeFieldsWithNotSafeFieldsForWhiteKing(this.originalGameBoard);
        check_black.fillSafeFieldsWithNotSafeFieldsForBlackKing(this.originalGameBoard);

        MovingFigures.SelectedFigure selectedFigure = new MovingFigures.SelectedFigure();
        selectedFigure.figure = figure;
        selectedFigure.figure_y = fig_y;
        selectedFigure.figure_x = fig_x;

        CheckChecker whiteKingCheckChecker = new CheckChecker(this.originalGameBoard, mouseCoordinatesRelative, selectedFigure, FigureNames.WHITEKING);
        CheckChecker blackKingCheckChecker = new CheckChecker(this.originalGameBoard, mouseCoordinatesRelative, selectedFigure, FigureNames.BLACKKING);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (figure) {
                    case WHITEPAWN:
                        movement = new PawnMovement(this.originalGameBoard, fig_y, fig_x, i, j, true, false);
                        break;
                    case WHITEKING:
                        movement = new KingMovement(this.originalGameBoard, fig_y, fig_x, i, j, true, dangerFieldsWhite, false);
                        break;
                    case WHITEROOK:
                        movement = new RookMovement(this.originalGameBoard, fig_y, fig_x, i, j, true);
                        break;
                    case WHITEBISHOP:
                        movement = new BishopMovement(this.originalGameBoard, fig_y, fig_x, i, j, true);
                        break;
                    case WHITEQUEEN:
                        movement = new QueenMovement(this.originalGameBoard, fig_y, fig_x, i, j, true);
                        break;
                    case WHITEHORSEY:
                        movement = new HorseyMovement(this.originalGameBoard, fig_y, fig_x, i, j, true);
                        break;
                    default:
                        movement = null;
                        break;
                }
                try {
                    if (movement.isMoveLegal() && whiteKingCheckChecker.willMoveGetKingOutOfCheck()) {
                        this.amountOfLegalMovesForWhite++;
                    }
                } catch (NullPointerException e) {
                    //System.out.println("CheckmateChecker.java White NullPointerException.");
                }
                switch (figure) {
                    case BLACKPAWN:
                        movement = new PawnMovement(this.originalGameBoard, fig_y, fig_x, i, j, false, false);
                        break;
                    case BLACKKING:
                        movement = new KingMovement(this.originalGameBoard, fig_y, fig_x, i, j, false, dangerFieldsBlack, false);
                        break;
                    case BLACKROOK:
                        movement = new RookMovement(this.originalGameBoard, fig_y, fig_x, i, j, false);
                        break;
                    case BLACKBISHOP:
                        movement = new BishopMovement(this.originalGameBoard, fig_y, fig_x, i, j, false);
                        break;
                    case BLACKQUEEN:
                        movement = new QueenMovement(this.originalGameBoard, fig_y, fig_x, i, j, false);
                        break;
                    case BLACKHORSEY:
                        movement = new HorseyMovement(this.originalGameBoard, fig_y, fig_x, i, j, false);
                        break;
                    default:
                        movement = null;
                        break;
                }
                try {
                    if (movement.isMoveLegal() && blackKingCheckChecker.willMoveGetKingOutOfCheck()) {
                        this.amountOfLegalMovesForBlack++;
                    }
                } catch (NullPointerException e) {
                    //System.out.println("CheckmateChecker.java Black NullPointerException.");
                }
            }
        }
    }
}
