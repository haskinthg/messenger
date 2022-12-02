package dev.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Content {
    FILE("FILE"),
    IMAGE("IMAGE"),
    TEXT("TEXT");

    private final String value;
}
