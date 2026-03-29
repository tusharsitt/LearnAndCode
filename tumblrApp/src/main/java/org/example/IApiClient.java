package org.example;

public interface IApiClient {
    String fetchData(String blogName, int start, int num) throws Exception;
}
