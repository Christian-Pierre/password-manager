package com.example.passwordmanager.domain.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Value Object de regras de senha.
 *
 * Padrões:
 * - Value Object: representa regras imutáveis.
 * - Factory Method: cria regras já normalizadas e validadas.
 */
public class PasswordRules {
   
    private static final int MIN_LENGTH = 4;
    private static final int DEFAULT_MIN_LENGTH = 6;
    private static final int DEFAULT_MAX_LENGTH = 12;

    private final int length;
    private final boolean useSpecials;
    private final boolean useNumbers;
    private final boolean useCapitalize;

    private PasswordRules(int length, boolean useSpecials, boolean useNumbers, boolean useCapitalize) {
        this.length = length;
        this.useSpecials = useSpecials;
        this.useNumbers = useNumbers;
        this.useCapitalize = useCapitalize;
    }

    public int length() { 
        return length; 
    }
    public boolean useSpecials() { 
        return useSpecials; 
    }
    public boolean useNumbers() { 
        return useNumbers; 
    }
    public boolean useCapitalize() { 
        return useCapitalize; 
    }

    public static PasswordRules from(Integer numberOfCharacters, Boolean useEspecialCharacters, Boolean useNumbers, Boolean useCapitalize) {

        int resolvedLength = (numberOfCharacters == null)
                ? ThreadLocalRandom.current().nextInt(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH + 1)
                : numberOfCharacters;

        if (resolvedLength < MIN_LENGTH) {
            throw new IllegalArgumentException("O tamanho da senha deve ser de no mínimo " + MIN_LENGTH + " caracteres.");
        }

        return new PasswordRules(
                resolvedLength,
                Boolean.TRUE.equals(useEspecialCharacters),
                Boolean.TRUE.equals(useNumbers),
                Boolean.TRUE.equals(useCapitalize)
        );
    }
}
