package com.assignment;

import com.assignment.app.ConfigLoader;
import com.assignment.app.GeocodingConsoleApp;
import com.assignment.boundary.GoogleGeocodingAdapter;
import com.assignment.domain.GeocodingProvider;


public class Main {
    public static void main(String[] args) {
     
        ConfigLoader configLoader = new ConfigLoader();
        String apiKey = configLoader.getProperty("google.api.key");

        GeocodingProvider geocodingProvider;
        try {
            geocodingProvider = new GoogleGeocodingAdapter(apiKey);
        } catch (IllegalArgumentException e) {
            System.err.println("Configuration Error: " + e.getMessage());
            System.err.println("Please configure your API key in config.properties.");
            return;
        }

        GeocodingConsoleApp app = new GeocodingConsoleApp(geocodingProvider);
        app.start();
    }
}
