package com.fiap.pj.infra.geradoruuid.gateways;

import com.fiap.pj.core.geradoruuid.app.gateways.UUIDGeradoGateway;
import com.fiap.pj.core.geradoruuid.app.response.UUIDGeradoResponse;
import com.fiap.pj.core.geradoruuid.domain.UUIDGerado;
import com.fiap.pj.infra.geradoruuid.controller.request.ListarUUIDRequest;
import com.fiap.pj.infra.geradoruuid.persistence.UUIDGeradoRepositoryJpa;
import com.fiap.pj.infra.sk.api.Slice;

import java.util.Optional;
import java.util.UUID;

public class UUIDGeradoRepositoryGatewayImpl implements UUIDGeradoGateway {

    private final UUIDGeradoRepositoryJpa repository;
    private final UUIDGeradoRepositoryMapper uuidGeradoRepositoryMapper;

    public UUIDGeradoRepositoryGatewayImpl(UUIDGeradoRepositoryJpa repository, UUIDGeradoRepositoryMapper uuidGeradoRepositoryMapper) {
        this.repository = repository;
        this.uuidGeradoRepositoryMapper = uuidGeradoRepositoryMapper;
    }

    @Override
    public UUIDGerado salvar(UUIDGerado uuidGerado) {
        var usuarioEntity = this.uuidGeradoRepositoryMapper.mapToTable(uuidGerado);
        return this.uuidGeradoRepositoryMapper.mapToDomain(repository.save(usuarioEntity));
    }

    @Override
    public void alterar(UUIDGerado uuidGerado) {
        var usuarioEntity = this.uuidGeradoRepositoryMapper.mapToTable(uuidGerado);
        repository.save(usuarioEntity);
    }

    @Override
    public void excluir(UUIDGerado uuidGerado) {
        var usuarioEntity = this.uuidGeradoRepositoryMapper.mapToTable(uuidGerado);
        repository.delete(usuarioEntity);
    }

    @Override
    public Optional<UUIDGerado> buscarPorId(UUID id) {
        return this.repository.findById(id)
                .map(this.uuidGeradoRepositoryMapper::mapToDomain);
    }

    @Override
    public Slice<UUIDGeradoResponse> listarUUIDGerados(ListarUUIDRequest request) {
        return repository.findProjectedBy(request.getPageable(), UUIDGeradoResponse.class);
    }
}