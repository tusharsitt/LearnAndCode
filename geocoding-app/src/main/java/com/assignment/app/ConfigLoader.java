package com.assignment.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility to load configuration properties from a file.
 */
public class ConfigLoader {
    private static final String DEFAULT_CONFIG_FILE = "config.properties";

    private final Properties properties;

    public ConfigLoader() {
        this(DEFAULT_CONFIG_FILE);
    }

    public ConfigLoader(String fileName) {
        properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Warning: Unable to find " + fileName + ". Please ensure it exists.");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
