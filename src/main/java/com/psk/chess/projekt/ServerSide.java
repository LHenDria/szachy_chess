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

public class ServerSide extends WebSocketServer {
    private boolean didClientJoin = false;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private FigureNames[][] gameBoard;

    public void setGameBoard(FigureNames[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public ServerSide(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {

    }

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

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Server closed.");
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println("received ByteBuffer from " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress() + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("Server started.");
    }
}
