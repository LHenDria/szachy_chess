package com.psk.chess.projekt.figures;

public abstract class Movement {
    protected FigureNames[][] gameBoard;
    protected int figure_y;
    protected int figure_x;
    protected int mouse_y;
    protected int mouse_x;
    protected boolean is_white_turn;

    Movement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn) {
        this.gameBoard = gameBoard;
        this.figure_y = figure_y;
        this.figure_x = figure_x;
        this.mouse_y = mouse_y;
        this.mouse_x = mouse_x;
        this.is_white_turn = is_white_turn;
    }

    public abstract boolean isMoveLegal();

    public boolean isFigureBlack(FigureNames[][] gameBoard, int figure_y, int figure_x) {
        FigureNames figure = gameBoard[figure_y][figure_x];
        return switch (figure) {
            case BLACKKING, BLACKQUEEN, BLACKROOK, BLACKBISHOP, BLACKHORSEY, BLACKPAWN -> true;
            default -> false;
        };
    }

    public boolean isFigureWhite(FigureNames[][] gameBoard, int figure_y, int figure_x) {
        FigureNames figure = gameBoard[figure_y][figure_x];
        return switch (figure) {
            case WHITEKING, WHITEQUEEN, WHITEROOK, WHITEBISHOP, WHITEHORSEY, WHITEPAWN -> true;
            default -> false;
        };
    }
}
