package com.fiap.pj.core.usuario.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Getter
public class Usuario implements Serializable {

    private static final long serialVersionUID = -2059235872181567136L;

    private UUID id;
    private String nome;
    private String sobreNome;
    private String email;
    private String senha;

    public Usuario(UUID id, String nome, String sobreNome, String email, String senha) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.email = requireNonNull(email);
        this.senha = requireNonNull(senha);
    }

    public void alterar(String nome, String sobreNome, String senha) {
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.senha = requireNonNull(senha);
    }
}