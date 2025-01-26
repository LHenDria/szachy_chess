package com.psk.chess.projekt.unittests;

import com.psk.chess.projekt.figures.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Klasa, która zawiera testy jednostkowe, które mają sprawdzić poruszanie się figur.
 */
public class MovementTest {
    /**
     * Test jednostkowy, który sprawdza, czy ruch czarnego skoczka jest legalny.
     */
    @Test
    public void testBlackHorsey() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.BLACKHORSEY;
        Movement movement = new HorseyMovement(gameBoard, 4, 4, 2, 3, false);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch czarnej wieży jest legalny.
     */
    @Test
    public void testBlackRook() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.BLACKROOK;
        Movement movement = new RookMovement(gameBoard, 4, 4, 6, 4, false);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch czarnego gońca jest legalny.
     */
    @Test
    public void testBlackBishop() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[3][3] = FigureNames.BLACKBISHOP;
        Movement movement = new BishopMovement(gameBoard, 3, 3, 4, 4, false);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch czarnego pionka jest legalny.
     */
    @Test
    public void testBlackPawn() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.BLACKPAWN;
        Movement movement = new PawnMovement(gameBoard, 4, 4, 5, 4, false, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch czarnego króla jest legalny.
     */
    @Test
    public void testBlackKing() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        boolean[][] dangerFields = new boolean[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(dangerFields[i], false);
        }
        gameBoard[4][4] = FigureNames.BLACKKING;
        Movement movement = new KingMovement(gameBoard, 4, 4, 5, 5, false, dangerFields, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch czarnej królówki jest legalny.
     */
    @Test
    public void testBlackQueen() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.BLACKQUEEN;
        Movement movement = new QueenMovement(gameBoard, 4, 4, 2, 2, false);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch białej królówki jest legalny.
     */
    @Test
    public void testWhiteQueen() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.WHITEQUEEN;
        Movement movement = new QueenMovement(gameBoard, 4, 4, 2, 2, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch białego skoczka jest legalny.
     */
    @Test
    public void testWhiteHorsey() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.WHITEHORSEY;
        Movement movement = new HorseyMovement(gameBoard, 4, 4, 2, 3, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch białej wieży jest legalny.
     */
    @Test
    public void testWhiteRook() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.WHITEROOK;
        Movement movement = new RookMovement(gameBoard, 4, 4, 6, 4, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch białego gońca jest legalny.
     */
    @Test
    public void testWhiteBishop() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[3][3] = FigureNames.WHITEBISHOP;
        Movement movement = new BishopMovement(gameBoard, 3, 3, 4, 4, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch białego pionka jest legalny.
     */
    @Test
    public void testWhitePawn() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.WHITEPAWN;
        Movement movement = new PawnMovement(gameBoard, 4, 4, 3, 4, true, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy ruch białego króla jest legalny.
     */
    @Test
    public void testWhiteKing() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        boolean[][] dangerFields = new boolean[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(dangerFields[i], false);
        }
        gameBoard[4][4] = FigureNames.WHITEKING;
        Movement movement = new KingMovement(gameBoard, 4, 4, 5, 5, true, dangerFields, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy biała wieża może zbić czarnego pionka.
     */
    @Test
    public void testWhiteRookCapture() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.WHITEROOK;
        gameBoard[6][4] = FigureNames.BLACKPAWN;
        Movement movement = new RookMovement(gameBoard, 4, 4, 6, 4, true);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy czarna wieża może zbić białego pionka.
     */
    @Test
    public void testBlackRookCapture() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.BLACKROOK;
        gameBoard[6][4] = FigureNames.WHITEPAWN;
        Movement movement = new RookMovement(gameBoard, 4, 4, 6, 4, false);
        assertTrue(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy biała wieża nie może zbić białego pionka.
     */
    @Test
    public void testWhiteRookIllegalCapture() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.WHITEROOK;
        gameBoard[6][4] = FigureNames.WHITEPAWN;
        Movement movement = new RookMovement(gameBoard, 4, 4, 6, 4, true);
        assertFalse(movement.isMoveLegal());
    }
    /**
     * Test jednostkowy, który sprawdza, czy czarna wieża nie może zbić czarnego pionka.
     */
    @Test
    public void testBlackRookIllegalCapture() {
        FigureNames[][] gameBoard = new FigureNames[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], FigureNames.EMPTY);
        }
        gameBoard[4][4] = FigureNames.BLACKROOK;
        gameBoard[6][4] = FigureNames.BLACKPAWN;
        Movement movement = new RookMovement(gameBoard, 4, 4, 6, 4, false);
        assertFalse(movement.isMoveLegal());
    }
}
