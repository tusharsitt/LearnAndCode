package com.assignment.app;

import com.assignment.domain.GeocodingException;
import com.assignment.domain.GeocodingProvider;
import com.assignment.domain.Location;

import java.util.List;
import java.util.Scanner;


public class GeocodingConsoleApp {
    private final GeocodingProvider geocodingProvider;

    public GeocodingConsoleApp(GeocodingProvider geocodingProvider) {
        this.geocodingProvider = geocodingProvider;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.print("\nEnter a location to geocode (or 'quit' to exit): ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                }

                if (input.isEmpty()) {
                    System.out.println("Location cannot be empty. Please try again.");
                    continue;
                }

                processLocationRequest(input);
            }
        }
    }

    private void processLocationRequest(String locationName) {
        System.out.println("Retrieving coordinates for '" + locationName + "'...");
        try {
            List<Location> results = geocodingProvider.geocode(locationName);

            if (results.isEmpty()) {
                System.out.println("No results found for the given location.");
                return;
            }

            System.out.println("\n--- Results ---");
            for (int i = 0; i < results.size(); i++) {
                System.out.printf("Result #%d:\n%s\n", (i + 1), results.get(i).toString());
            }
        } catch (GeocodingException e) {
            System.err.println("Error occurred during geocoding: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Root cause: " + e.getCause().getMessage());
            }
        }
    }
}
