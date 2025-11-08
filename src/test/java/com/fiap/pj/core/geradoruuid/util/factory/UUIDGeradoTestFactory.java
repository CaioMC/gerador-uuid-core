package com.fiap.pj.core.geradoruuid.util.factory;

import com.fiap.pj.core.geradoruuid.app.usecase.command.GerarUUIDCommand;
import com.fiap.pj.core.geradoruuid.domain.UUIDGerado;
import com.fiap.pj.core.utils.DateTimeUtils;

import java.time.ZonedDateTime;
import java.util.UUID;

public class UUIDGeradoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final UUID UUID_GERADO = UUID.randomUUID();
    public static final UUID USUARIO_ID = UUID.randomUUID();
    public static final ZonedDateTime DATA_CRIACAO = DateTimeUtils.getNow();

    public static UUIDGerado umUUIDGerado() {
        return new UUIDGerado(ID, UUID_GERADO, USUARIO_ID, DATA_CRIACAO);
    }

    public static GerarUUIDCommand umGerarUUIDCommand() {
        return new GerarUUIDCommand();
    }
}