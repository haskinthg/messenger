package dev.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageStatus {
    RECEIVED("RECEIVED"),
    DELIVERED("DELIVERED"),
    DELETED("DELETED");
    private final String value;
}
