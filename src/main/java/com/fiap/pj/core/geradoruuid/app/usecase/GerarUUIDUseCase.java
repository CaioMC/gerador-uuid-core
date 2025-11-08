package com.fiap.pj.core.geradoruuid.app.usecase;

import com.fiap.pj.core.geradoruuid.app.usecase.command.GerarUUIDCommand;

import java.util.UUID;

public interface GerarUUIDUseCase {

    UUID handle(GerarUUIDCommand cmd);
}