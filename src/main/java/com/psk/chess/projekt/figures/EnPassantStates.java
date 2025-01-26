package com.psk.chess.projekt.figures;

/**
 * Enumerator który który zawiera informację o tym czy może zostać wykonany en passant.
 */
public enum EnPassantStates {
    /**
     * Pionek nie może wykonać teraz en passant ale będzie mógł w przyszłości.
     */
    NOTELIGABLE,
    /**
     * Pionek może wykonać en passant.
     */
    ELIGABLE,
    /**
     * Pionek nie będzie juz mógł wykonać en passant.
     */
    NOLONGERELIGABLE
}
