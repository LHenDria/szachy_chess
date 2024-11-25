package com.psk.chess.projekt.figures;

public class BishopMovement extends Movement {
    @Override
    public boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        boolean blocked1 = false;
        boolean blocked2 = false;
        boolean blocked3 = false;
        boolean blocked4 = false;

        if (gameBoard[figure_y][figure_x] == FigureNames.WHITEBISHOP) {
            for(int k = 1; k < 8; k++) {
                if(mouse_x > figure_x && mouse_y > figure_y && figure_y + k <= 7 && figure_x + k <= 7) {
                    if(gameBoard[figure_y + k][figure_x + k] != FigureNames.EMPTY) {
                        blocked1 = true;
                    }
                    if(figure_y + k == mouse_y && figure_x + k == mouse_x && (!blocked1 || isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
                if(mouse_x < figure_x && mouse_y > figure_y && figure_y + k <= 7 && figure_x - k >= 0) {
                    if(gameBoard[figure_y + k][figure_x - k] != FigureNames.EMPTY) {
                        blocked2 = true;
                    }
                    if(figure_y + k == mouse_y && figure_x - k == mouse_x && (!blocked2 || isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
                if(mouse_x < figure_x && mouse_y < figure_y && figure_y - k >= 0 && figure_x - k >= 0) {
                    if(gameBoard[figure_y - k][figure_x - k] != FigureNames.EMPTY) {
                        blocked3 = true;
                    }
                    if(figure_y - k == mouse_y && figure_x - k == mouse_x && (!blocked3 || isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
                if(mouse_x > figure_x && mouse_y < figure_y && figure_y + k >= 0 && figure_x + k <= 7) {
                    if(gameBoard[figure_y - k][figure_x + k] != FigureNames.EMPTY) {
                        blocked4 = true;
                    }
                    if(figure_y - k == mouse_y && figure_x + k == mouse_x && (!blocked4 || isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
            }
        } else {
            for(int k = 1; k < 8; k++) {
                if(mouse_x > figure_x && mouse_y > figure_y && figure_y + k <= 7 && figure_x + k <= 7) {
                    if(gameBoard[figure_y + k][figure_x + k] != FigureNames.EMPTY) {
                        blocked1 = true;
                    }
                    if(figure_y + k == mouse_y && figure_x + k == mouse_x && (!blocked1 || !isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
                if(mouse_x < figure_x && mouse_y > figure_y && figure_y + k <= 7 && figure_x - k >= 0) {
                    if(gameBoard[figure_y + k][figure_x - k] != FigureNames.EMPTY) {
                        blocked2 = true;
                    }
                    if(figure_y + k == mouse_y && figure_x - k == mouse_x && (!blocked2 || !isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
                if(mouse_x < figure_x && mouse_y < figure_y && figure_y - k >= 0 && figure_x - k >= 0) {
                    if(gameBoard[figure_y - k][figure_x - k] != FigureNames.EMPTY) {
                        blocked3 = true;
                    }
                    if(figure_y - k == mouse_y && figure_x - k == mouse_x && (!blocked3 || !isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
                if(mouse_x > figure_x && mouse_y < figure_y && figure_y + k >= 0 && figure_x + k <= 7) {
                    if(gameBoard[figure_y - k][figure_x + k] != FigureNames.EMPTY) {
                        blocked4 = true;
                    }
                    if(figure_y - k == mouse_y && figure_x + k == mouse_x && (!blocked4 || !isFigureBlack(gameBoard, mouse_y, mouse_x))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
