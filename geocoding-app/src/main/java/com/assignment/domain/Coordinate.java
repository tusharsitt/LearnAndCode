package com.assignment.domain;


public record Coordinate(double latitude, double longitude) {
    @Override
    public String toString() {
        return String.format("%.6f, %.6f", latitude, longitude);
    }
}
