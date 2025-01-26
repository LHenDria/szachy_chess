package com.psk.chess.projekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psk.chess.projekt.api.*;
import com.psk.chess.projekt.figures.FigureNames;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.psk.chess.projekt.Globals.member;

/**
 * Klasa, która ma się zajmować serwerem.
 */
public class ServerSide extends WebSocketServer {
    /**
     * Czy klient dołączył do serwera.
     */
    private boolean didClientJoin = false;
    /**
     * Klasa ObjectMapper, która miała zostać wykorzystana do wysyłania wiadomości do serwera przez JSON.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Szachownica gry.
     */
    private FigureNames[][] gameBoard;

    /**
     * Setter dla szachownicy gry.
     * @param gameBoard szachownica gry.
     */
    public void setGameBoard(FigureNames[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Konstruktor klasy ServerSide. Tylko przypisuje wartości do pól.
     * @param address adres serwera.
     */
    public ServerSide(InetSocketAddress address) {
        super(address);
    }

    /**
     * Metoda, która jest wywoływana, kiedy klient dołączy do serwera.
     * @param conn połączenie.
     * @param handshake handshake.
     */
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {

    }

    /**
     * Metoda, która jest wywoływana, kiedy serwer przyjmie wiadomość od klienta.
     * @param conn połączenie.
     * @param message wiadomość.
     */
    @Override
    public void onMessage(WebSocket conn, String message) {
        if (!didClientJoin) {
            System.out.println("Received message in server: " + message);
            //conn.send("Joined to server.");
            didClientJoin = true;
            member = Member.SERVER;
        } else {
            try {
                Event eventType = objectMapper.readValue(message, Event.class);

                if (eventType instanceof MoveEvent moveEvent) {

                } else if (eventType instanceof GameBoardEvent gameBoardEvent) {
                    sendGameBoardEvent(conn);
                } else if (eventType instanceof StartGameEvent startGameEvent) {
                    startGameEvent.startGame();
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Metoda, która miała za zadanie wysyłać do klienta szachownicę gry.
     * @param conn połączenie.
     */
    private void sendGameBoardEvent(WebSocket conn) {
        try {
            GameBoardEvent event = new GameBoardEvent();
            event.type = EventType.GAMEBOARD;
            event.gameBoard = this.gameBoard;
            System.out.println(Arrays.deepToString(event.gameBoard));
            System.out.println(Arrays.deepToString(this.gameBoard));
            String json = objectMapper.writeValueAsString(event);
            System.out.println("json" + json);
            conn.send(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda, która jest wywoływana, kiedy serwer jest zamykany.
     * @param conn połączenie.
     * @param code kod.
     * @param reason powód.
     * @param remote remote.
     */
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Server closed.");
    }

    /**
     * Metoda, która jest wywoływana, kiedy serwer przyjmie wiadomość od klienta.
     * @param conn połączenie.
     * @param message wiadomość.
     */
    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println("received ByteBuffer from " + conn.getRemoteSocketAddress());
    }

    /**
     * Metoda, która jest wywoływana podczas wystąpienia błędu.
     * @param conn połączenie.
     * @param ex wyjątek.
     */
    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress() + ":" + ex);
    }

    /**
     * Metoda, która jest wywoływana przy starcie serwera.
     */
    @Override
    public void onStart() {
        System.out.println("Server started.");
    }
}
