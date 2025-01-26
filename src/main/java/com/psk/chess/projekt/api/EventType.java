package com.psk.chess.projekt.api;

/**
 * Enumerator który zawiera typy eventów.
 */
public enum EventType {
    /**
     * Event jest ruchem.
     */
    MOVE,
    /**
     * Event jest przesyłaniem planszy.
     */
    GAMEBOARD,
    /**
     * Event mówi o rozpoczęciu gry.
     */
    GAMESTART
}
