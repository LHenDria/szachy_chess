package com.psk.chess.projekt.api;

/**
 * Enumerator który zawiera stany gry.
 */
public enum Member {
    /**
     * Program jest teraz serwerem.
     */
    SERVER,
    /**
     * Program jest teraz klientem.
     */
    CLIENT,
    /**
     * Program jest teraz w trybie offline.
     */
    OFFLINE
}
