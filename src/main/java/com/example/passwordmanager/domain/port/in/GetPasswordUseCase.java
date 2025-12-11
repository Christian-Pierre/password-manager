package com.example.passwordmanager.domain.port.in;

import com.example.passwordmanager.domain.model.PasswordEntry;

import java.util.List;
import java.util.UUID;

/**
 * Porta de entrada para consulta de senhas.
 */
public interface GetPasswordUseCase {

    PasswordEntry getById(UUID id);

    List<PasswordEntry> getAll();
}
