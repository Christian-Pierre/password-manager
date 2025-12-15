package com.example.passwordmanager.domain.impl;

import com.example.passwordmanager.domain.model.PasswordRules;
import com.example.passwordmanager.domain.service.PasswordGenerator;

import java.security.SecureRandom;
import java.util.EnumSet;

/**
 * Implementação padrão do gerador de senhas.
 *
 * Padrões aplicados:
 * ------------------------------------------------
 * - Strategy: esta é uma implementação concreta de PasswordGenerator
 * - Replace Conditional with Polymorphism:
 *     os tipos de caracteres são representados por um enum (CharType)
 * - Single Responsibility:
 *     esta classe APENAS gera senhas (não valida regras, não define defaults)
 */
public class DefaultPasswordGenerator implements PasswordGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Cada constante representa um tipo de caractere possível.
     * O enum sabe:
     * - quais caracteres representa
     * - quando está habilitado
     * - se precisa garantir ao menos 1 caractere
     */
    private enum CharType {

        LOWER("abcdefghijklmnopqrstuvwxyz") {
            @Override
            boolean isEnabled(PasswordRules rules) {
                return true; // base obrigatória
            }

            @Override
            boolean mustGuaranteeOne(PasswordRules rules) {
                return false;
            }
        },

        UPPER("ABCDEFGHIJKLMNOPQRSTUVWXYZ") {
            @Override
            boolean isEnabled(PasswordRules rules) {
                return rules.useCapitalize();
            }

            @Override
            boolean mustGuaranteeOne(PasswordRules rules) {
                return rules.useCapitalize();
            }
        },

        NUMBER("0123456789") {
            @Override
            boolean isEnabled(PasswordRules rules) {
                return rules.useNumbers();
            }

            @Override
            boolean mustGuaranteeOne(PasswordRules rules) {
                return rules.useNumbers();
            }
        },

        SPECIAL("!@#$%^&*()-_=+[]{};:,.<>?") {
            @Override
            boolean isEnabled(PasswordRules rules) {
                return rules.useSpecials();
            }

            @Override
            boolean mustGuaranteeOne(PasswordRules rules) {
                return rules.useSpecials();
            }
        };

        private final String chars;

        CharType(String chars) {
            this.chars = chars;
        }

        String chars() {
            return chars;
        }

        abstract boolean isEnabled(PasswordRules rules);

        abstract boolean mustGuaranteeOne(PasswordRules rules);
    }

    @Override
    public String generate(PasswordRules rules) {

        StringBuilder pool = new StringBuilder();
        StringBuilder password = new StringBuilder(rules.length());

        // 1️ Monta o pool e garante os caracteres obrigatórios
        for (CharType type : EnumSet.allOf(CharType.class)) {

            if (type.isEnabled(rules)) {
                pool.append(type.chars());
            }

            if (type.mustGuaranteeOne(rules)) {
                password.append(randomChar(type.chars()));
            }
        }

        // 2️ Completa o restante da senha
        while (password.length() < rules.length()) {
            password.append(randomChar(pool.toString()));
        }

        // 3️ (Opcional) embaralhar os caracteres para evitar padrões previsíveis
        return shuffle(password.toString());
    }

    private char randomChar(String source) {
        return source.charAt(RANDOM.nextInt(source.length()));
    }

    /**
     * Embaralha os caracteres da senha para evitar que
     * os caracteres "garantidos" fiquem sempre no início.
     */
    private String shuffle(String input) {
        char[] chars = input.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}
