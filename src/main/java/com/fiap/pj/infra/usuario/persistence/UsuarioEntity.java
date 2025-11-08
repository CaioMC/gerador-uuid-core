package com.fiap.pj.infra.usuario.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = -2059235872181567136L;

    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobreNome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @Setter
    private String senha;

    public UsuarioEntity(UUID id, String nome, String sobreNome, String email, String senha) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.email = requireNonNull(email);
        this.senha = requireNonNull(senha);
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.sobreNome;
    }

}