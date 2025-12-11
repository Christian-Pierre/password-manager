package com.example.passwordmanager.application.service;

import com.example.passwordmanager.domain.model.PasswordEntry;
import com.example.passwordmanager.domain.port.in.CreatePasswordUseCase;
import com.example.passwordmanager.domain.port.out.PasswordRepository;
import com.example.passwordmanager.domain.service.PasswordGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Implementação de caso de uso.
 *
 * Padrão: Application Service / Use Case
 * - Esta classe é uma implementação de uma porta de entrada do domínio.
 * - Ela coordena o fluxo:
 *     1) pede ao gerador de senha (núcleo) para criar uma senha
 *     2) cria a entidade de domínio PasswordEntry
 *     3) usa a porta de saída PasswordRepository para persistir
 *
 * Observação importante (proteção do core):
 *  - A regra de geração de senha NUNCA é chamada diretamente pelo controller.
 *  - O controller chama APENAS esta porta de entrada do domínio.
 */
public class CreatePasswordService implements CreatePasswordUseCase {

    private final PasswordRepository passwordRepository;
    private final PasswordGenerator passwordGenerator;

    private static final int MIN_LENGTH = 4;

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

        if (numberOfCharacters == null || numberOfCharacters < MIN_LENGTH) {
                throw new IllegalArgumentException("O tamanho da senha deve ser de no mínimo " + MIN_LENGTH + " caracteres.");
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
