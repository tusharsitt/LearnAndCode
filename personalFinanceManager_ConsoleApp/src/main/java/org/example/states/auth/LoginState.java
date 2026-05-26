package org.example.states.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;
import org.example.dtos.AuthRequestDto;
import org.example.dtos.AuthResponseDto;
import org.example.states.AppState;
import org.example.states.main.MainMenuState;
import org.example.util.ApiHelper;

/**
 * Handles user login by collecting credentials, calling the login API,
 * and storing the returned JWT token in the context.
 */
public class LoginState implements AppState {

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n--- Login ---");

        final String email = context.readString("Email: ");
        final String password = context.readString("Password: ");

        final AuthRequestDto request = new AuthRequestDto();
        request.setEmail(email);
        request.setPassword(password);

        try {
            final AuthResponseDto response = ApiHelper.post(
                    context,
                    "/auth/login",
                    request,
                    new TypeReference<>() {}
            );

            context.setJwtToken(response.getToken());
            System.out.println("[✓] Login successful! Welcome back.");
            return new MainMenuState();

        } catch (Exception e) {
            System.out.println("[!] Login failed: " + e.getMessage());
            return new InitialState();
        }
    }
}
