package com.example.testtask.client;

import com.example.testtask.model.PostDto;
import com.example.testtask.util.PostEndpoints;
import com.example.testtask.util.RestUtils;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostsClient {
    private static final String BASE_PATH = PostEndpoints.BASE_PATH.getPath();

    public static Response createPost(PostDto postDto) {
        return given()
                .spec(RestUtils.getRequestSpecWithHeader(BASE_PATH))
                .log().uri()
                .body(postDto)
                .log().body()
                .post();
    }

    public static Response getPostById(Integer id) {
        return given()
                .spec(RestUtils.getRequestSpec(BASE_PATH))
                .log().uri()
                .get(id.toString());
    }

    public static Response getPosts() {
        return given()
                .spec(RestUtils.getRequestSpec(BASE_PATH))
                .log().uri()
                .get();
    }

    public static Response updatePost(Map<String, Object> body, Integer id) {
        return given()
                .spec(RestUtils.getRequestSpecWithHeader(BASE_PATH))
                .log().uri()
                .body(body)
                .log().body()
                .put(id.toString());
    }

    public static Response partialUpdatePost(Map<String, Object> body, Integer id) {
        return given()
                .spec(RestUtils.getRequestSpecWithHeader(BASE_PATH))
                .log().uri()
                .body(body)
                .log().body()
                .patch(id.toString());
    }

    public static Response deletePost(Integer id) {
        return given()
                .spec(RestUtils.getRequestSpec(BASE_PATH))
                .log().uri()
                .delete(id.toString());
    }
}
