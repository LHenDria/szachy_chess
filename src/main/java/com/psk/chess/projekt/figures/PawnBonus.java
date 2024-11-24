package com.psk.chess.projekt.figures;

public class PawnBonus
{
    // Zamiana pionka na królówke.
    public boolean End(FigureNames[][] gameBoard, int figure_y, int figure_x)
    {
        if(gameBoard[figure_y][figure_x] == FigureNames.WHITEPAWN)
        {
            if(figure_y == 1)
            {
                return true;
            }
        } else if(gameBoard[figure_y][figure_x] == FigureNames.BLACKPAWN) {
            if(figure_y == 6)
            {
                return true;
            }
        }
        return false;
    }
}
