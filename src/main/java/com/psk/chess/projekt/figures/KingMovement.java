package com.psk.chess.projekt.figures;

import static com.psk.chess.projekt.figures.KingMovementHelper.*;

public class KingMovement extends Movement{
    @Override
    public boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        if(gameBoard[figure_y][figure_x] == FigureNames.WHITEKING) {
            // Poruszanie się.
            for(int i = -1; i <= 1; i++) {
                for(int j = -1; j <= 1; j++) {
                    if(mouse_x == figure_x + i && mouse_y == figure_y + j) {
                        if(mouse_x == figure_x && mouse_y == figure_y) {
                            return false;
                        }
                        boolean dangerFields[][] = new boolean[8][8];
                        fillSafeFieldsWithNotSafeFieldsForWhiteKing(gameBoard, dangerFields);

                        if(gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY && !dangerFields[mouse_y][mouse_x]) {
                            return true;
                        }
                        // Zbijanie innych figur.
                        if(isFigureBlack(gameBoard, mouse_y, mouse_x)  && !dangerFields[mouse_y][mouse_x]) {
                            return true;
                        }
                    }
                }
            }
        } else {
            for(int i = -1; i <= 1; i++) {
                for(int j = -1; j <= 1; j++) {
                    if(mouse_x == figure_x + i && mouse_y == figure_y + j) {
                        if(mouse_x == figure_x && mouse_y == figure_y) {
                            return false;
                        }
                        boolean dangerFields[][] = new boolean[8][8];
                        fillSafeFieldsWithNotSafeFieldsForBlackKing(gameBoard, dangerFields);

                        if(gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY && !dangerFields[mouse_y][mouse_x]) {
                            return true;
                        }
                        // Zbijanie innych figur.
                        if(isFigureBlack(gameBoard, mouse_y, mouse_x)  && !dangerFields[mouse_y][mouse_x]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
