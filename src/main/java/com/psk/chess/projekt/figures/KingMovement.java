package com.psk.chess.projekt.figures;

import com.psk.chess.projekt.SimpleShapes;
import javafx.scene.paint.Color;

/**
 * Klasa, która reprezentuje poruszanie się króla. Dziedziczy po klasie Movement.
 */
public class KingMovement extends Movement {
    /**
     * Macierz niebezpiecznych pól dla króla.
     */
    private boolean[][] dangerFields;
    /**
     * Czy król może wykonać roszadę.
     */
    private boolean moveFiguresWhenCastling;

    /**
     * Konstruktor klasy KingMovement. Tylko przypisuje wartości do pól.
     * @param gameBoard szachownica gry.
     * @param figure_y pozycja figury na osi Y.
     * @param figure_x pozycja figury na osi X.
     * @param mouse_y pozycja na osi Y, na którą figura ma się ruszyć.
     * @param mouse_x pozycja na osi X, na którą figura ma się ruszyć.
     * @param is_white_turn czy jest to ruch białego.
     */
    public KingMovement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn, boolean[][] dangerFields, boolean moveFiguresWhenCastling) {
        super(gameBoard, figure_y, figure_x, mouse_y, mouse_x, is_white_turn);
        this.dangerFields = dangerFields;
        this.moveFiguresWhenCastling = moveFiguresWhenCastling;
    }

    /**
     * Metoda wywoła odpowiednie metody do sprawdzenia, czy ruch, który chcemy wykonać jest legalny.
     * @return zwraca true, jeżeli ruch jest legalny, false, jeżeli nie jest.
     */
    @Override
    public boolean isMoveLegal() {
        if (gameBoard[figure_y][figure_x] == FigureNames.WHITEKING && is_white_turn) {
            return moveKing(isFigureBlack(gameBoard, mouse_y, mouse_x));
        }
        if (gameBoard[figure_y][figure_x] == FigureNames.BLACKKING && !is_white_turn) {
            return moveKing(!isFigureBlack(gameBoard, mouse_y, mouse_x));
        }
        return false;
    }

    /**
     * Metoda, która sprawdzi, czy wykonany ruch jest legalny.
     * @param isFigureBlackForCapture czy figura, która znajduje się na tym polu może być przez nas zbita.
     * @return zwraca true, jeżeli ruch jest legalny, false, jeżeli nie jest.
     */
    private boolean moveKing(boolean isFigureBlackForCapture) {
        if (gameBoard[figure_y][figure_x] == FigureNames.WHITEKING && gameBoard[7][4] == FigureNames.WHITEKING) {
            if (mouse_y == 7 && mouse_x == 6 && gameBoard[7][7] == FigureNames.WHITEROOK && gameBoard[7][6] == FigureNames.EMPTY && gameBoard[7][5] == FigureNames.EMPTY && !dangerFields[7][6] && !dangerFields[7][5]) {
                if (moveFiguresWhenCastling) {
                    gameBoard[7][7] = FigureNames.EMPTY;
                    gameBoard[7][5] = FigureNames.WHITEROOK;
                    return true;
                }
            }
            if (mouse_y == 7 && mouse_x == 2 && gameBoard[7][0] == FigureNames.WHITEROOK && gameBoard[7][1] == FigureNames.EMPTY && gameBoard[7][2] == FigureNames.EMPTY && gameBoard[7][3] == FigureNames.EMPTY && !dangerFields[7][1] && !dangerFields[7][2] && !dangerFields[7][3]) {
                if (moveFiguresWhenCastling) {
                    gameBoard[7][0] = FigureNames.EMPTY;
                    gameBoard[7][3] = FigureNames.WHITEROOK;
                    return true;
                }
            }
        }
        if (gameBoard[figure_y][figure_x] == FigureNames.BLACKKING && gameBoard[0][4] == FigureNames.BLACKKING) {
            if (mouse_y == 0 && mouse_x == 6 && gameBoard[0][0] == FigureNames.BLACKROOK && gameBoard[0][6] == FigureNames.EMPTY && gameBoard[0][5] == FigureNames.EMPTY && !dangerFields[0][6] && !dangerFields[0][5]) {
                if (moveFiguresWhenCastling) {
                    gameBoard[0][7] = FigureNames.EMPTY;
                    gameBoard[0][5] = FigureNames.BLACKROOK;
                    return true;
                }
            }
            if (mouse_y == 0 && mouse_x == 2 && gameBoard[0][0] == FigureNames.BLACKROOK && gameBoard[0][1] == FigureNames.EMPTY && gameBoard[0][2] == FigureNames.EMPTY && gameBoard[0][3] == FigureNames.EMPTY && !dangerFields[0][1] && !dangerFields[0][2] && !dangerFields[0][3]) {
                if (moveFiguresWhenCastling) {
                    gameBoard[0][0] = FigureNames.EMPTY;
                    gameBoard[0][3] = FigureNames.BLACKROOK;
                    return true;
                }
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (mouse_x == figure_x + i && mouse_y == figure_y + j) {
/*
                    if(moveFiguresWhenCastling) {
                        System.out.println(gameBoard[figure_y][figure_x] + ":");
                        for (int xi = 0; xi < dangerFields.length; xi++) {
                            for (int xj = 0; xj < dangerFields[xi].length; xj++) {
                                System.out.print(dangerFields[xi][xj] ? "🟥" : "🟩");
                            }
                            System.out.println();
                        }
                    }
 */
                    if (mouse_x == figure_x && mouse_y == figure_y) {
                        return false;
                    }
                    if (gameBoard[mouse_y][mouse_x] == FigureNames.EMPTY && !this.dangerFields[mouse_y][mouse_x]) {
                        return true;
                    }
                    if (gameBoard[figure_y][figure_x] == FigureNames.WHITEKING) {
                        if (isFigureBlackForCapture && !this.dangerFields[mouse_y][mouse_x]) {
                            return true;
                        }
                    }
                    if (gameBoard[figure_y][figure_x] == FigureNames.BLACKKING) {
                        if (isFigureBlackForCapture && !this.dangerFields[mouse_y][mouse_x]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
