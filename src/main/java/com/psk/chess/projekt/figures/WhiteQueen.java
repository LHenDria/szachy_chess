package com.psk.chess.projekt.figures;

public class WhiteQueen extends Figure {
    public FigureNames figure_name = FigureNames.WHITEQUEEN;
    @Override
    public boolean isMoveLegal() {
        return false;
    }
}
