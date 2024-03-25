package io.imob.core.exception;

public class BadCredentialsException extends org.springframework.security.authentication.BadCredentialsException {

    public BadCredentialsException() {
        super("Usuário e/ou senha inválidos!");
    }
}
