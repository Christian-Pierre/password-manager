package com.example.passwordmanager.application.service;

import com.example.passwordmanager.domain.model.PasswordEntry;
import com.example.passwordmanager.domain.port.in.CreatePasswordUseCase;
import com.example.passwordmanager.domain.port.out.PasswordRepository;
import com.example.passwordmanager.domain.service.PasswordGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementação de caso de uso.
 *
 * Regras importantes:
 * - Se numberOfCharacters for null => gera um tamanho aleatório entre 6 e 12.
 * - Se numberOfCharacters for informado e < 4 => lança exceção.
 */
public class CreatePasswordService implements CreatePasswordUseCase {

    private final PasswordRepository passwordRepository;
    private final PasswordGenerator passwordGenerator;

    private static final int MIN_LENGTH = 4;
    private static final int DEFAULT_MIN_LENGTH = 6;
    private static final int DEFAULT_MAX_LENGTH = 12;

    public CreatePasswordService(PasswordRepository passwordRepository,
                                 PasswordGenerator passwordGenerator) {
        this.passwordRepository = passwordRepository;
        this.passwordGenerator = passwordGenerator;
    }

    @Override
    public PasswordEntry createPassword(String description,
                                        Integer numberOfCharacters,
                                        Boolean useEspecialCharacters,
                                        Boolean useNumbers,
                                        Boolean useCapitalize) {

        // Regra 1: se não veio valor, escolhe aleatoriamente entre 6 e 12
        if (numberOfCharacters == null) {
            numberOfCharacters = ThreadLocalRandom.current()
                    .nextInt(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH + 1);
        }

        // Regra 2: se veio valor explícito < 4, rejeita
        if (numberOfCharacters < MIN_LENGTH) {
            throw new IllegalArgumentException(
                    "O tamanho da senha deve ser de no mínimo " + MIN_LENGTH + " caracteres."
            );
        }

        // Toda chamada de geração de senha passa pelo core (PasswordGenerator)
        String generated = passwordGenerator.generateForRules(
                numberOfCharacters,
                useEspecialCharacters,
                useNumbers,
                useCapitalize
        );

        PasswordEntry entry = new PasswordEntry(
                UUID.randomUUID(),
                generated,
                description,
                LocalDateTime.now()
        );

        return passwordRepository.save(entry);
    }
}
