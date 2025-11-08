package com.fiap.pj.infra.geradoruuid.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class ListarUUIDRequest {

    @Setter
    @JsonIgnore
    private Pageable pageable;
}