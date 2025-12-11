# Password Manager – Prova de Conceito (POC)

Projeto de **prova de conceito** para um gerenciador de senhas em **Java 17** com **Spring Boot**, utilizando **arquitetura hexagonal** (ports & adapters) e foco em:

- Geração de senhas baseada em regras configuráveis
- Proteção da lógica de geração e uso de senhas no **núcleo de domínio**
- Exposição via API REST
- Persistência em memória (apenas para fins de demonstração)

---

## Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Maven**
- **Spring Web**
- **(Opcional)** springdoc-openapi (Swagger) para documentação da API

---

## Estrutura do projeto

Árvore de diretórios relevante:

```text
src/main/java/com/example/passwordmanager
├── PasswordManagerApplication.java         # Classe main do Spring Boot
├── application
│   └── service
│       ├── CreatePasswordService.java      # Caso de uso: criação de senha
│       └── GetPasswordService.java         # Caso de uso: consulta de senhas
├── domain
│   ├── impl
│   │   └── DefaultPasswordGenerator.java   # Implementação padrão do gerador (Strategy)
│   ├── model
│   │   └── PasswordEntry.java             # Entidade de domínio (senha armazenada)
│   ├── port
│   │   ├── in
│   │   │   ├── CreatePasswordUseCase.java  # Port de entrada (criar senha)
│   │   │   └── GetPasswordUseCase.java     # Port de entrada (consultar senhas)
│   │   └── out
│   │       └── PasswordRepository.java     # Port de saída (persistência)
│   └── service
│       └── PasswordGenerator.java          # Contrato do gerador de senhas
└── infrastructure
    ├── adapter
    │   ├── in
    │   │   └── web
    │   │       ├── PasswordController.java # Adapter de entrada (REST Controller)
    │   │       └── dto
    │   │           └── CreatePasswordRequest.java
    │   └── out
    │       └── persistence
    │           └── InMemoryPasswordRepository.java  # Adapter de saída (persistência em memória)
    └── config
        └── BeanConfig.java                  # Configuração de beans (montagem do hexágono)
