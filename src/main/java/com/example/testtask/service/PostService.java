package com.example.testtask.service;

import com.example.testtask.client.PostsClient;
import com.example.testtask.model.PostDto;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.Arrays;


public class PostService {
    public static Response getPosts() {
        var response = PostsClient.getPosts();
        response.then().statusCode(HttpStatus.SC_OK);

        return response;
    }

    public static Integer getPostsAmount() {
        return getPosts().then().extract().jsonPath().getList("$").size();
    }

    public static PostDto getPostDto() {
        return Arrays.stream(getPosts().then().extract().as(PostDto[].class))
                .findFirst()
                .orElse(null);
    }

    public static Integer createPostAndGetPostId(PostDto postDto) {
        var response = PostsClient.createPost(postDto);
        response.then().statusCode(HttpStatus.SC_CREATED);

        return response.jsonPath().getInt("id");
    }
}
