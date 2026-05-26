package org.example.states;

import org.example.context.ConsoleContext;

/**
 * Defines the contract for each state in the console application.
 * Each state performs its logic and returns the next state to transition to.
 * Returning null signals the application to exit.
 */
public interface AppState {

    /**
     * Executes the logic for this state.
     *
     * @param context the shared console context holding token, scanner, http client etc.
     * @return the next AppState to transition to, or null to exit the application
     */
    AppState execute(ConsoleContext context);
}
