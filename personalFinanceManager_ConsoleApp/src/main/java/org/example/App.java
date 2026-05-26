package org.example;

import org.example.context.ConsoleContext;
import org.example.states.AppState;
import org.example.states.auth.InitialState;

/**
 * Entry point for the Personal Finance Manager Console Application.
 *
 * <p>Uses the State Pattern to manage application flow — each state encapsulates
 * one screen of the console and returns the next state to transition to.
 * Returning null from a state signals application exit.</p>
 */
public class App {

    public static void main(String[] args) {
        final ConsoleContext context = new ConsoleContext();
        AppState currentState = new InitialState();

        while (currentState != null) {
            currentState = currentState.execute(context);
        }

        System.out.println("\nGoodbye! Thank you for using Personal Finance Manager.");
    }
}
