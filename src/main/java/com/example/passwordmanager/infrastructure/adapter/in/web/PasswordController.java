package com.example.passwordmanager.infrastructure.adapter.in.web;

import com.example.passwordmanager.domain.model.PasswordEntry;
import com.example.passwordmanager.domain.port.in.CreatePasswordUseCase;
import com.example.passwordmanager.domain.port.in.GetPasswordUseCase;
import com.example.passwordmanager.infrastructure.adapter.in.web.dto.CreatePasswordRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Adaptador de entrada (REST Controller).
 *
 * Padrão: Adapter
 * - Ele expõe HTTP, mas fala com o núcleo apenas via ports (CreatePasswordUseCase e GetPasswordUseCase).
 * - Não tem lógica de geração de senha aqui, apenas delegação para o core.
 */
@RestController
@RequestMapping("/passwords")
public class PasswordController {

    private final CreatePasswordUseCase createPasswordUseCase;
    private final GetPasswordUseCase getPasswordUseCase;

    public PasswordController(CreatePasswordUseCase createPasswordUseCase,
                              GetPasswordUseCase getPasswordUseCase) {
        this.createPasswordUseCase = createPasswordUseCase;
        this.getPasswordUseCase = getPasswordUseCase;
    }

    @PostMapping("/default")
    public PasswordEntry createDefault(@RequestParam(required = false) String description) {
        // Poderia chamar o use case com default (null) ou valores padrão
        return createPasswordUseCase.createPassword(description, null, null, null, null);
    }

    // Novo endpoint com regras personalizadas
    @PostMapping("/custom")
    public PasswordEntry createWithRules(@RequestBody CreatePasswordRequest request) {
        return createPasswordUseCase.createPassword(
                request.getDescription(),
                request.getNumberOfCharacters(),
                request.getUseEspecialCharacters(),
                request.getUseNumbers(),
                request.getUseCapitalize()
        );
    }

    @GetMapping("/{id}")
    public PasswordEntry getById(@PathVariable UUID id) {
        return getPasswordUseCase.getById(id);
    }

    @GetMapping
    public List<PasswordEntry> getAll() {
        return getPasswordUseCase.getAll();
    }
}
