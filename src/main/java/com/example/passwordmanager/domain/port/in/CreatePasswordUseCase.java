package com.example.passwordmanager.domain.port.in;

import com.example.passwordmanager.domain.model.PasswordEntry;

/**
 * Porta de entrada para o caso de uso de criação de senha.
 *
 * Padrão: Use Case (Application Service API)
 * - Esta interface representa o que o mundo externo pode pedir ao domínio:
 *   "crie uma senha".
 * - O REST Controller chamará esta interface, NÃO a implementação diretamente.
 */
public interface CreatePasswordUseCase {

    PasswordEntry createPassword(String description,
                                 Integer numberOfCharacters,
                                 Boolean useEspecialCharacters,
                                 Boolean useNumbers,
                                 Boolean useCapitalize);
}
