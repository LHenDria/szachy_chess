package com.psk.chess.projekt.api;

import com.psk.chess.projekt.figures.FigureNames;

/**
 * Event który miał być wykonywany po otrzymwaniu szachownicy.
 */
public class GameBoardEvent extends Event {
    public FigureNames[][] gameBoard;
}
