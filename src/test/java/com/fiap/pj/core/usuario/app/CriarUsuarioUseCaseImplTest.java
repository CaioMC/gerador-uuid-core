package com.fiap.pj.core.usuario.app;

import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.util.factory.UsuarioTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioUseCaseImplTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private CriarUsuarioUseCaseImpl criarUsuarioUseCaseImpl;

    @Test
    void deveCriarUsuarior() {
        when(this.usuarioGateway.salvar(any(Usuario.class))).thenReturn(UsuarioTestFactory.umUsuario());

        var usuarioId = criarUsuarioUseCaseImpl.handle(UsuarioTestFactory.umCriarUsuarioCommand());
        assertNotNull(usuarioId);
    }
}