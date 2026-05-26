package com.tushar.personalFinanceManager.auth;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {


    private final String token;

    public JwtAuthenticationToken(String jwtToken) {

        super(Collections.emptyList());
        token = jwtToken;

        // Creating an empty token.
        super.setAuthenticated(false);

    }

    @Override
    public @Nullable Object getCredentials() {
        return null;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return null;
    }
}
