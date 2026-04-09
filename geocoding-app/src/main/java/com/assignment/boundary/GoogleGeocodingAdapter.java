package com.assignment.boundary;

import com.assignment.domain.Coordinate;
import com.assignment.domain.GeocodingException;
import com.assignment.domain.GeocodingProvider;
import com.assignment.domain.Location;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;


public class GoogleGeocodingAdapter implements GeocodingProvider {
    private static final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";
    
    private final String apiKey;
    private final HttpClient httpClient;
    private final Gson gson;

    public GoogleGeocodingAdapter(String apiKey) {
        if (apiKey == null || apiKey.trim().isEmpty() || apiKey.equals("YOUR_API_KEY_HERE")) {
            throw new IllegalArgumentException("Invalid Google maps API key provided.");
        }
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @Override
    public List<Location> geocode(String address) throws GeocodingException {
        if (address == null || address.trim().isEmpty()) {
            return Collections.emptyList();
        }

        try {
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
            String url = String.format(API_URL, encodedAddress, this.apiKey);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new GeocodingException("Failed to fetch geocoding data. HTTP Status: " + response.statusCode());
            }

            GoogleGeocodingResponse apiResponse = gson.fromJson(response.body(), GoogleGeocodingResponse.class);

            if (!"OK".equals(apiResponse.status()) && !"ZERO_RESULTS".equals(apiResponse.status())) {
                String errorMsg = apiResponse.error_message() != null ? apiResponse.error_message() : apiResponse.status();
                throw new GeocodingException("Google API returned error: " + errorMsg);
            }

            if (apiResponse.results() == null || apiResponse.results().isEmpty()) {
                return Collections.emptyList();
            }

            return apiResponse.results().stream()
                    .map(this::mapToDomain)
                    .toList();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GeocodingException("Geocoding request was interrupted.", e);
        } catch (Exception e) {
            throw new GeocodingException("An error occurred while calling the geocoding service.", e);
        }
    }

    private Location mapToDomain(GoogleGeocodingResponse.Result result) {
        Coordinate coordinate = new Coordinate(
                result.geometry().location().lat(),
                result.geometry().location().lng()
        );
        return new Location(result.formatted_address(), coordinate);
    }
}
