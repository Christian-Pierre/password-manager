package com.example.passwordmanager.domain.port.out;

import com.example.passwordmanager.domain.model.PasswordEntry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída do domínio para persistência.
 *
 * Padrão de projeto: Repository
 * - Esta interface representa o contrato de acesso aos dados.
 * - O domínio depende apenas da interface, não da implementação.
 * - Implementações concretas (memória, banco relacional, etc.) ficarão em "infrastructure".
 */
public interface PasswordRepository {

    PasswordEntry save(PasswordEntry entry);

    Optional<PasswordEntry> findById(UUID id);

    List<PasswordEntry> findAll();
}
