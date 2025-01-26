package com.psk.chess.projekt.figures;

/**
 * Klasa, która reprezentuje poruszanie się gońca. Dziedziczy po klasie Movement.
 */
public class BishopMovement extends Movement {
    /**
     * Konstruktor klasy BishopMovement. Tylko przypisuje wartości do pól.
     * @param gameBoard szachownica gry.
     * @param figure_y pozycja figury na osi Y.
     * @param figure_x pozycja figury na osi X.
     * @param mouse_y pozycja na osi Y, na którą figura ma się ruszyć.
     * @param mouse_x pozycja na osi X, na którą figura ma się ruszyć.
     * @param is_white_turn czy jest to ruch białego.
     */
    public BishopMovement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn) {
        super(gameBoard, figure_y, figure_x, mouse_y, mouse_x, is_white_turn);
    }

    /**
     * Metoda wywoła odpowiednie metody do sprawdzenia, czy ruch, który chcemy wykonać jest legalny.
     * @return zwraca true, jeżeli ruch jest legalny, false, jeżeli nie jest.
     */
    @Override
    public boolean isMoveLegal() {
        if (gameBoard[figure_y][figure_x] == FigureNames.WHITEBISHOP && is_white_turn) {
            return moveBishop(isFigureBlack(gameBoard, mouse_y, mouse_x));
        }
        if (gameBoard[figure_y][figure_x] == FigureNames.BLACKBISHOP && !is_white_turn) {
            return moveBishop(isFigureWhite(gameBoard, mouse_y, mouse_x));
        }
        return false;
    }

    /**
     * Metoda, która sprawdzi, czy wykonany ruch jest legalny.
     * @param isFigureBlackForCapture czy figura, która znajduje się na tym polu może być przez nas zbita.
     * @return zwraca true, jeżeli ruch jest legalny, false, jeżeli nie jest.
     */
    private boolean moveBishop(boolean isFigureBlackForCapture) {
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
}
