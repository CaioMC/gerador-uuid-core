package com.fiap.pj.infra.geradoruuid.controller;


import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.helpers.JwtUtil;
import com.fiap.pj.infra.util.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"classpath:db/it/uuidgerado/create_uuid.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class ListarUUIDControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarUsuarios() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(GeradorUUIDController.PATH))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].uuidGerado", is("751c0a9e-59a3-4133-9fb8-e67998520096")))
                .andExpect(jsonPath("$.items[0].usuarioId", is("f8b34383-fe7a-4e14-9d0a-7d01d7fe3ef6")));
    }
}