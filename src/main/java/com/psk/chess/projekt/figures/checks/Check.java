package com.psk.chess.projekt.figures.checks;

import com.psk.chess.projekt.figures.FigureNames;

/**
 * Klasa która ma za zadanie wypełnić macierz niebezpiecznymi pola dla króla.
 */
public class Check {
    /**
     * Macierz która ma zawiera w sobie pola na które król nie może stanąć ponieważ będzie w szachu.
     */
    private boolean[][] dangerFields;

    /**
     * Konstruktor klasy Check. Tylko przypisuje wartości.
     * @param dangerFields macierz niebezpiecznych pól.
     */
    public Check(boolean[][] dangerFields) {
        this.dangerFields = dangerFields;
    }

    /**
     * Metoda wypełnia macierz dangerFields niebezpiecznymi polami które są generowane przez wieżę.
     * @param i pozycja na osi X wieży.
     * @param j pozycja na osi Y wieży.
     * @param gameBoard szachownica gry
     * @param dangerFields macierz z niebezpiecznymi polami.
     * @param ourPiece kolor figury która generuje niebezpieczne pola.
     * @param enemyKing jaki kolor ma przeciwny król.
     */
    private void rookFillDangerFields(int i, int j, FigureNames[][] gameBoard, boolean dangerFields[][], FigureNames ourPiece , FigureNames enemyKing) {
        boolean blocking1 = true;
        boolean blocking2 = true;
        boolean blocking3 = true;
        boolean blocking4 = true;
        for (int k = 1; k < 8; k++) {
            if (i + k <= 7) {
                if (gameBoard[j][i + k] == FigureNames.EMPTY && blocking1) {
                    dangerFields[j][i + k] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j][i + k] == enemyKing && blocking1) {
                    dangerFields[j][i + k] = true;
                } else if (blocking1) {
                    dangerFields[j][i + k] = true;
                    blocking1 = false;
                }
            }
            if (i - k >= 0) {
                if (gameBoard[j][i - k] == FigureNames.EMPTY && blocking2) {
                    dangerFields[j][i - k] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j][i - k] == enemyKing && blocking2) {
                    dangerFields[j][i - k] = true;
                } else if (blocking2) {
                    dangerFields[j][i - k] = true;
                    blocking2 = false;
                }
            }
            if (j + k <= 7) {
                if (gameBoard[j + k][i] == FigureNames.EMPTY && blocking3) {
                    dangerFields[j + k][i] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j + k][i] == enemyKing && blocking3) {
                    dangerFields[j + k][i] = true;
                } else if (blocking3) {
                    dangerFields[j + k][i] = true;
                    blocking3 = false;
                }
            }
            if (j - k >= 0) {
                if (gameBoard[j - k][i] == FigureNames.EMPTY && blocking4) {
                    dangerFields[j - k][i] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j - k][i] == enemyKing && blocking4) {
                    dangerFields[j - k][i] = true;
                } else if (blocking4) {
                    dangerFields[j - k][i] = true;
                    blocking4 = false;
                }
            }
        }
    }

    /**
     * Metoda wypełnia macierz dangerFields niebezpiecznymi polami które są generowane przez gońca.
     * @param i pozycja na osi X gońca.
     * @param j pozycja na osi Y gońca.
     * @param gameBoard szachownica gry
     * @param dangerFields macierz z niebezpiecznymi polami.
     * @param ourPiece kolor figury która generuje niebezpieczne pola.
     * @param enemyKing jaki kolor ma przeciwny król.
     */
    private void bishopFillDangerFields(int i, int j, FigureNames[][] gameBoard, boolean dangerFields[][], FigureNames ourPiece , FigureNames enemyKing) {
        boolean blocking1 = true;
        boolean blocking2 = true;
        boolean blocking3 = true;
        boolean blocking4 = true;
        for (int k = 1; k < 8; k++) {
            if (j + k <= 7 && i + k <= 7) {
                if (gameBoard[j + k][i + k] == FigureNames.EMPTY && blocking1) {
                    dangerFields[j + k][i + k] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j + k][i + k] == enemyKing && blocking1) {
                    dangerFields[j + k][i + k] = true;
                } else if (blocking1) {
                    dangerFields[j + k][i + k] = true;
                    blocking1 = false;
                }
            }
            if (j - k >= 0 && i + k <= 7) {
                if (gameBoard[j - k][i + k] == FigureNames.EMPTY && blocking2) {
                    dangerFields[j - k][i + k] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j - k][i + k] == enemyKing && blocking2) {
                    dangerFields[j - k][i + k] = true;
                }  else if (blocking2) {
                    dangerFields[j - k][i + k] = true;
                    blocking2 = false;
                }
            }
            if (j - k >= 0 && i - k >= 0) {
                if (gameBoard[j - k][i - k] == FigureNames.EMPTY && blocking3) {
                    dangerFields[j - k][i - k] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j - k][i - k] == enemyKing && blocking3) {
                    dangerFields[j - k][i - k] = true;
                }  else if (blocking3) {
                    dangerFields[j - k][i - k] = true;
                    blocking3 = false;
                }
            }
            if (j + k <= 7 && i - k >= 0) {
                if (gameBoard[j + k][i - k] == FigureNames.EMPTY && blocking4) {
                    dangerFields[j + k][i - k] = true;
                } else if (gameBoard[j][i] == ourPiece && gameBoard[j + k][i - k] == enemyKing && blocking4) {
                    dangerFields[j + k][i - k] = true;
                }  else if (blocking4) {
                    dangerFields[j + k][i - k] = true;
                    blocking4 = false;
                }
            }
        }
    }
    /**
     * Metoda wypełnia macierz dangerFields niebezpiecznymi polami które są generowane przez skoczka.
     * @param i pozycja na osi X skoczka.
     * @param j pozycja na osi Y skoczka.
     * @param dangerFields macierz z niebezpiecznymi polami.
     */
    private void horseyFillDangerFields(int i, int j, boolean dangerFields[][]) {
        if (j + 2 <= 7 && i - 1 >= 0) {
            dangerFields[j + 2][i - 1] = true;
        }
        if (j + 2 <= 7 && i + 1 <= 7) {
            dangerFields[j + 2][i + 1] = true;
        }
        if (j - 2 >= 0 && i - 1 >= 0) {
            dangerFields[j - 2][i - 1] = true;
        }
        if (j - 2 >= 0 && i + 1 <= 7) {
            dangerFields[j - 2][i + 1] = true;
        }
        if (j + 1 <= 7 && i + 2 <= 7) {
            dangerFields[j + 1][i + 2] = true;
        }
        if (j - 1 >= 0 && i + 2 <= 7) {
            dangerFields[j - 1][i + 2] = true;
        }
        if (j + 1 <= 7 && i - 2 > 0) {
            dangerFields[j + 1][i - 2] = true;
        }
        if (j - 1 >= 0 && i - 2 >= 0) {
            dangerFields[j - 1][i - 2] = true;
        }
    }

    /**
     * Metoda wypełnia macierz dangerFields niebezpiecznymi polami które są generowane przez króla.
     * @param i pozycja na osi X króla.
     * @param j pozycja na osi Y króla.
     * @param dangerFields macierz z niebezpiecznymi polami.
     */
    private void kingFillDangerFields(int i, int j, boolean dangerFields[][]) {
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (j + l >= 0 && j + l <= 7 && i + k >= 0 && i + k <= 7) {
                    dangerFields[j + l][i + k] = true;
                }
            }
        }
    }

    /**
     * Metoda wypełnia macierz dangerFields niebezpiecznymi polami które są generowane przez królówkę.
     * @param i pozycja na osi X królówki.
     * @param j pozycja na osi Y królówki.
     * @param dangerFields macierz z niebezpiecznymi polami.
     */
    private void queenFillDangerFields(int i, int j, FigureNames[][] gameBoard, boolean dangerFields[][], FigureNames ourPiece ,FigureNames enemyKing) {
        rookFillDangerFields(i, j, gameBoard, dangerFields, ourPiece , enemyKing);
        bishopFillDangerFields(i, j, gameBoard, dangerFields, ourPiece , enemyKing);
    }

    /**
     * Metoda która wypełni macierz dangerFields bezpiecznymi polami.
     * @param dangerFields macierz niebezpiecznych pól.
     */
    public void setDangerFields(boolean dangerFields[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                dangerFields[j][i] = false;
            }
        }
    }

    /**
     * Metoda wypełni macierz dangerFields dla białego króla niebezpiecznymi polami generowanymi przez wszystkie czarne figury.
     * @param gameBoard szachownica gry.
     */
    public void fillSafeFieldsWithNotSafeFieldsForWhiteKing(FigureNames[][] gameBoard) {
        setDangerFields(dangerFields);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[j][i] == FigureNames.BLACKPAWN) {
                    if (i - 1 > 0 && j + 1 <= 7) {
                        dangerFields[j + 1][i - 1] = true;
                    }
                    if (i + 1 < 7 && j + 1 <= 7) {
                        dangerFields[j + 1][i + 1] = true;
                    }
                }
                if (gameBoard[j][i] == FigureNames.BLACKBISHOP) {
                    bishopFillDangerFields(i, j, gameBoard, dangerFields, FigureNames.BLACKBISHOP, FigureNames.WHITEKING);
                }
                if (gameBoard[j][i] == FigureNames.BLACKROOK) {
                    rookFillDangerFields(i, j, gameBoard, dangerFields, FigureNames.BLACKROOK, FigureNames.WHITEKING);
                }
                if (gameBoard[j][i] == FigureNames.BLACKHORSEY) {
                    horseyFillDangerFields(i, j, dangerFields);
                }
                if (gameBoard[j][i] == FigureNames.BLACKKING) {
                    kingFillDangerFields(i, j, dangerFields);
                }
                if (gameBoard[j][i] == FigureNames.BLACKQUEEN) {
                    queenFillDangerFields(i, j, gameBoard, dangerFields, FigureNames.BLACKQUEEN, FigureNames.WHITEKING);
                }
            }
        }
    }

    /**
     * Metoda wypełni macierz dangerFields dla czarnego króla niebezpiecznymi polami generowanymi przez wszystkie białe figury.
     * @param gameBoard szachownica gry.
     */
    public void fillSafeFieldsWithNotSafeFieldsForBlackKing(FigureNames[][] gameBoard) {
        setDangerFields(dangerFields);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[j][i] == FigureNames.WHITEPAWN) {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        dangerFields[j - 1][i - 1] = true;
                    }
                    if (i + 1 <= 7 && j - 1 >= 0) {
                        dangerFields[j - 1][i + 1] = true;
                    }
                }
                if (gameBoard[j][i] == FigureNames.WHITEBISHOP) {
                    bishopFillDangerFields(i, j, gameBoard, dangerFields, FigureNames.WHITEBISHOP, FigureNames.BLACKKING);
                }
                if (gameBoard[j][i] == FigureNames.WHITEROOK) {
                    rookFillDangerFields(i, j, gameBoard, dangerFields, FigureNames.WHITEROOK, FigureNames.BLACKKING);
                }
                if (gameBoard[j][i] == FigureNames.WHITEHORSEY) {
                    horseyFillDangerFields(i, j, dangerFields);
                }
                if (gameBoard[j][i] == FigureNames.WHITEKING) {
                    kingFillDangerFields(i, j, dangerFields);
                }
                if (gameBoard[j][i] == FigureNames.WHITEQUEEN) {
                    queenFillDangerFields(i, j, gameBoard, dangerFields, FigureNames.WHITEQUEEN, FigureNames.BLACKKING);
                }
            }
        }
    }
}
