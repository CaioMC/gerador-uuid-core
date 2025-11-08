package com.fiap.pj.core.geradoruuid.app;


import com.fiap.pj.core.geradoruuid.app.gateways.UUIDGeradoGateway;
import com.fiap.pj.core.geradoruuid.app.response.UUIDGeradoResponse;
import com.fiap.pj.core.geradoruuid.app.usecase.ListarUUIDUseCase;
import com.fiap.pj.infra.geradoruuid.controller.request.ListarUUIDRequest;
import com.fiap.pj.infra.sk.api.Slice;


public class ListarUUIDUseCaseImpl implements ListarUUIDUseCase {

    private final UUIDGeradoGateway uuidGeradoGateway;

    public ListarUUIDUseCaseImpl(UUIDGeradoGateway usuarioGateway) {
        this.uuidGeradoGateway = usuarioGateway;
    }

    @Override
    public Slice<UUIDGeradoResponse> handle(ListarUUIDRequest request) {
        return this.uuidGeradoGateway.listarUUIDGerados(request);
    }
}
