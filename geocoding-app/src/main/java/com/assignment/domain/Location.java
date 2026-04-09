package com.assignment.domain;


public record Location(String formattedAddress, Coordinate coordinate) {
    @Override
    public String toString() {
        return "Address: " + formattedAddress + "\nCoordinates: " + coordinate;
    }
}
