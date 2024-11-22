package com.psk.chess.projekt.figures;

public abstract class Movement {

    // Abstrakcyjna metoda do zaimplementowania przez podklasy, która sprawdza, czy ruch jest legalny.
    public abstract boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x);

    // Uproszczona wersja metody isFigureBlack.
    public boolean isFigureBlack(FigureNames[][] gameBoard, int figure_y, int figure_x) {
        FigureNames figure = gameBoard[figure_y][figure_x];

        // Sprawdzamy, czy figura jest czarna, używając instrukcji switch.
        return switch (figure) {
            case BLACKKING, BLACKQUEEN, BLACKROOK, BLACKBISHOP, BLACKHORSEY, BLACKPAWN -> true;
            default -> false;
        };
    }

}
