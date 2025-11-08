package com.fiap.pj.infra.geradoruuid.controller;


import com.fiap.pj.core.geradoruuid.app.usecase.GerarUUIDUseCase;
import com.fiap.pj.core.geradoruuid.app.usecase.command.GerarUUIDCommand;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GerarUUIDControllerTest {

    @Mock
    private GerarUUIDUseCase gerarUUIDUseCase;

    @InjectMocks
    private GeradorUUIDController userController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void deve() throws Exception {
        UUID uuid = UUID.randomUUID();

        Mockito.when(this.gerarUUIDUseCase.handle(Mockito.any(GerarUUIDCommand.class))).thenReturn(uuid);

        this.mock.perform(post(
                TestUtils.buildURL(GeradorUUIDController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(new GerarUUIDCommand()))).andExpect(status().is2xxSuccessful());
    }
}