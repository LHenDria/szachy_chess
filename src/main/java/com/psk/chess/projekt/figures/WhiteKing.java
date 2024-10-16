package com.psk.chess.projekt.figures;

public class WhiteKing extends Figure {
    public FigureNames figure_name = FigureNames.WHITEKING;
    @Override
    public boolean isMoveLegal() {
        return false;
    }
}
