package com.example.testtask.negative;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.testtask.client.PostsClient;
import com.example.testtask.model.PostDto;
import com.example.testtask.service.PostService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Epic("Негативные тесты на CRUD операции с постами")
@Story("Тесты на частичное обновление постов")
public class PartialUpdateTest {

    @Test
    @DisplayName("Обновление двух постов одинаковыми данными")
    @Description("""
        Обновление на уже существующий элемент не должно быть возможным. Появляются два поста с одинаковыми id.
    """)
    public void partialUpdateTwoPostsWithSameData() {
        var postId = PostService.getPostDto().getId();
        var secondPostId =
                Arrays.stream(PostService.getPosts().then().extract().as(PostDto[].class))
                        .filter(postDto -> !Objects.equals(postDto.getId(), postId))
                        .findFirst()
                        .map(PostDto::getId)
                        .orElse(null);

        PostsClient.partialUpdatePost(Map.of("id", postId), secondPostId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Обновление поста с некорректным id")
    @Description("Тест должен упасть, т.к. поста с таким id не существует")
    public void partialUpdatePostWithInvalidId() {
        var postId = RandomUtil.getPositiveInt();

        PostsClient.partialUpdatePost(Map.of("title", "new title"), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Обновление поста некорректными данными")
    @Description("Тест должен упасть, т.к. пользователя с таким id не существует")
    public void partialUpdatePostWithInvalidUserId() {
        var postId = PostService.getPostDto().getId();
        var userId = RandomUtil.getPositiveInt();

        PostsClient.partialUpdatePost(Map.of("userId", userId), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
