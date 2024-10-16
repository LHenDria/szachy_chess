package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import javafx.scene.Group;

import static com.psk.chess.projekt.ChessBoard.createChessBoard;

public class Game {
    private static void gameBoardSetToBasic(FigureNames[][] gameBoard) {
        gameBoard[0][0] = FigureNames.BLACKROOK;
        gameBoard[0][1] = FigureNames.BLACKHORSEY;
        gameBoard[0][2] = FigureNames.BLACKBISHOP;
        gameBoard[0][3] = FigureNames.BLACKQUEEN;
        gameBoard[0][4] = FigureNames.BLACKKING;
        gameBoard[0][5] = FigureNames.BLACKBISHOP;
        gameBoard[0][6] = FigureNames.BLACKHORSEY;
        gameBoard[0][7] = FigureNames.BLACKROOK;

        gameBoard[7][0] = FigureNames.WHITEROOK;
        gameBoard[7][1] = FigureNames.WHITEHORSEY;
        gameBoard[7][2] = FigureNames.WHITEBISHOP;
        gameBoard[7][3] = FigureNames.WHITEQUEEN;
        gameBoard[7][4] = FigureNames.WHITEKING;
        gameBoard[7][5] = FigureNames.WHITEBISHOP;
        gameBoard[7][6] = FigureNames.WHITEHORSEY;
        gameBoard[7][7] = FigureNames.WHITEROOK;

        for (int i = 0; i < 8; i++) {
            gameBoard[1][i] = FigureNames.BLACKPAWN;
            gameBoard[6][i] = FigureNames.WHITEPAWN;
        }
    }
    public static void gameInnit(Group root) {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        gameBoardSetToBasic(gameBoard);
        createChessBoard(root);
        DrawFigures.drawFigures(gameBoard, root);
    }
}
