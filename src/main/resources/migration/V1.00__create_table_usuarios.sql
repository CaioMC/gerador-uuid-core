CREATE TABLE usuarios
(
    id         UUID           NOT NULL,
    nome       VARCHAR(255)   NOT NULL,
    sobre_nome  VARCHAR(255)   NOT NULL,
    ativo      BOOLEAN        NOT NULL,
    email      VARCHAR(255)   NOT NULL UNIQUE,
    senha      VARCHAR(255)   NOT NULL,
    CONSTRAINT usuarios_pkey  PRIMARY KEY (id)
);


CREATE TABLE usuarios_perfil
(
   usuario_id               UUID		NOT NULL,
   perfil                   SMALLINT 	NOT NULL,

   CONSTRAINT usuarios_perfil_pkey		PRIMARY KEY (usuario_id, perfil),
   CONSTRAINT fk_usuarios 			    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

