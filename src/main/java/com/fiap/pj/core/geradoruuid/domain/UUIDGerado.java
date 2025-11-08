package com.fiap.pj.core.geradoruuid.domain;

import com.fiap.pj.core.utils.DateTimeUtils;
import lombok.Getter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class UUIDGerado implements Serializable {

    private static final long serialVersionUID = -5702303289282195665L;

    private UUID id;
    private UUID uuidGerado;
    private UUID usuarioId;
    private ZonedDateTime dataCriacao;

    public UUIDGerado(UUID id, UUID uuidGerado, UUID usuarioId, ZonedDateTime dataCriacao) {
        this.id = id;
        this.uuidGerado = uuidGerado;
        this.usuarioId = usuarioId;
        this.dataCriacao = dataCriacao;
    }

    public static UUIDGerado gerarUUID(UUID usuarioId) {
        return new UUIDGerado(
                UUID.randomUUID(),
                UUID.randomUUID(),
                usuarioId,
                DateTimeUtils.getNow()
        );
    }
}