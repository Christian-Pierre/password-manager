package com.example.passwordmanager.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade de domínio que representa uma senha armazenada.
 *
 * Esta classe faz parte do núcleo (hexágono). Ela NÃO conhece nada de
 * frameworks (Spring, JPA, etc.), apenas regras do negócio.
 */
public class PasswordEntry {

    private final UUID id;
    private final String value;
    private final String description;
    private final LocalDateTime createdAt;

    public PasswordEntry(UUID id, String value, String description, LocalDateTime createdAt) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
