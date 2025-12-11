package com.example.passwordmanager.application.service;

import com.example.passwordmanager.domain.model.PasswordEntry;
import com.example.passwordmanager.domain.port.in.GetPasswordUseCase;
import com.example.passwordmanager.domain.port.out.PasswordRepository;

import java.util.List;
import java.util.UUID;

/**
 * Implementação de caso de uso de leitura de senhas.
 */
public class GetPasswordService implements GetPasswordUseCase {

    private final PasswordRepository passwordRepository;

    public GetPasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Override
    public PasswordEntry getById(UUID id) {
        return passwordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Senha não encontrada"));
    }

    @Override
    public List<PasswordEntry> getAll() {
        return passwordRepository.findAll();
    }
}
