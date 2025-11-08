package com.fiap.pj.core.geradoruuid.app.usecase;

import com.fiap.pj.core.geradoruuid.app.response.UUIDGeradoResponse;
import com.fiap.pj.infra.geradoruuid.controller.request.ListarUUIDRequest;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarUUIDUseCase {

    Slice<UUIDGeradoResponse> handle(ListarUUIDRequest cmd);
}