package io.imob.core.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class UserInactiveOrBlockedException extends OAuth2AuthenticationException {

    public UserInactiveOrBlockedException() {
        super("Usuário inativo ou bloqueado!");
    }

}