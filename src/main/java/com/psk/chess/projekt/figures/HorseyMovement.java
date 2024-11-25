package com.psk.chess.projekt.figures;

public class HorseyMovement extends Movement {
    @Override
    public boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        if(gameBoard[figure_y][figure_x] == FigureNames.WHITEHORSEY) {
            if(figure_y - 1 >= 0 && figure_x - 2 >= 0) {
                if(mouse_y == figure_y - 1 && mouse_x == figure_x - 2 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y - 1 >= 0 && figure_x + 2 <= 7) {
                if(mouse_y == figure_y - 1 && mouse_x == figure_x + 2 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 1 <= 7 && figure_x - 2 >= 0) {
                if(mouse_y == figure_y + 1 && mouse_x == figure_x - 2 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 1 <= 7 && figure_x + 2 <= 7) {
                if(mouse_y == figure_y + 1 && mouse_x == figure_x + 2 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y - 2 >= 0 && figure_x - 1 >= 0) {
                if(mouse_y == figure_y - 2 && mouse_x == figure_x - 1 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y - 2 >= 0 && figure_x + 1 <= 7) {
                if(mouse_y == figure_y - 2 && mouse_x == figure_x + 1 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 2 <= 7 && figure_x - 1 >= 0) {
                if(mouse_y == figure_y + 2 && mouse_x == figure_x - 1 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 2 <= 7 && figure_x + 1 <= 7) {
                if(mouse_y == figure_y + 2 && mouse_x == figure_x + 1 && (isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
        } else {
            if(figure_y - 1 >= 0 && figure_x - 2 >= 0) {
                if(mouse_y == figure_y - 1 && mouse_x == figure_x - 2 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y - 1 >= 0 && figure_x + 2 <= 7) {
                if(mouse_y == figure_y - 1 && mouse_x == figure_x + 2 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 1 <= 7 && figure_x - 2 >= 0) {
                if(mouse_y == figure_y + 1 && mouse_x == figure_x - 2 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 1 <= 7 && figure_x + 2 <= 7) {
                if(mouse_y == figure_y + 1 && mouse_x == figure_x + 2 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y - 2 >= 0 && figure_x - 1 >= 0) {
                if(mouse_y == figure_y - 2 && mouse_x == figure_x - 1 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y - 2 >= 0 && figure_x + 1 <= 7) {
                if(mouse_y == figure_y - 2 && mouse_x == figure_x + 1 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 2 <= 7 && figure_x - 1 >= 0) {
                if(mouse_y == figure_y + 2 && mouse_x == figure_x - 1 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
            if(figure_y + 2 <= 7 && figure_x + 1 <= 7) {
                if(mouse_y == figure_y + 2 && mouse_x == figure_x + 1 && (!isFigureBlack(gameBoard, mouse_y, mouse_x) || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                    return true;
                }
            }
        }

        return false;
    }
}
