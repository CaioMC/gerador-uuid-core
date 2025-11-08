package com.fiap.pj.infra.config;

import com.fiap.pj.core.geradoruuid.app.GerarUUIDUseCaseImpl;
import com.fiap.pj.core.geradoruuid.app.ListarUUIDUseCaseImpl;
import com.fiap.pj.core.geradoruuid.app.gateways.UUIDGeradoGateway;
import com.fiap.pj.infra.geradoruuid.gateways.UUIDGeradoRepositoryGatewayImpl;
import com.fiap.pj.infra.geradoruuid.gateways.UUIDGeradoRepositoryMapper;
import com.fiap.pj.infra.geradoruuid.persistence.UUIDGeradoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UUIDGeradoConfig {

    @Bean
    GerarUUIDUseCaseImpl UUIDGeradoUseCase(
            UUIDGeradoGateway repository) {
        return new GerarUUIDUseCaseImpl(repository);
    }

    @Bean
    ListarUUIDUseCaseImpl ListarUUIDUseCase(
            UUIDGeradoGateway repository) {
        return new ListarUUIDUseCaseImpl(repository);
    }

    @Bean
    UUIDGeradoRepositoryGatewayImpl uuidGeradoGateway(
            UUIDGeradoRepositoryJpa repository,
            UUIDGeradoRepositoryMapper uuidGeradoRepositoryMapper) {
        return new UUIDGeradoRepositoryGatewayImpl(repository, uuidGeradoRepositoryMapper);
    }

    @Bean
    UUIDGeradoRepositoryMapper UUIDGeradoMapper() {
        return new UUIDGeradoRepositoryMapper();
    }
}