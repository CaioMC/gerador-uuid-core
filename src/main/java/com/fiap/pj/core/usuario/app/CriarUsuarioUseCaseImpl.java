package com.fiap.pj.core.usuario.app;

import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.core.usuario.domain.Usuario;

import java.util.UUID;


public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public CriarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public UUID handle(CriarUsuarioCommand cmd) {
        Usuario usuario = new Usuario(UUID.randomUUID(), cmd.getNome(), cmd.getSobreNome(), cmd.getEmail(), cmd.getSenha());

        this.usuarioGateway.salvar(usuario);

        return usuario.getId();
    }
}