package com.fiap.pj.infra.geradoruuid.persistence;


import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface UUIDGeradoRepositoryJpa extends Repository<UUIDGeradoEntity, UUID>, ExtendedRepository<UUIDGeradoEntity, UUID>, JpaSpecificationExecutor<UUIDGeradoEntity> {

}