package com.example.testtask.util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestUtils {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    public static RequestSpecification getRequestSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(basePath)
                .build();
    }

    public static RequestSpecification getRequestSpecWithHeader(String basePath) {
        return getRequestSpec(basePath)
                .header("Content-Type", ContentType.JSON.toString());
    }
}
