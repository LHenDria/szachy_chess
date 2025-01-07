package com.psk.chess.projekt.figures;

public class QueenMovement extends Movement {
    public QueenMovement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn) {
        super(gameBoard, figure_y, figure_x, mouse_y, mouse_x, is_white_turn);
    }

    @Override
    public boolean isMoveLegal() {
        if (gameBoard[figure_y][figure_x] == FigureNames.WHITEQUEEN && is_white_turn) {
            if (figure_x == mouse_x) {
                return isVerticalMoveLegal(gameBoard, figure_y, figure_x, mouse_y);
            } else if (figure_y == mouse_y) {
                return isHorizontalMoveLegal(gameBoard, figure_y, figure_x, mouse_x);
            } else {
                return moveDiagonally(isFigureBlack(gameBoard, mouse_y, mouse_x));
            }
        }
        if (gameBoard[figure_y][figure_x] == FigureNames.BLACKQUEEN && !is_white_turn) {
            if (figure_x == mouse_x) {
                return isVerticalMoveLegal(gameBoard, figure_y, figure_x, mouse_y);
            } else if (figure_y == mouse_y) {
                return isHorizontalMoveLegal(gameBoard, figure_y, figure_x, mouse_x);
            } else {
                return moveDiagonally(isFigureWhite(gameBoard, mouse_y, mouse_x));
            }
        }
        return false;
    }

    private boolean moveDiagonally(boolean isFigureBlackForCapture) {
        boolean blocked1 = false;
        boolean blocked2 = false;
        boolean blocked3 = false;
        boolean blocked4 = false;

        for(int k = 1; k < 8; k++) {
            if(mouse_x > figure_x && mouse_y > figure_y && figure_y + k <= 7 && figure_x + k <= 7) {
                if(gameBoard[figure_y + k][figure_x + k] != FigureNames.EMPTY) {
                    blocked1 = true;
                }
                if(figure_y + k == mouse_y && figure_x + k == mouse_x && (!blocked1 || isFigureBlackForCapture)) {
                    return true;
                }
            }
            if(mouse_x < figure_x && mouse_y > figure_y && figure_y + k <= 7 && figure_x - k >= 0) {
                if(gameBoard[figure_y + k][figure_x - k] != FigureNames.EMPTY) {
                    blocked2 = true;
                }
                if(figure_y + k == mouse_y && figure_x - k == mouse_x && (!blocked2 || isFigureBlackForCapture)) {
                    return true;
                }
            }
            if(mouse_x < figure_x && mouse_y < figure_y && figure_y - k >= 0 && figure_x - k >= 0) {
                if(gameBoard[figure_y - k][figure_x - k] != FigureNames.EMPTY) {
                    blocked3 = true;
                }
                if(figure_y - k == mouse_y && figure_x - k == mouse_x && (!blocked3 || isFigureBlackForCapture)) {
                    return true;
                }
            }
            if(mouse_x > figure_x && mouse_y < figure_y && figure_y - k >= 0 && figure_x + k <= 7) {
                if(gameBoard[figure_y - k][figure_x + k] != FigureNames.EMPTY) {
                    blocked4 = true;
                }
                if(figure_y - k == mouse_y && figure_x + k == mouse_x && (!blocked4 || isFigureBlackForCapture)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isVerticalMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y) {
        int minY = Math.min(figure_y, mouse_y);
        int maxY = Math.max(figure_y, mouse_y);
        for (int i = minY + 1; i < maxY; i++) {
            if (gameBoard[i][figure_x] != FigureNames.EMPTY) {
                return false;
            }
        }
        return isTargetLegal(gameBoard, figure_y, figure_x, mouse_y, figure_x);
    }

    private boolean isHorizontalMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_x) {
        int minX = Math.min(figure_x, mouse_x);
        int maxX = Math.max(figure_x, mouse_x);

        for (int i = minX + 1; i < maxX; i++) {
            if (gameBoard[figure_y][i] != FigureNames.EMPTY) {
                return false;
            }
        }
        return isTargetLegal(gameBoard, figure_y, figure_x, figure_y, mouse_x);
    }

    private boolean isTargetLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        FigureNames targetPiece = gameBoard[mouse_y][mouse_x];
        if (targetPiece == FigureNames.EMPTY) {
            return true;
        }
        return isOpponent(gameBoard, figure_y, figure_x, mouse_y, mouse_x);
    }

    private boolean isOpponent(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        boolean isFigureBlack = isFigureBlack(gameBoard, figure_y, figure_x);
        boolean isTargetBlack = isFigureBlack(gameBoard, mouse_y, mouse_x);

        return isFigureBlack != isTargetBlack;
    }
}
