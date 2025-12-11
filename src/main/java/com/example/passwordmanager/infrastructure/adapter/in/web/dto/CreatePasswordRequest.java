package com.example.passwordmanager.infrastructure.adapter.in.web.dto;

/**
 * DTO de entrada para criar uma senha com regras customizadas.
 *
 * Importante: este DTO é da camada de infraestrutura (web), não do domínio.
 * O domínio não sabe que isso é um JSON ou HTTP.
 */
public class CreatePasswordRequest {

    private String description;
    private Integer numberOfCharacters;
    private Boolean useEspecialCharacters;
    private Boolean useNumbers;
    private Boolean useCapitalize;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(Integer numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }

    public Boolean getUseEspecialCharacters() {
        return useEspecialCharacters;
    }

    public void setUseEspecialCharacters(Boolean useEspecialCharacters) {
        this.useEspecialCharacters = useEspecialCharacters;
    }

    public Boolean getUseNumbers() {
        return useNumbers;
    }

    public void setUseNumbers(Boolean useNumbers) {
        this.useNumbers = useNumbers;
    }

    public Boolean getUseCapitalize() {
        return useCapitalize;
    }

    public void setUseCapitalize(Boolean useCapitalize) {
        this.useCapitalize = useCapitalize;
    }
}
