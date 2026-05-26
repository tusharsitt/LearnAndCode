package org.example.states.auth;

import org.example.context.ConsoleContext;
import org.example.states.AppState;

/**
 * The entry state of the application.
 * Presents the user with options to Login, Register, or Exit.
 */
public class InitialState implements AppState {

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n========================================");
        System.out.println("   Personal Finance Manager - Welcome!");
        System.out.println("========================================");
        System.out.println("  1. Login");
        System.out.println("  2. Register");
        System.out.println("  0. Exit");
        System.out.println("----------------------------------------");

        final int choice = context.readIntChoice("Enter choice: ");

        return switch (choice) {
            case 1 -> new LoginState();
            case 2 -> new RegisterState();
            case 0 -> null; // null signals the app loop to exit
            default -> {
                System.out.println("[!] Invalid choice. Please try again.");
                yield this; // Stay in the same state
            }
        };
    }
}
