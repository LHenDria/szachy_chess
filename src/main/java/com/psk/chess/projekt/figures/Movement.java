package com.psk.chess.projekt.figures;

public abstract class Movement {
    public abstract boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x);
    public boolean isFigureBlack(FigureNames[][] gameBoard, int figure_y, int figure_x) {
        if(gameBoard[figure_y][figure_x] == FigureNames.BLACKKING) {
            return true;
        }
        if(gameBoard[figure_y][figure_x] == FigureNames.BLACKQUEEN) {
            return true;
        }
        if(gameBoard[figure_y][figure_x] == FigureNames.BLACKROOK) {
            return true;
        }
        if(gameBoard[figure_y][figure_x] == FigureNames.BLACKBISHOP) {
            return true;
        }
        if(gameBoard[figure_y][figure_x] == FigureNames.BLACKHORSEY) {
            return true;
        }
        if(gameBoard[figure_y][figure_x] == FigureNames.BLACKPAWN) {
            return true;
        }
        return false;
    }
}
