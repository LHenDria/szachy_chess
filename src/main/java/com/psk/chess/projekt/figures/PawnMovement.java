package com.psk.chess.projekt.figures;

import java.util.Arrays;

import static com.psk.chess.projekt.Globals.whiteEnPassantStates;
import static com.psk.chess.projekt.Globals.blackEnPassantStates;

/**
 * Klasa, która reprezentuje poruszanie się pionka. Dziedziczy po klasie Movement.
 */
public class PawnMovement extends Movement {
    /**
     * Sprawdzenie, czy pionek może zostać usunięty podczas en passant.
     */
    private boolean removeWhenEnPassant = true;

    /**
     * Konstruktor klasy PawnMovement. Tylko przypisuje wartości do pól.
     * @param gameBoard szachownica gry.
     * @param figure_y pozycja figury na osi Y.
     * @param figure_x pozycja figury na osi X.
     * @param mouse_y pozycja na osi Y, na którą figura ma się ruszyć.
     * @param mouse_x pozycja na osi X, na którą figura ma się ruszyć.
     * @param is_white_turn czy jest to ruch białego.
     * @param removeWhenEnPassant czy może zostać usunięty podczas en passant.
     */
    public PawnMovement(FigureNames[][] gameBoard, int figure_y, int figure_x, int mouse_y, int mouse_x, boolean is_white_turn, boolean removeWhenEnPassant) {
        super(gameBoard, figure_y, figure_x, mouse_y, mouse_x, is_white_turn);
        this.removeWhenEnPassant = removeWhenEnPassant;
    }

    /**
     * Metoda, która sprawdzi, czy wykonywany ruch jest legalny. Będzie modyfikować szachownicę gry podczas en passant do usuwania przeciwnych pionków.
     * @return zwraca true, jeżeli ruch jest legalny, false, jeżeli nie jest.
     */
    @Override
    public boolean isMoveLegal() {
        if(removeWhenEnPassant) {
            changeEnPassantEnumsStatus();
        }
        if (is_white_turn) {
            // Poruszenie się o jedno lub dwa pola.
            if (figure_y == 6) {
                if ((mouse_y == figure_y - 2 || mouse_y == figure_y - 1) && mouse_x == figure_x && gameBoard[figure_y - 1][figure_x] == FigureNames.EMPTY && gameBoard[figure_y - 2][figure_x] == FigureNames.EMPTY) {
                    return true;
                }
            }
            // Zbijanie figur.
            if(mouse_y == figure_y - 1) {
                if(figure_x <= 6 && mouse_x == figure_x + 1) {
                    if(isFigureBlack(gameBoard, figure_y - 1, figure_x + 1)) {
                        return true;
                    }
                }
                if(figure_x >= 1 && mouse_x == figure_x - 1) {
                    if(isFigureBlack(gameBoard, figure_y - 1, figure_x - 1)) {
                        return true;
                    }
                }
            }

            // Ten bażant.
            if (figure_y == 3) {
                if(figure_x >= 1 && mouse_x == figure_x - 1 && mouse_y == figure_y - 1) {
                    if(isFigureBlack(gameBoard, figure_y, figure_x - 1) && whiteEnPassantStates[figure_x - 1] == EnPassantStates.ELIGABLE) {
                        if(removeWhenEnPassant) {
                            gameBoard[figure_y][figure_x - 1] = FigureNames.EMPTY;
                        }
                        return true;
                    }
                }
                if(figure_x <= 6 && mouse_x == figure_x + 1 && mouse_y == figure_y - 1) {
                    if(isFigureBlack(gameBoard, figure_y, figure_x + 1) && whiteEnPassantStates[figure_x + 1] == EnPassantStates.ELIGABLE) {
                        if(removeWhenEnPassant) {
                            gameBoard[figure_y][figure_x + 1] = FigureNames.EMPTY;
                        }
                        return true;
                    }
                }
            }

            // Poruszanie się o jedno pole w przód.
            if (mouse_y == figure_y - 1 && mouse_x == figure_x && gameBoard[figure_y - 1][figure_x] == FigureNames.EMPTY) {
                return true;
            } else {
                return false;
            }
        } else {
            // Poruszanie się o dwa lub jedno pole.
            if (figure_y == 1) {
                if ((mouse_y == figure_y + 2 || mouse_y == figure_y + 1) && mouse_x == figure_x && gameBoard[figure_y + 1][figure_x] == FigureNames.EMPTY && gameBoard[figure_y + 2][figure_x] == FigureNames.EMPTY) {
                    return true;
                }
            }

            // Zbijanie figur.
            if(mouse_y == figure_y + 1) {
                if(figure_x <= 6 && mouse_x == figure_x + 1) {
                    if(isFigureWhite(gameBoard, figure_y + 1, figure_x + 1)) {
                        return true;
                    }
                }
                if(figure_x >= 1 && mouse_x == figure_x - 1) {
                    if(isFigureWhite(gameBoard, figure_y + 1, figure_x - 1)) {
                        return true;
                    }
                }
            }

            // Ten bażant.
            if (figure_y == 4) {
                if(figure_x >= 1 && mouse_x == figure_x - 1 && mouse_y == figure_y + 1) {
                    if(isFigureWhite(gameBoard, figure_y, figure_x - 1) && blackEnPassantStates[figure_x - 1] == EnPassantStates.ELIGABLE) {
                        if(removeWhenEnPassant) {
                            gameBoard[figure_y][figure_x - 1] = FigureNames.EMPTY;
                        }
                        return true;
                    }
                }
                if(figure_x <= 6 && mouse_x == figure_x + 1 && mouse_y == figure_y + 1) {
                    if(isFigureWhite(gameBoard, figure_y, figure_x + 1) && blackEnPassantStates[figure_x + 1] == EnPassantStates.ELIGABLE) {
                        if(removeWhenEnPassant) {
                            gameBoard[figure_y][figure_x + 1] = FigureNames.EMPTY;
                        }
                        return true;
                    }
                }
            }

            // Poruszanie się o jedno pole w przód.
            if (mouse_y == figure_y + 1 && mouse_x == figure_x && gameBoard[figure_y + 1][figure_x] == FigureNames.EMPTY) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Metoda, która odpowiada za sprawdzanie i zmianę, które pionki już nie mogą wykonać en passant.
     */
    private void changeEnPassantEnumsStatus() {
        for (int i = 0; i < 8; i++) {
            if(gameBoard[3][i] == FigureNames.BLACKPAWN) {
                if(whiteEnPassantStates[i] == EnPassantStates.ELIGABLE) {
                    whiteEnPassantStates[i] = EnPassantStates.NOLONGERELIGABLE;
                }
                if(whiteEnPassantStates[i] == EnPassantStates.NOTELIGABLE) {
                    whiteEnPassantStates[i] = EnPassantStates.ELIGABLE;
                }
            }
            if(gameBoard[4][i] == FigureNames.WHITEPAWN) {
                if(blackEnPassantStates[i] == EnPassantStates.ELIGABLE) {
                    blackEnPassantStates[i] = EnPassantStates.NOLONGERELIGABLE;
                }
                if(blackEnPassantStates[i] == EnPassantStates.NOTELIGABLE) {
                    blackEnPassantStates[i] = EnPassantStates.ELIGABLE;
                }
            }
        }
    }
}