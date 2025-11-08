package com.fiap.pj.core.geradoruuid.app.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.ZonedDateTime;

@JsonPropertyOrder({"uuidGerado", "usuarioId", "dataCriacao"})
public interface UUIDGeradoResponse {

    String getUuidGerado();

    String getUsuarioId();

    ZonedDateTime getDataCriacao();
}