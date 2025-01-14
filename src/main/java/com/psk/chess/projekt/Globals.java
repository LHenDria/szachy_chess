package com.psk.chess.projekt;

import com.psk.chess.projekt.api.Member;
import com.psk.chess.projekt.figures.EnPassantStates;
import com.psk.chess.projekt.menus.Menus;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

public class Globals {
    static public boolean gameWonByWhite = false;
    static public boolean gameWonByBlack = false;
    static public boolean gameWonByStalemate = false;
    static public boolean showEndGameText = false;
    static public Menus menu = Menus.MAIN_MENU;

    public static int number_of_turns = 0;
    public static boolean is_white_turn = true;
    public static EnPassantStates[] whiteEnPassantStates = new EnPassantStates[8];
    public static EnPassantStates[] blackEnPassantStates = new EnPassantStates[8];

    public static String hostname = "localhost";
    public static int port = 2137;
    public static Member member = Member.OFFLINE;

    public static ServerSide server = new ServerSide(new InetSocketAddress(hostname, port));
    public static ClientSide client;

    static {
        try {
            client = new ClientSide(new URI("ws://" + hostname + ":" + port));
        } catch (URISyntaxException e) {
            System.err.println("Error when creating client. Globals.java");
        }
    }
}
