package com.psk.chess.projekt.figures.checks;

import com.psk.chess.projekt.figures.FigureNames;

public class Check {
    private boolean[][] dangerFields;

    public Check(boolean[][] dangerFields) {
        this.dangerFields = dangerFields;
    }

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

    private void kingFillDangerFields(int i, int j, boolean dangerFields[][]) {
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (j + l >= 0 && j + l <= 7 && i + k >= 0 && i + k <= 7) {
                    dangerFields[j + l][i + k] = true;
                }
            }
        }
    }

    private void queenFillDangerFields(int i, int j, FigureNames[][] gameBoard, boolean dangerFields[][], FigureNames ourPiece ,FigureNames enemyKing) {
        rookFillDangerFields(i, j, gameBoard, dangerFields, ourPiece , enemyKing);
        bishopFillDangerFields(i, j, gameBoard, dangerFields, ourPiece , enemyKing);
    }

    public void setDangerFields(boolean dangerFields[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                dangerFields[j][i] = false;
            }
        }
    }

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
