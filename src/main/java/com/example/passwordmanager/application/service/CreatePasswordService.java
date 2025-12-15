package com.example.passwordmanager.application.service;

import com.example.passwordmanager.domain.model.PasswordEntry;
import com.example.passwordmanager.domain.model.PasswordRules;
import com.example.passwordmanager.domain.port.in.CreatePasswordUseCase;
import com.example.passwordmanager.domain.port.out.PasswordRepository;
import com.example.passwordmanager.domain.service.PasswordGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Implementação de caso de uso.
 *
 */
public class CreatePasswordService implements CreatePasswordUseCase {

    private final PasswordRepository passwordRepository;
    private final PasswordGenerator passwordGenerator;

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

        PasswordRules rules = PasswordRules.from(
                numberOfCharacters,
                useEspecialCharacters,
                useNumbers,
                useCapitalize
        );

        String generated = passwordGenerator.generate(rules);

        PasswordEntry entry = new PasswordEntry(
                UUID.randomUUID(),
                generated,
                description,
                LocalDateTime.now()
        );

        return passwordRepository.save(entry);
    }
}
