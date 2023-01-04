package dev.application.models.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WebSocketObject<T> {
    WebSocketType type;
    T content;
}
