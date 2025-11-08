package com.fiap.pj.infra.geradoruuid.controller;

import com.fiap.pj.core.geradoruuid.app.response.UUIDGeradoResponse;
import com.fiap.pj.core.geradoruuid.app.usecase.GerarUUIDUseCase;
import com.fiap.pj.core.geradoruuid.app.usecase.ListarUUIDUseCase;
import com.fiap.pj.core.geradoruuid.app.usecase.command.GerarUUIDCommand;
import com.fiap.pj.infra.geradoruuid.controller.openapi.GeradorUUIDControllerOpenApi;
import com.fiap.pj.infra.geradoruuid.controller.request.ListarUUIDRequest;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = GeradorUUIDController.PATH)
@AllArgsConstructor
public class GeradorUUIDController implements GeradorUUIDControllerOpenApi {

    public static final String PATH = "v1/gerador-uuid";

    private final GerarUUIDUseCase gerarUUIDUseCase;
    private final ListarUUIDUseCase listarUUIDUseCase;

    @PostMapping
    public ResponseEntity<ResponseId> gerarUUID(@Valid @RequestBody GerarUUIDCommand cmd) {
        UUID uuidGerado = this.gerarUUIDUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), uuidGerado);
    }

    @Override
    @GetMapping
    public Slice<UUIDGeradoResponse> listarUUID(@ParameterObject ListarUUIDRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return this.listarUUIDUseCase.handle(filterRequest);
    }

}
