package com.fiap.pj.core.geradoruuid.app.gateways;

import com.fiap.pj.core.geradoruuid.app.response.UUIDGeradoResponse;
import com.fiap.pj.core.geradoruuid.domain.UUIDGerado;
import com.fiap.pj.infra.geradoruuid.controller.request.ListarUUIDRequest;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;

public interface UUIDGeradoGateway extends BaseRepositoryGateway<UUIDGerado> {

    Slice<UUIDGeradoResponse> listarUUIDGerados(ListarUUIDRequest request);
}