package com.fiap.pj.infra.geradoruuid.controller.openapi;

import com.fiap.pj.core.geradoruuid.app.response.UUIDGeradoResponse;
import com.fiap.pj.core.geradoruuid.app.usecase.command.GerarUUIDCommand;
import com.fiap.pj.infra.geradoruuid.controller.request.ListarUUIDRequest;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface GeradorUUIDControllerOpenApi {

    @Operation(description = "Cria um novo UUID", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "UUID criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  UUID não pode ser criado.")})
    ResponseEntity<ResponseId> gerarUUID(@Valid @RequestBody GerarUUIDCommand cmd);

    @Operation(description = "Retorna uma lista de uuids gerados.", method = "GET")
    Slice<UUIDGeradoResponse> listarUUID(@ParameterObject ListarUUIDRequest filterRequest, @ParameterObject Pageable pageable);
}