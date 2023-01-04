package dev.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RegisterStatus {
    REG("REG"), CONTAIN("CONTAIN"), ERROR("ERROR");
    private String value;
}
