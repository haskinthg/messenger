package dev.application.models.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Setter
public class WebSocketObject<T> {
    WebSocketType type;
    T content;
}
