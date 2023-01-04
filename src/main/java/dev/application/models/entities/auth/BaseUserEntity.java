package dev.application.models.entities.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseUserEntity {
    private String username;
    private String password;
}
