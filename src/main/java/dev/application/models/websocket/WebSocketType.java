package dev.application.models.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebSocketType {
    ADD("ADD"),
    UPDATE("UPDATE"),
    DELETE("DELETE");
    private final String value;
}
