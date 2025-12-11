package com.example.passwordmanager.domain.impl;

import com.example.passwordmanager.domain.service.PasswordGenerator;

import java.security.SecureRandom;

/**
 * Implementação padrão do gerador de senhas.
 *
 * Padrão de projeto: Strategy (Concrete Strategy)
 * ------------------------------------------------
 * - Esta classe é uma estratégia concreta de geração de senha.
 * - Se quisermos outra forma de gerar senha (por exemplo, integrando
 *   com um serviço externo), criamos outra implementação de PasswordGenerator.
 */
public class DefaultPasswordGenerator implements PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{};:,.<>?";

    // SecureRandom é mais adequado para geração de senhas do que Random
    private final SecureRandom random = new SecureRandom();

    @Override
    public String generateForRules(Integer numberOfCharacters,
                                   Boolean useEspecialCharacters,
                                   Boolean useNumbers,
                                   Boolean useCapitalize) {

        // Definição de defaults caso venham null
        if (numberOfCharacters == null || numberOfCharacters < 4) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 4 caracteres.");
        }

        int length = numberOfCharacters;
        boolean specials = Boolean.TRUE.equals(useEspecialCharacters);
        boolean numbers = Boolean.TRUE.equals(useNumbers);
        boolean capitalize = Boolean.TRUE.equals(useCapitalize);

        StringBuilder pool = new StringBuilder(LOWERCASE);

        if (capitalize) {
            pool.append(UPPERCASE);
        }
        if (numbers) {
            pool.append(NUMBERS);
        }
        if (specials) {
            pool.append(SPECIAL);
        }

        // Se por algum motivo ninguém ligou opções, garantimos pelo menos letras minúsculas
        if (pool.length() == 0) {
            pool.append(LOWERCASE);
        }

        StringBuilder password = new StringBuilder(length);

        // Regra simples: garantir ao menos 1 de cada tipo escolhido
        if (capitalize) {
            password.append(randomChar(UPPERCASE));
        }
        if (numbers) {
            password.append(randomChar(NUMBERS));
        }
        if (specials) {
            password.append(randomChar(SPECIAL));
        }

        // Completa o restante dos caracteres com o pool combinado
        while (password.length() < length) {
            password.append(randomChar(pool.toString()));
        }

        // Em aplicação real, poderíamos embaralhar os caracteres
        return password.toString();
    }

    private char randomChar(String source) {
        int index = random.nextInt(source.length());
        return source.charAt(index);
    }
}
