package com.example.passwordmanager.infrastructure.config;

import com.example.passwordmanager.application.service.CreatePasswordService;
import com.example.passwordmanager.application.service.GetPasswordService;
import com.example.passwordmanager.domain.impl.DefaultPasswordGenerator;
import com.example.passwordmanager.domain.port.in.CreatePasswordUseCase;
import com.example.passwordmanager.domain.port.in.GetPasswordUseCase;
import com.example.passwordmanager.domain.port.out.PasswordRepository;
import com.example.passwordmanager.domain.service.PasswordGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração responsável por ligar as implementações ao Spring.
 *
 * Aqui fazemos a "montagem" do hexágono:
 *  - definimos quais classes concretas implementam as interfaces (ports).
 */
@Configuration
public class BeanConfig {

        @Bean
    public PasswordGenerator passwordGenerator() {
        return new DefaultPasswordGenerator();// Escolha de Strategy que será usada
    }

    @Bean
    public CreatePasswordUseCase createPasswordUseCase(PasswordRepository repository,
                                                       PasswordGenerator generator) {
        return new CreatePasswordService(repository, generator);
    }

    @Bean
    public GetPasswordUseCase getPasswordUseCase(PasswordRepository repository) {
        return new GetPasswordService(repository);
    }
}
