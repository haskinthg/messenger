package dev.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    FILE("FILE"),
    IMAGE("IMAGE"),
    TEXT("TEXT"),
    REPLY("REPLY"),
    FORWARD("FORWARD");
    private final String value;
}
