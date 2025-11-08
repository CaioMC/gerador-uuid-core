package com.fiap.pj.infra.geradoruuid.gateways;

import com.fiap.pj.core.geradoruuid.domain.UUIDGerado;
import com.fiap.pj.infra.geradoruuid.persistence.UUIDGeradoEntity;

import java.util.UUID;

public class UUIDGeradoRepositoryMapper {

    UUIDGeradoEntity mapToTable(UUIDGerado usuario) {
        return new UUIDGeradoEntity(
                UUID.randomUUID(),
                usuario.getUsuarioId()
        );
    }

    UUIDGerado mapToDomain(UUIDGeradoEntity entity) {
        return new UUIDGerado(
                entity.getId(),
                entity.getUuidGerado(),
                entity.getUsuarioId(),
                entity.getDataCriacao()
        );
    }
}