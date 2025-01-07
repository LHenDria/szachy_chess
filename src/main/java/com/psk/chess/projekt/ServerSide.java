package com.psk.chess.projekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psk.chess.projekt.api.Event;
import com.psk.chess.projekt.api.GameBoardEvent;
import com.psk.chess.projekt.api.MoveEvent;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.nio.ByteBuffer;

public class ServerSide extends WebSocketServer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        //conn.send("Welcome to the server!");
        //broadcast("new connection: " + handshake.getResourceDescriptor());
        //System.out.println("new connection to " + conn.getRemoteSocketAddress());

        try {
            conn.send(objectMapper.writeValueAsString(new GameBoardEvent()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try {
            Event eventType = objectMapper.readValue(message, Event.class);

            if (eventType instanceof MoveEvent moveEvent) {
                System.out.println(moveEvent);
            } else if (eventType instanceof GameBoardEvent gameBoardEvent) {
                System.out.println(gameBoardEvent);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println("server started successfully");
    }
}
