package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Utility class for building and executing HTTP requests against the backend API.
 * Centralizes JSON serialization and authorization header handling.
 */
public final class ApiHelper {

    private static final String CONTENT_TYPE_JSON = "application/json";

    // Utility class — prevent instantiation
    private ApiHelper() {}

    /**
     * Sends a POST request with a JSON body and parses the response into the given type.
     *
     * @param context      the console context (holds HttpClient, ObjectMapper, token)
     * @param path         the API path relative to base URL (e.g. "/auth/login")
     * @param requestBody  the request object to serialize as JSON
     * @param responseType the TypeReference of the expected response type
     * @param <T>          the response type
     * @return the deserialized response object
     */
    public static <T> T post(ConsoleContext context,
                              String path,
                              Object requestBody,
                              TypeReference<T> responseType) throws Exception {
        final String json = context.getObjectMapper().writeValueAsString(requestBody);

        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(context.getBaseUrl() + path))
                .header("Content-Type", CONTENT_TYPE_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(json));

        attachToken(builder, context);

        final HttpResponse<String> response = context.getHttpClient()
                .send(builder.build(), HttpResponse.BodyHandlers.ofString());

        return parseResponse(context, response, responseType);
    }

    /**
     * Sends a GET request and parses the response into the given type.
     *
     * @param context      the console context
     * @param path         the API path relative to base URL
     * @param responseType the TypeReference of the expected response type
     * @param <T>          the response type
     * @return the deserialized response object
     */
    public static <T> T get(ConsoleContext context,
                             String path,
                             TypeReference<T> responseType) throws Exception {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(context.getBaseUrl() + path))
                .GET();

        attachToken(builder, context);

        final HttpResponse<String> response = context.getHttpClient()
                .send(builder.build(), HttpResponse.BodyHandlers.ofString());

        return parseResponse(context, response, responseType);
    }

    /**
     * Sends a PUT request with a JSON body and parses the response into the given type.
     *
     * @param context      the console context
     * @param path         the API path relative to base URL
     * @param requestBody  the request object to serialize as JSON
     * @param responseType the TypeReference of the expected response type
     * @param <T>          the response type
     * @return the deserialized response object
     */
    public static <T> T put(ConsoleContext context,
                             String path,
                             Object requestBody,
                             TypeReference<T> responseType) throws Exception {
        final String json = context.getObjectMapper().writeValueAsString(requestBody);

        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(context.getBaseUrl() + path))
                .header("Content-Type", CONTENT_TYPE_JSON)
                .PUT(HttpRequest.BodyPublishers.ofString(json));

        attachToken(builder, context);

        final HttpResponse<String> response = context.getHttpClient()
                .send(builder.build(), HttpResponse.BodyHandlers.ofString());

        return parseResponse(context, response, responseType);
    }

    /**
     * Sends a DELETE request.
     *
     * @param context the console context
     * @param path    the API path relative to base URL
     * @return the HTTP status code
     */
    public static int delete(ConsoleContext context, String path) throws Exception {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(context.getBaseUrl() + path))
                .DELETE();

        attachToken(builder, context);

        final HttpResponse<String> response = context.getHttpClient()
                .send(builder.build(), HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    // --- Private helpers ---

    private static void attachToken(HttpRequest.Builder builder, ConsoleContext context) {
        if (context.isAuthenticated()) {
            builder.header("Authorization", "Bearer " + context.getJwtToken());
        }
    }

    private static <T> T parseResponse(ConsoleContext context,
                                        HttpResponse<String> response,
                                        TypeReference<T> responseType) throws Exception {
        final int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            return context.getObjectMapper().readValue(response.body(), responseType);
        }
        throw new RuntimeException("API error (" + statusCode + "): " + response.body());
    }
}
