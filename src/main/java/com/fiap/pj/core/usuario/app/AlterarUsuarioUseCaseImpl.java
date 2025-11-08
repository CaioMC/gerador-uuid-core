package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.AlterarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.AlterarUsuarioCommand;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNaoEncontradoException;

public class AlterarUsuarioUseCaseImpl implements AlterarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public AlterarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void handle(AlterarUsuarioCommand cmd) {
        var usuario = this.usuarioGateway.buscarPorId(cmd.getId()).orElseThrow(UsuarioNaoEncontradoException::new);

        usuario.alterar(cmd.getNome(), cmd.getSobreNome(), cmd.getSenha());

        this.usuarioGateway.alterar(usuario);
    }
}
