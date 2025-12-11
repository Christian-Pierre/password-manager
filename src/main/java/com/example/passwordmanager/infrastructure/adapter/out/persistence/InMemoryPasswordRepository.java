package com.example.passwordmanager.infrastructure.adapter.out.persistence;

import com.example.passwordmanager.domain.model.PasswordEntry;
import com.example.passwordmanager.domain.port.out.PasswordRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Adaptador de saída que implementa a porta PasswordRepository usando memória.
 *
 * Padrão: Adapter
 * - Esta classe adapta o contrato do domínio (PasswordRepository) para um meio concreto.
 * - Se amanhã quisermos trocar para um banco real, criaremos outro adapter sem mexer no core.
 */
@Repository
public class InMemoryPasswordRepository implements PasswordRepository {

    private final Map<UUID, PasswordEntry> storage = new ConcurrentHashMap<>();

    @Override
    public PasswordEntry save(PasswordEntry entry) {
        storage.put(entry.getId(), entry);
        return entry;
    }

    @Override
    public Optional<PasswordEntry> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<PasswordEntry> findAll() {
        return new ArrayList<>(storage.values());
    }
}
