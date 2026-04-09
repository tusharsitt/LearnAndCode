package com.assignment.domain;

import java.util.List;

public interface GeocodingProvider {
    /**
     * Resolves a physical address to a list of geocoded locations.
     *
     * @param address the string address to geocode.
     * @return a list of locations matching the address.
     * @throws GeocodingException if the underlying service fails.
     */
    List<Location> geocode(String address) throws GeocodingException;
}
