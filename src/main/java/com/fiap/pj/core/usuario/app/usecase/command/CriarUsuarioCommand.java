package com.fiap.pj.core.usuario.app.usecase.command;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static java.util.Objects.requireNonNull;


@Getter
public class CriarUsuarioCommand extends UserCommand {


    @NotBlank(message = "E-mail do usuário não pode estar vazio.")
    protected String email;

    public CriarUsuarioCommand(String nome, String sobreNome, String email, String senha) {
        super(nome, sobreNome, senha);
        this.email = requireNonNull(email);
    }
}





