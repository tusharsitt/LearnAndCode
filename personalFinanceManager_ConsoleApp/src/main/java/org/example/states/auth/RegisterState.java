package org.example.states.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;
import org.example.dtos.AuthResponseDto;
import org.example.dtos.RegisterRequestDto;
import org.example.states.AppState;
import org.example.states.main.MainMenuState;
import org.example.util.ApiHelper;

/**
 * Handles new user registration by collecting user details, calling the register API,
 * and storing the returned JWT token in the context.
 */
public class RegisterState implements AppState {

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n--- Register ---");

        final String name = context.readString("Full Name: ");
        final String email = context.readString("Email: ");
        final String password = context.readString("Password: ");

        final RegisterRequestDto request = new RegisterRequestDto();
        request.setName(name);
        request.setEmail(email);
        request.setPassword(password);

        try {
            final AuthResponseDto response = ApiHelper.post(
                    context,
                    "/auth/register",
                    request,
                    new TypeReference<>() {}
            );

            context.setJwtToken(response.getToken());
            System.out.println("[✓] Registration successful! You are now logged in.");
            return new MainMenuState();

        } catch (Exception e) {
            System.out.println("[!] Registration failed: " + e.getMessage());
            return new InitialState();
        }
    }
}
