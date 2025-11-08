package com.fiap.pj.core.usuario.app.usecase.command;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
public class AlterarUsuarioCommand extends UserCommand {

    @Setter
    @JsonIgnore
    private UUID id;

    public AlterarUsuarioCommand(UUID id, String nome, String sobreNome, String senha) {
        super(nome, sobreNome, senha);
        this.id = id;
    }
}




