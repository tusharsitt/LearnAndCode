package org.example;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TumblrApiClient implements IApiClient {
    private static final String API_TEMPLATE = "https://%s.tumblr.com/api/read/json?type=photo&num=%d&start=%d";

    @Override
    public String fetchData(String blogName, int start, int num) throws Exception {

        String urlString = String.format(API_TEMPLATE, blogName, num, start);
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("Failed to connect: HTTP code " + status);
        }

        // Read Response
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = buffer.readLine()) != null) {
            content.append(inputLine);
        }

        buffer.close();
        connection.disconnect();

        // We need to clean it to get pure JSON.
        String response = content.toString();
        return cleanJson(response);
    }

    private String cleanJson(String response) {
        if (response.startsWith("var tumblr_api_read =")) {
            response = response.replace("var tumblr_api_read =", "");
        }
        if (response.endsWith(";")) {
            response = response.substring(0, response.length() - 1);
        }
        return response.trim();
    }
}
