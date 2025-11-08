package com.fiap.pj.infra.config;

import com.fiap.pj.core.usuario.app.AlterarUsuarioUseCaseImpl;
import com.fiap.pj.core.usuario.app.CriarUsuarioUseCaseImpl;
import com.fiap.pj.core.usuario.app.ExcluirUsuarioUseCaseImpl;
import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.infra.usuario.gateways.UsuarioRepositoryGatewayImpl;
import com.fiap.pj.infra.usuario.gateways.UsuarioRepositoryMapper;
import com.fiap.pj.infra.usuario.persistence.UsuarioRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioConfig {

    @Bean
    CriarUsuarioUseCaseImpl criarUsuarioUseCase(
            UsuarioGateway usuarioGateway
    ) {
        return new CriarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    AlterarUsuarioUseCaseImpl alterarUsuarioUseCase(
            UsuarioGateway usuarioGateway
    ) {
        return new AlterarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    ExcluirUsuarioUseCaseImpl excluirUsuarioUseCase(
            UsuarioGateway usuarioGateway
    ) {
        return new ExcluirUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    UsuarioRepositoryGatewayImpl usuarioGateway(UsuarioRepositoryJpa repository,
                                                UsuarioRepositoryMapper usuarioRepositoryMapper, PasswordEncoder passwordEncoder) {
        return new UsuarioRepositoryGatewayImpl(repository, usuarioRepositoryMapper, passwordEncoder);
    }

    @Bean
    UsuarioRepositoryMapper usuarioMapper() {
        return new UsuarioRepositoryMapper();
    }
}