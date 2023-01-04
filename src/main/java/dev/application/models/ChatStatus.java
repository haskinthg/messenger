package dev.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatStatus {
    CREATED("CREATED"),
    DELETED("DELETED");
    private final String value;
}
