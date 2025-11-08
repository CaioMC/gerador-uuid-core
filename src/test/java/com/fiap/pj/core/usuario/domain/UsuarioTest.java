package com.fiap.pj.core.usuario.domain;

import com.fiap.pj.core.usuario.util.factory.UsuarioTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.usuario.util.factory.UsuarioTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factory.UsuarioTestFactory.LAST_NAME;
import static com.fiap.pj.core.usuario.util.factory.UsuarioTestFactory.NAME;
import static com.fiap.pj.core.usuario.util.factory.UsuarioTestFactory.PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsuarioTest {

    @Test
    @DisplayName("Deve criar com sucesso uma instância de usuario.")
    void deveCriarUsuario() {

        var usuario = UsuarioTestFactory.umUsuario();

        assertEquals(NAME, usuario.getNome());
        assertEquals(LAST_NAME, usuario.getSobreNome());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(PASSWORD, usuario.getSenha());
    }

    @Nested
    class FalhaNaCriacao {

        @Test
        void deveFalharComNomeInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Usuario(UsuarioTestFactory.ID,
                            null,
                            null,
                            null, null));
        }

        @Test
        void deveFalharComEmailInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Usuario(UsuarioTestFactory.ID,
                            NAME,
                            LAST_NAME,
                            null, null));
        }

        @Test
        void deveFalharComSenhaInvalida() {
            assertThrows(NullPointerException.class,
                    () -> new Usuario(UsuarioTestFactory.ID,
                            NAME,
                            LAST_NAME,
                            E_MAIL, null));
        }
    }
}