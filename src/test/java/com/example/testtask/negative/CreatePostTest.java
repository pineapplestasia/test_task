package com.example.testtask.negative;

import com.example.testtask.client.PostsClient;
import com.example.testtask.model.PostDto;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Негативные тесты на CRUD операции с постами")
@Story("Тесты на создание постов")
public class CreatePostTest {

    @Test
    @DisplayName("Создание поста с пустыми полями")
    @Description("Не должно отрабатывать из-за ошибки валидации")
    public void createPostWithoutData() {
        var postDto = PostDto.builder().build();

        PostsClient.createPost(postDto).then().log().body().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание поста с отрицательным значением id пользователя")
    @Description("Не должно отрабатывать из-за ошибки валидации")
    public void createPostWithNegativeUserId() {
        var postDto = PostDto.builder()
                .userId(-1)
                .title("some title")
                .build();

        PostsClient.createPost(postDto).then().log().body().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
