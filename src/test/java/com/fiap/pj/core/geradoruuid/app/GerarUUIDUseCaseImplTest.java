package com.fiap.pj.core.geradoruuid.app;

import com.fiap.pj.core.geradoruuid.app.gateways.UUIDGeradoGateway;
import com.fiap.pj.core.geradoruuid.domain.UUIDGerado;
import com.fiap.pj.core.geradoruuid.util.factory.UUIDGeradoTestFactory;
import com.fiap.pj.infra.util.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GerarUUIDUseCaseImplTest {

    @Mock
    private UUIDGeradoGateway uuidGeradoGateway;

    @InjectMocks
    private GerarUUIDUseCaseImpl gerarUUIDUseCaseImpl;

    @Test
    void deveGerarUUID() {
        TestSecurityConfig.setAuthentication();

        when(this.uuidGeradoGateway.salvar(any(UUIDGerado.class))).thenReturn(UUIDGeradoTestFactory.umUUIDGerado());

        var uuidGerado = this.gerarUUIDUseCaseImpl.handle(UUIDGeradoTestFactory.umGerarUUIDCommand());
        assertNotNull(uuidGerado);
    }
}