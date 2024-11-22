package com.psk.chess.projekt.figures;

public class BishopMovement extends Movement {
    @Override
    public boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        return false;
    }
}
