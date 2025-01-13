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

public class ClientSide extends WebSocketClient {
    private boolean didJoinRoom = false;
    private boolean sendFirstStartEvent = true;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private FigureNames[][] gameBoard = new FigureNames[8][8];

    public FigureNames[][] getGameBoard() {
        return gameBoard;
    }

    public boolean getDidJoinRoom() {
        return didJoinRoom;
    }

    public ClientSide(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Client joined.");
        didJoinRoom = true;
        member = Member.CLIENT;
        sendStartGameEvent();
    }

    @Override
    public void onMessage(String message) {
        requestGameBoardEvent();
        setGameBoardEvent(message);
    }

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

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Client closed.");
        didJoinRoom = false;
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }
}
