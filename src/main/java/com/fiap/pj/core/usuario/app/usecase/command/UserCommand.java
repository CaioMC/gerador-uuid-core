package com.fiap.pj.core.usuario.app.usecase.command;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public abstract class UserCommand {

    @NotBlank(message = "Nome do usuário não pode estar vazio.")
    protected String nome;

    protected String sobreNome;

    @NotBlank(message = "Senha do usuário não pode estar vazio.")
    protected String senha;

    protected UserCommand(String nome, String sobreNome, String senha) {
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.senha = requireNonNull(senha);
    }
}




