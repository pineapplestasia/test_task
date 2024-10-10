package com.example.testtask.negative;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.testtask.client.PostsClient;
import com.example.testtask.service.PostService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Epic("Негативные тесты на CRUD операции с постами")
@Story("Тесты на обновление постов")
public class UpdatePostTest {

    @Test
    @DisplayName("Обновление поста с некорректным id")
    @Description("Не должно отрабатывать, т.к. поста с таким id не существует")
    public void updatePostWithInvalidId() {
        var postId = RandomUtil.getPositiveInt();

        PostsClient.updatePost(Map.of("title", "new title"), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Обновление поста с некорректным id пользователя")
    @Description("Не должно отрабатывать, т.к. пользователя с таким id не существует")
    public void updatePostWithInvalidUserId() {
        var postId = PostService.getPostDto().getId();
        var userId = RandomUtil.getPositiveInt();

        PostsClient.updatePost(Map.of("userId", userId), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
