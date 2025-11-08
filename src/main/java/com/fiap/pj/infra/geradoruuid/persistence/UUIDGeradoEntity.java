package com.fiap.pj.infra.geradoruuid.persistence;

import com.fiap.pj.core.utils.DateTimeUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Getter
@Entity
@Table(name = "uuid_gerado")
@NoArgsConstructor
public class UUIDGeradoEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID uuidGerado;

    @Column(nullable = false)
    private UUID usuarioId;

    private ZonedDateTime dataCriacao;

    public UUIDGeradoEntity(UUID id, UUID usuarioId) {
        this.id = requireNonNull(id);
        this.uuidGerado = UUID.randomUUID();
        this.usuarioId = requireNonNull(usuarioId);
        this.dataCriacao = DateTimeUtils.getNow();
    }
}