package com.fiap.pj.core.geradoruuid.app;

import com.fiap.pj.core.geradoruuid.app.gateways.UUIDGeradoGateway;
import com.fiap.pj.core.geradoruuid.app.response.UUIDGeradoResponse;
import com.fiap.pj.core.geradoruuid.util.factory.UUIDGeradoTestFactory;
import com.fiap.pj.infra.geradoruuid.controller.request.ListarUUIDRequest;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.util.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarUUIDUseCaseImplTest {

    @Mock
    private UUIDGeradoGateway uuidGeradoGateway;

    @InjectMocks
    private ListarUUIDUseCaseImpl listarUUIDUseCase;

    @Test
    void deveListarUsuarios() {
        TestSecurityConfig.setAuthentication();

        var request = new ListarUUIDRequest(PageRequest.of(0, 10));

        UUIDGeradoResponse uuidGeradoResponseMock = Mockito.mock(UUIDGeradoResponse.class);

        when(uuidGeradoResponseMock.getUuidGerado()).thenReturn(UUIDGeradoTestFactory.ID.toString());
        when(uuidGeradoResponseMock.getUsuarioId()).thenReturn(UUIDGeradoTestFactory.USUARIO_ID.toString());
        when(uuidGeradoResponseMock.getDataCriacao()).thenReturn(UUIDGeradoTestFactory.DATA_CRIACAO);

        var slice = new Slice<>(false, List.of(uuidGeradoResponseMock));

        when(uuidGeradoGateway.listarUUIDGerados(request)).thenReturn(slice);

        var result = this.listarUUIDUseCase.handle(request);

        assertNotNull(result);

        var item = result.getItems().stream().findFirst().orElse(null);

        assertNotNull(item);
        assertEquals(1, result.getItems().size());
        assertNotNull(item.getUuidGerado());
        assertEquals(UUIDGeradoTestFactory.USUARIO_ID.toString(), item.getUsuarioId().toString());
        assertEquals(UUIDGeradoTestFactory.DATA_CRIACAO, item.getDataCriacao());
    }
}