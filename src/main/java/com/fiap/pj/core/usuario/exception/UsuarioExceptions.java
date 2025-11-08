package com.fiap.pj.core.usuario.exception;

public class UsuarioExceptions {
    private UsuarioExceptions() {
    }

    public static class UsuarioNaoEncontradoException extends RuntimeException {
        public UsuarioNaoEncontradoException() {
            super("Usuário não encontrado.");
        }

        public UsuarioNaoEncontradoException(String message) {
            super(message);
        }
    }


    public static class UsuarioComRelacionamentoException extends RuntimeException {
        public UsuarioComRelacionamentoException() {
            super("Usuário com relacionamento, não permite excluir.");
        }
    }
}
