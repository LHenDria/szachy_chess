package com.psk.chess.projekt.figures;

public class PawnMovement extends Movement {
    @Override
    public boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        if (gameBoard[figure_y][figure_x] == FigureNames.WHITEPAWN) {
            // Poruszanie się o dwa lub jedno pole w przód.
            if (figure_y == 6) {
                if ((mouse_y == figure_y - 2 || mouse_y == figure_y - 1) && mouse_x == figure_x && gameBoard[figure_y - 1][figure_x] == FigureNames.EMPTY && gameBoard[figure_y - 2][figure_x] == FigureNames.EMPTY) {
                    return true;
                }
            }
            // Zbijanie innych figur.
            if(mouse_y == figure_y - 1 && (mouse_x == figure_x - 1 || mouse_x == figure_x + 1) && (isFigureBlack(gameBoard, figure_y - 1, figure_x - 1) || isFigureBlack(gameBoard, figure_y - 1, figure_x + 1))) {
                return true;
            }
            // Poruszanie się o jedno pole w przód.
            if (mouse_y == figure_y - 1 && mouse_x == figure_x && gameBoard[figure_y - 1][figure_x] == FigureNames.EMPTY) {
                return true;
            } else {
                return false;
            }
        } else {
            // Poruszanie się o dwa lub jedno pole w przód.
            if (figure_y == 1) {
                if ((mouse_y == figure_y + 2 || mouse_y == figure_y + 1) && mouse_x == figure_x && gameBoard[figure_y + 1][figure_x] == FigureNames.EMPTY && gameBoard[figure_y + 2][figure_x] == FigureNames.EMPTY) {
                    return true;
                }
            }
            // Zbijanie innych figur.
            if(mouse_y == figure_y + 1 && (mouse_x == figure_x - 1 || mouse_x == figure_x + 1) && (!isFigureBlack(gameBoard, figure_y + 1, figure_x - 1) || !isFigureBlack(gameBoard, figure_y + 1, figure_x + 1))) {
                return true;
            }
            // Poruszanie się o jedno pole w przód.
            if (mouse_y == figure_y + 1 && mouse_x == figure_x && gameBoard[figure_y + 1][figure_x] == FigureNames.EMPTY) {
                return true;
            } else {
                return false;
            }
        }
    }
}
