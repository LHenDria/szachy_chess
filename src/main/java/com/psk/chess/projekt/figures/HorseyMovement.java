package com.psk.chess.projekt.figures;

public class HorseyMovement extends Movement {
    public HorseyMovement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn) {
        super(gameBoard, figure_y, figure_x, mouse_y, mouse_x, is_white_turn);
    }

    @Override
    public boolean isMoveLegal() {
        if(gameBoard[figure_y][figure_x] == FigureNames.WHITEHORSEY && is_white_turn) {
            return moveHorsey(isFigureBlack(gameBoard, mouse_y, mouse_x));
        }
        if (gameBoard[figure_y][figure_x] == FigureNames.BLACKHORSEY && !is_white_turn) {
            return moveHorsey(!isFigureBlack(gameBoard, mouse_y, mouse_x));
        }
        return false;
    }

    private boolean moveHorsey(boolean isFigureBlackForCapture) {
        if(figure_y - 1 >= 0 && figure_x - 2 >= 0) {
            if(mouse_y == figure_y - 1 && mouse_x == figure_x - 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y - 1 >= 0 && figure_x + 2 <= 7) {
            if(mouse_y == figure_y - 1 && mouse_x == figure_x + 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 1 <= 7 && figure_x - 2 >= 0) {
            if(mouse_y == figure_y + 1 && mouse_x == figure_x - 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 1 <= 7 && figure_x + 2 <= 7) {
            if(mouse_y == figure_y + 1 && mouse_x == figure_x + 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y - 2 >= 0 && figure_x - 1 >= 0) {
            if(mouse_y == figure_y - 2 && mouse_x == figure_x - 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y - 2 >= 0 && figure_x + 1 <= 7) {
            if(mouse_y == figure_y - 2 && mouse_x == figure_x + 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 2 <= 7 && figure_x - 1 >= 0) {
            if(mouse_y == figure_y + 2 && mouse_x == figure_x - 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 2 <= 7 && figure_x + 1 <= 7) {
            if(mouse_y == figure_y + 2 && mouse_x == figure_x + 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        return false;
    }
}
