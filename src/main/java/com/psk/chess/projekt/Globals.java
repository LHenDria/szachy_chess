package com.psk.chess.projekt;

import com.psk.chess.projekt.api.Member;
import com.psk.chess.projekt.figures.EnPassantStates;
import com.psk.chess.projekt.menus.Menus;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Klasa zawierająca zmienne globalne.
 */
public class Globals {
    /**
     * Zmienna, która mówi nam, czy gra została wygrana przez białego.
     */
    static public boolean gameWonByWhite = false;
    /**
     * Zmienna, która mówi nam, czy gra została wygrana przez czarnego.
     */
    static public boolean gameWonByBlack = false;
    /**
     * Zmienna, która mówi nam, czy gra zakończyła się patem.
     */
    static public boolean gameWonByStalemate = false;
    /**
     * Zmienna, która mówi nam, czy powinien zostać wyświetlony napis z tym, jak zakończyła się gra.
     */
    static public boolean showEndGameText = false;
    /**
     * Zmienna, która mówi, w którym menu się znajdujemy.
     */
    static public Menus menu = Menus.MAIN_MENU;

    /**
     * Zmienna, która mówi, ile turn mineło.
     */
    public static int number_of_turns = 0;
    /**
     * Zmienna, która mówi, czy teraz jest ruch białego.
     */
    public static boolean is_white_turn = true;
    /**
     * Tablica, która zawiera informacje o tym, które białe pionki mogą wykonać en passant.
     */
    public static EnPassantStates[] whiteEnPassantStates = new EnPassantStates[8];
    /**
     * Tablica, która zawiera informacje o tym, które czarne pionki mogą wykonać en passant.
     */
    public static EnPassantStates[] blackEnPassantStates = new EnPassantStates[8];

    /**
     * IP serwera.
     */
    public static String hostname = "localhost";
    /**
     * Port serwera.
     */
    public static int port = 2137;
    /**
     * Zmienna, która mówi, czy jesteśmy w online, czy offline.
     */
    public static Member member = Member.OFFLINE;

    /**
     * Serwer gry.
     */
    public static ServerSide server = new ServerSide(new InetSocketAddress(hostname, port));
    /**
     * Klient gry.
     */
    public static ClientSide client;

    static {
        try {
            client = new ClientSide(new URI("ws://" + hostname + ":" + port));
        } catch (URISyntaxException e) {
            System.err.println("Error when creating client. Globals.java");
        }
    }
}
