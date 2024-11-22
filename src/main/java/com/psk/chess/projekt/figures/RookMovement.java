package com.psk.chess.projekt.figures;

public class RookMovement extends Movement {

    @Override
    public boolean isMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        FigureNames piece = gameBoard[figure_y][figure_x];

        // Sprawdzenie, czy figura to wieża
        if (piece != FigureNames.WHITEROOK && piece != FigureNames.BLACKROOK) {
            return false; // Jeśli nie jest to wieża, ruch jest nielegalny
        }

        // Ruch wieży: pionowy lub poziomy
        if (figure_x == mouse_x) {
            return isVerticalMoveLegal(gameBoard, figure_y, figure_x, mouse_y);  // Ruch pionowy
        } else if (figure_y == mouse_y) {
            return isHorizontalMoveLegal(gameBoard, figure_y, figure_x, mouse_x);  // Ruch poziomy
        }

        return false; // Wieża nie porusza się po skosie
    }

    // Sprawdzanie, czy ruch pionowy jest legalny
    private boolean isVerticalMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y) {
        int minY = Math.min(figure_y, mouse_y);
        int maxY = Math.max(figure_y, mouse_y);

        // Sprawdzamy, czy droga jest wolna
        for (int i = minY + 1; i < maxY; i++) {
            if (gameBoard[i][figure_x] != FigureNames.EMPTY) {
                return false; // Zablokowana droga
            }
        }

        // Sprawdzamy pole docelowe
        return isTargetLegal(gameBoard, figure_y, figure_x, mouse_y, figure_x);
    }

    // Sprawdzanie, czy ruch poziomy jest legalny
    private boolean isHorizontalMoveLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_x) {
        int minX = Math.min(figure_x, mouse_x);
        int maxX = Math.max(figure_x, mouse_x);

        // Sprawdzamy, czy droga jest wolna
        for (int i = minX + 1; i < maxX; i++) {
            if (gameBoard[figure_y][i] != FigureNames.EMPTY) {
                return false; // Zablokowana droga
            }
        }

        // Sprawdzamy pole docelowe
        return isTargetLegal(gameBoard, figure_y, figure_x, figure_y, mouse_x);
    }

    // Sprawdzanie, czy na polu docelowym jest przeciwnik lub jest puste
    private boolean isTargetLegal(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        FigureNames targetPiece = gameBoard[mouse_y][mouse_x];

        // Jeśli pole docelowe jest puste, ruch jest legalny
        if (targetPiece == FigureNames.EMPTY) {
            return true;
        }

        // Sprawdzenie, czy figura na polu docelowym jest przeciwnikiem
        return isOpponent(gameBoard, figure_y, figure_x, mouse_y, mouse_x);
    }

    // Metoda pomocnicza do sprawdzania, czy figura na polu docelowym jest przeciwnikiem
    private boolean isOpponent(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x) {
        FigureNames piece = gameBoard[figure_y][figure_x];
        FigureNames targetPiece = gameBoard[mouse_y][mouse_x];

        // Używamy metody isFigureBlack, żeby sprawdzić kolor figury
        boolean isFigureBlack = isFigureBlack(gameBoard, figure_y, figure_x);
        boolean isTargetBlack = isFigureBlack(gameBoard, mouse_y, mouse_x);

        // Sprawdzamy, czy figury są przeciwnikami (biała kontra czarna)
        return isFigureBlack != isTargetBlack; // Jeśli kolory są różne, to są przeciwnikami
    }
}
