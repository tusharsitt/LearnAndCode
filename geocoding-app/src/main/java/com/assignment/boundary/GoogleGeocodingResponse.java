package com.assignment.boundary;

import java.util.List;


public record GoogleGeocodingResponse(List<Result> results, String status, String error_message) {

    public record Result(String formatted_address, Geometry geometry) {}

    public record Geometry(LocationNode location) {}

    public record LocationNode(double lat, double lng) {}
}
