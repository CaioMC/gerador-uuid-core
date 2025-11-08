package com.fiap.pj.core.usuario.util.factory;


import com.fiap.pj.core.usuario.app.usecase.command.AlterarUsuarioCommand;
import com.fiap.pj.core.usuario.app.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.core.usuario.app.usecase.command.LoginUsuarioCommand;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.usuario.persistence.UsuarioEntity;

import java.util.UUID;

public class UsuarioTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NAME = "Hakuna";
    public static final String LAST_NAME = "Matata";
    public static final String E_MAIL = "hakuna.matata@gmail.com";
    public static final String PASSWORD = "1234";

    public static final String ALTER_NAME = "Jack";
    public static final String ALTER_LAST_NAME = "Daniels";
    public static final String ALTER_PASSWORD = "4321";

    public static Usuario umUsuario() {
        return new Usuario(ID, NAME, LAST_NAME, E_MAIL, PASSWORD);
    }

    public static UsuarioEntity umUsuarioEntity() {
        return new UsuarioEntity(ID, NAME, LAST_NAME, E_MAIL, PASSWORD);
    }

    public static CriarUsuarioCommand umCriarUsuarioCommand() {
        return new CriarUsuarioCommand(NAME, LAST_NAME, E_MAIL, PASSWORD);
    }

    public static AlterarUsuarioCommand UmAlterarUsuarioCommand(UUID id) {
        return new AlterarUsuarioCommand(id, ALTER_NAME, ALTER_LAST_NAME, ALTER_PASSWORD);
    }

    public static LoginUsuarioCommand umLoginUsuarioCommand() {
        return new LoginUsuarioCommand(E_MAIL, "2025");
    }
}