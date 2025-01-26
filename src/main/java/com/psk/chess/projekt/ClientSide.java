package com.psk.chess.projekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psk.chess.projekt.api.*;
import com.psk.chess.projekt.figures.FigureNames;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.psk.chess.projekt.Globals.member;

/**
 * Klasa, która ma się zajmować klientem.
 */
public class ClientSide extends WebSocketClient {
    /**
     * Czy klient dołączył do serwera.
     */
    private boolean didJoinRoom = false;
    /**
     * Wysłanie eventa, że gra się rozpoczeła.
     */
    private boolean sendFirstStartEvent = true;
    /**
     * Klasa ObjectMapper, która miała zostać wykorzystana do wysyłania wiadomości do serwera przez JSON.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Szachownica gry.
     */
    private FigureNames[][] gameBoard = new FigureNames[8][8];

    /**
     * Getter do szachownicy gry.
     * @return zwraca szachownicę gry.
     */
    public FigureNames[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * Getter do zmiennej czy klient dołączył do serwera.
     * @return zwraca czy klient dołączył do serwera.
     */
    public boolean getDidJoinRoom() {
        return didJoinRoom;
    }

    /**
     * Konstruktor klasy ClientSize. Tylko przypisuje wartości do pól.
     * @param serverUri URL serwera.
     */
    public ClientSide(URI serverUri) {
        super(serverUri);
    }

    /**
     * Metoda, która jest wywołuwana, kiedy klient dołączy do serwera.
     * @param handshakedata handshakedata.
     */
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Client joined.");
        didJoinRoom = true;
        member = Member.CLIENT;
        sendStartGameEvent();
    }

    /**
     * Metoda, która jest wywoływana, kiedy klient przyjmie wiadomość od serwera.
     * @param message wiadomość od serwera.
     */
    @Override
    public void onMessage(String message) {
        requestGameBoardEvent();
        setGameBoardEvent(message);
    }

    /**
     * Metoda, która miała przyjować szachownicę gry.
     * @param message wiadomość od serwera.
     */
    private void setGameBoardEvent(String message) {
        try {
            Event eventType = objectMapper.readValue(message, Event.class);
            if (eventType instanceof GameBoardEvent gameBoardEvent) {
                this.gameBoard = gameBoardEvent.gameBoard;
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda, która miała zarządzać od serwera wysłania szachownicy gry.
     */
    private void requestGameBoardEvent() {
        try {
            GameBoardEvent event = new GameBoardEvent();
            event.type = EventType.GAMEBOARD;
            event.gameBoard = null;
            String json = objectMapper.writeValueAsString(event);
            send(json);
            sendFirstStartEvent = false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda, która miała wysłać do serwera event o rozpoczęciu gry.
     */
    private void sendStartGameEvent() {
        if (sendFirstStartEvent) {
            try {
                Event event = new StartGameEvent();
                event.type = EventType.GAMESTART;
                String json = objectMapper.writeValueAsString(event);
                System.out.println(json);
                send(json);
                sendFirstStartEvent = false;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Metoda, która jest wywoływana, kiedy klient jest zamykany.
     * @param code kod.
     * @param reason powód.
     * @param remote remote.
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Client closed.");
        didJoinRoom = false;
    }

    /**
     * Metoda, która jest wywoływana, kiedy klient przyjmie wiadomość od serwera.
     * @param message wiadmość od serwera.
     */
    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    /**
     * Metoda, która jest wywoływana podczas wystąpienia błędu.
     * @param ex wyjątek.
     */
    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }
}
