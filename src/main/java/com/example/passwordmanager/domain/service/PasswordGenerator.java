package com.example.passwordmanager.domain.service;

import com.example.passwordmanager.domain.model.PasswordRules;

/**
 * Serviço de domínio responsável por gerar senhas.
 *
 * Padrão de projeto: Strategy
 * ---------------------------
 * - Esta interface permite ter várias estratégias de geração de senha
 *   (por exemplo: simples, forte, com políticas específicas).
 * - O domínio conhece APENAS esta interface, não a implementação concreta.
 */
public interface PasswordGenerator {

    /**
     * Gera uma senha a partir de regras definidas pelo chamador.
     *
     * @param numberOfCharacters      quantidade de caracteres da senha
     * @param useEspecialCharacters   se deve usar caracteres especiais (!@#$...)
     * @param useNumbers              se deve usar números (0-9)
     * @param useCapitalize           se deve usar letras maiúsculas (A-Z)
     * @return senha gerada de acordo com as regras
     */
    String generate(PasswordRules rules);
}
