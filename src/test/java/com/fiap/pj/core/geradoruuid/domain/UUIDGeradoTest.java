package com.fiap.pj.core.geradoruuid.domain;

import com.fiap.pj.core.geradoruuid.util.factory.UUIDGeradoTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.geradoruuid.util.factory.UUIDGeradoTestFactory.DATA_CRIACAO;
import static com.fiap.pj.core.geradoruuid.util.factory.UUIDGeradoTestFactory.ID;
import static com.fiap.pj.core.geradoruuid.util.factory.UUIDGeradoTestFactory.USUARIO_ID;
import static com.fiap.pj.core.geradoruuid.util.factory.UUIDGeradoTestFactory.UUID_GERADO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UUIDGeradoTest {

    @Test
    @DisplayName("Deve gerar UUID.")
    void deveGerarUUID() {
        var UUIDGerado = UUIDGeradoTestFactory.umUUIDGerado();

        assertEquals(ID, UUIDGerado.getId());
        assertEquals(UUID_GERADO, UUIDGerado.getUuidGerado());
        assertEquals(USUARIO_ID, UUIDGerado.getUsuarioId());
        assertEquals(DATA_CRIACAO, UUIDGerado.getDataCriacao());
    }
}