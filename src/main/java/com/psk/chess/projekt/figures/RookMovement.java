package com.psk.chess.projekt.figures;

public class RookMovement extends Movement {
    public RookMovement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn) {
        super(gameBoard, figure_y, figure_x, mouse_y, mouse_x, is_white_turn);
    }

    @Override
    public boolean isMoveLegal() {
        if (gameBoard[figure_y][figure_x] == FigureNames.WHITEROOK && is_white_turn) {
            if (figure_x == mouse_x) {
                return isVerticalMoveLegal(gameBoard, figure_y, figure_x, mouse_y);
            } else if (figure_y == mouse_y) {
                return isHorizontalMoveLegal(gameBoard, figure_y, figure_x, mouse_x);
            }
        }
        if (gameBoard[figure_y][figure_x] == FigureNames.BLACKROOK && !is_white_turn) {
            if (figure_x == mouse_x) {
                return isVerticalMoveLegal(gameBoard, figure_y, figure_x, mouse_y);
            } else if (figure_y == mouse_y) {
                return isHorizontalMoveLegal(gameBoard, figure_y, figure_x, mouse_x);
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
