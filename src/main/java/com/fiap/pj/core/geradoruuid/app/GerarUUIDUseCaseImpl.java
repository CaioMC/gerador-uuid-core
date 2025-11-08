package com.fiap.pj.core.geradoruuid.app;

import com.fiap.pj.core.geradoruuid.app.gateways.UUIDGeradoGateway;
import com.fiap.pj.core.geradoruuid.app.usecase.GerarUUIDUseCase;
import com.fiap.pj.core.geradoruuid.app.usecase.command.GerarUUIDCommand;
import com.fiap.pj.core.geradoruuid.domain.UUIDGerado;
import com.fiap.pj.infra.utils.security.SecurityContextUtils;

import java.util.UUID;

public class GerarUUIDUseCaseImpl implements GerarUUIDUseCase {

    private final UUIDGeradoGateway uuidGeradoGateway;

    public GerarUUIDUseCaseImpl(UUIDGeradoGateway uuidGeradoGateway) {
        this.uuidGeradoGateway = uuidGeradoGateway;
    }

    @Override
    public UUID handle(GerarUUIDCommand cmd) {
        UUID usuarioId = SecurityContextUtils.getUsuarioId();

        UUIDGerado uuid = UUIDGerado.gerarUUID(usuarioId);

        var uuidGerado = this.uuidGeradoGateway.salvar(uuid);

        return uuidGerado.getUuidGerado();
    }
}