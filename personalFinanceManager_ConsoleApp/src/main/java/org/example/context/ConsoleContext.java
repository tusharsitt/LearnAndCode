package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.http.HttpClient;
import java.util.Scanner;

/**
 * Holds shared application state passed between console states.
 * Acts as the central context in the State Pattern — stores the HttpClient,
 * Scanner, ObjectMapper, and the authenticated JWT token.
 */
public class ConsoleContext {

    private static final String BASE_URL = "http://localhost:8080/api/v1";

    private final HttpClient httpClient;
    private final Scanner scanner;
    private final ObjectMapper objectMapper;

    private String jwtToken;

    public ConsoleContext() {
        this.httpClient = HttpClient.newHttpClient();
        this.scanner = new Scanner(System.in);
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    // --- Getters ---

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    // --- Token management ---

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public boolean isAuthenticated() {
        return jwtToken != null && !jwtToken.isBlank();
    }

    public void clearToken() {
        this.jwtToken = null;
    }

    /**
     * Convenience method to read an integer choice safely from the console.
     *
     * @param prompt the prompt to display to the user
     * @return the integer entered, or -1 if input was invalid
     */
    public int readIntChoice(String prompt) {
        System.out.print(prompt);
        final String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Convenience method to read a non-blank string from the console.
     *
     * @param prompt the prompt to display to the user
     * @return the trimmed string entered
     */
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
