package com.psk.chess.projekt.figures;

/**
 * Klasa która reprezentuje poruszanie się skoczka. Dziedziczy po klasie Movement.
 */
public class HorseyMovement extends Movement {
    /**
     * Konstruktor klasy HorseyMovement. Tylko przypisuje wartości do pól.
     * @param gameBoard szachownica gry.
     * @param figure_y pozycja figury na osi Y.
     * @param figure_x pozycja figury na osi X.
     * @param mouse_y pozycja na osi Y na którą figura ma się ruszyć.
     * @param mouse_x pozycja na osi X na którą figura ma się ruszyć.
     * @param is_white_turn czy jest to ruch białego.
     */
    public HorseyMovement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn) {
        super(gameBoard, figure_y, figure_x, mouse_y, mouse_x, is_white_turn);
    }

    /**
     * Metoda wywoła odpowiednie metody do sprawdzenia czy ruch który chcemy wykonać jest legalny.
     * @return zwraca true jeżeli ruch jest legalny, false jeżeli nie jest.
     */
    @Override
    public boolean isMoveLegal() {
        if(gameBoard[figure_y][figure_x] == FigureNames.WHITEHORSEY && is_white_turn) {
            return moveHorsey(isFigureBlack(gameBoard, mouse_y, mouse_x));
        }
        if (gameBoard[figure_y][figure_x] == FigureNames.BLACKHORSEY && !is_white_turn) {
            return moveHorsey(!isFigureBlack(gameBoard, mouse_y, mouse_x));
        }
        return false;
    }

    /**
     * Metoda która sprawdzi czy wykonany ruch jest legalny.
     * @param isFigureBlackForCapture czy figura któa znajduje się na tym polu może być przez nas zbita.
     * @return zwraca true jeżeli ruch jest legalny, false jeżeli nie jest.
     */
    private boolean moveHorsey(boolean isFigureBlackForCapture) {
        if(figure_y - 1 >= 0 && figure_x - 2 >= 0) {
            if(mouse_y == figure_y - 1 && mouse_x == figure_x - 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y - 1 >= 0 && figure_x + 2 <= 7) {
            if(mouse_y == figure_y - 1 && mouse_x == figure_x + 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 1 <= 7 && figure_x - 2 >= 0) {
            if(mouse_y == figure_y + 1 && mouse_x == figure_x - 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 1 <= 7 && figure_x + 2 <= 7) {
            if(mouse_y == figure_y + 1 && mouse_x == figure_x + 2 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y - 2 >= 0 && figure_x - 1 >= 0) {
            if(mouse_y == figure_y - 2 && mouse_x == figure_x - 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y - 2 >= 0 && figure_x + 1 <= 7) {
            if(mouse_y == figure_y - 2 && mouse_x == figure_x + 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 2 <= 7 && figure_x - 1 >= 0) {
            if(mouse_y == figure_y + 2 && mouse_x == figure_x - 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        if(figure_y + 2 <= 7 && figure_x + 1 <= 7) {
            if(mouse_y == figure_y + 2 && mouse_x == figure_x + 1 && (isFigureBlackForCapture || gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY)) {
                return true;
            }
        }
        return false;
    }
}
