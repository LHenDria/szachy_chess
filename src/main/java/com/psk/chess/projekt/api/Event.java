package com.psk.chess.projekt.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = Event.class,
        visible = true)
@JsonSubTypes({
        @Type(value = MoveEvent.class, name = "MOVE"),
        @Type(value = GameBoardEvent.class, name = "GAMEBOARD"),
})
public class Event {
    EventType type;
}