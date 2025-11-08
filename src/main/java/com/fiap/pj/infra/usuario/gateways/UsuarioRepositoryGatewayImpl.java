package com.fiap.pj.infra.usuario.gateways;

import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.usuario.persistence.UsuarioRepositoryJpa;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

public class UsuarioRepositoryGatewayImpl implements UsuarioGateway {

    private final UsuarioRepositoryJpa repository;
    private final UsuarioRepositoryMapper usuarioRepositoryMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioRepositoryGatewayImpl(UsuarioRepositoryJpa repository, UsuarioRepositoryMapper usuarioRepositoryMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.usuarioRepositoryMapper = usuarioRepositoryMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        var usuarioEntity = usuarioRepositoryMapper.mapToTable(usuario, passwordEncoder);
        return usuarioRepositoryMapper.mapToDomain(repository.save(usuarioEntity));
    }

    @Override
    public void alterar(Usuario usuario) {
        var usuarioEntity = usuarioRepositoryMapper.mapToTable(usuario, passwordEncoder);
        repository.save(usuarioEntity);
    }

    @Override
    public void excluir(Usuario usuario) {
        var usuarioEntity = usuarioRepositoryMapper.mapToTable(usuario, passwordEncoder);
        repository.delete(usuarioEntity);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(usuarioRepositoryMapper::mapToDomain);
    }
}