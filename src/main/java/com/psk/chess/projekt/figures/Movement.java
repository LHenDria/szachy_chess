package com.psk.chess.projekt.figures;

/**
 * Klasa abstrakcyjna, która reprezentuje ruch figury na szachownicy, po której mają dziedzyczyć inne klasy, które reprezentują poruszanie się poszczególnych figur.
 */
public abstract class Movement {
    /**
     * Szachownica gry.
     */
    protected FigureNames[][] gameBoard;
    /**
     * Pozycja figury na planszy na osi Y.
     */
    protected int figure_y;
    /**
     * Pozycja figury na planszy na osi X.
     */
    protected int figure_x;
    /**
     * Pozycja na osi Y, na którą ma się ruszyć figura.
     */
    protected int mouse_y;
    /**
     * Pozycja na osi X, na którą ma się ruszyć figura.
     */
    protected int mouse_x;
    /**
     * Czy jest to tura białego.
     */
    protected boolean is_white_turn;

    /**
     * Konstuktor abstrakcyjnej klasy Movement. Tylko przypisuje wartości do pól.
     * @param gameBoard szachownica gry.
     * @param figure_y pozycja figury na osi Y.
     * @param figure_x pozycja figury na osi X.
     * @param mouse_y poruszenie się figury na osi Y.
     * @param mouse_x poruszenie się figury na osi X.
     * @param is_white_turn czy jest to tura białego.
     */
    Movement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn) {
        this.gameBoard = gameBoard;
        this.figure_y = figure_y;
        this.figure_x = figure_x;
        this.mouse_y = mouse_y;
        this.mouse_x = mouse_x;
        this.is_white_turn = is_white_turn;
    }

    /**
     * Asbstrakcyja metoda isMoveLegal(). Ma za zadanie sprawdzić, czy wykonywany ruch jest legalny.
     * @return zwraca true, jeżeli ruch jest legalny, zwraca false jeżeli nie jest.
     */
    public abstract boolean isMoveLegal();

    /**
     * Metoda sprawdza, czy figura jest czarna na tym polu.
     * @param gameBoard szachownica gry.
     * @param figure_y pozycja figury na osi Y.
     * @param figure_x pozycja figury na osi X.
     * @return zwraca true, jeżeli figura jest czarna, zwraca false, jeżeli nie jest.
     */
    public boolean isFigureBlack(FigureNames[][] gameBoard, int figure_y, int figure_x) {
        FigureNames figure = gameBoard[figure_y][figure_x];
        return switch (figure) {
            case BLACKKING, BLACKQUEEN, BLACKROOK, BLACKBISHOP, BLACKHORSEY, BLACKPAWN -> true;
            default -> false;
        };
    }

    /**
     * Metoda sprawdza, czy figura jest biała na tym polu.
     * @param gameBoard szachownica gry.
     * @param figure_y pozycja figury na osi Y.
     * @param figure_x pozycja figury na osi X.
     * @return zwraca true, jeżeli figura jest biała, zwraca false, jeżeli nie jest.
     */
    public boolean isFigureWhite(FigureNames[][] gameBoard, int figure_y, int figure_x) {
        FigureNames figure = gameBoard[figure_y][figure_x];
        return switch (figure) {
            case WHITEKING, WHITEQUEEN, WHITEROOK, WHITEBISHOP, WHITEHORSEY, WHITEPAWN -> true;
            default -> false;
        };
    }
}
