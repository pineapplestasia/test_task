package com.example.testtask.positive;

import com.example.testtask.client.PostsClient;
import com.example.testtask.model.PostDto;
import com.example.testtask.service.PostService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

@Epic("Позитивные тесты на CRUD операции с постами")
@Story("Тесты на обновление постов")
public class UpdatePostTest {
    public static PostDto postDto;
    public static Integer postId;

    @BeforeAll
    public static void setUp() {
        postDto = PostService.getPostDto();
        postId = postDto.getId();
    }

    @Test
    @DisplayName("Обновление id пользователя")
    public void updateUserId() {
        var updatedUserId = 2;

        PostsClient.updatePost(Map.of("userId", updatedUserId), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId)
                )
                .body(
                        Matchers.not(Matchers.is(postDto.getTitle())),
                        Matchers.not(Matchers.is(postDto.getBody()))
                );
    }

    @Test
    @DisplayName("Обновление заголовка поста")
    public void updateTitle() {
        var updatedTitle = "updated title " + Instant.now().toString();

        PostsClient.updatePost(Map.of("title", updatedTitle), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "title", Matchers.is(updatedTitle)
                )
                .body(
                        Matchers.not(Matchers.is(postDto.getUserId())),
                        Matchers.not(Matchers.is(postDto.getBody()))
                );
    }

    @Test
    @DisplayName("Обновление тела поста")
    public void updateBody() {
        var updatedBody = "updated body " + Instant.now().toString();

        PostsClient.updatePost(Map.of("body", updatedBody), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "body", Matchers.is(updatedBody)
                )
                .body(
                        Matchers.not(Matchers.is(postDto.getUserId())),
                        Matchers.not(Matchers.is(postDto.getTitle()))
                );
    }

    @Test
    @DisplayName("Обновление id пользователя и заголовка")
    public void updateUserIdAndTitle() {
        var updatedUserId = 2;
        var updatedTitle = "updated title " + Instant.now().toString();

        PostsClient.updatePost(Map.of("userId", updatedUserId, "title", updatedTitle), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(updatedTitle)
                )
                .body(Matchers.not(Matchers.is(postDto.getBody())));
    }

    @Test
    @DisplayName("Обновление id пользователя и тела поста")
    public void updateUserIdAndBody() {
        var updatedUserId = 2;
        var updatedBody = "updated body " + Instant.now().toString();

        PostsClient.updatePost(Map.of("userId", updatedUserId, "body", updatedBody), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId),
                        "body", Matchers.is(updatedBody)
                )
                .body(Matchers.not(Matchers.is(postDto.getTitle())));
    }

    @Test
    @DisplayName("Обновление заголовка и тела поста")
    public void updateTitleAndBody() {
        var updatedTitle = "updated title " + Instant.now().toString();
        var updatedBody = "updated body" + Instant.now().toString();

        PostsClient.updatePost(Map.of("title", updatedTitle, "body", updatedBody), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(updatedBody)
                )
                .body(Matchers.not(Matchers.is(postDto.getUserId())));
    }

    @Test
    @DisplayName("Обновление id поста")
    @Description("id поста обновить невозможно, поэтому id никак не изменится")
    public void updatePostId() {
        var updatedPostId = 2;

        PostsClient.updatePost(Map.of("id", updatedPostId), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.is(postId));
    }

    @Test
    @DisplayName("Обновление существующего поста")
    @Description("Без корректного получения поста по id мы не можем провалидировать работу метода обновления.")
    public void updatePost() {
        var updatedUserId = 5;
        var updatedTitle = "new title " + Instant.now().toString();
        var updatedBody = "new body " + Instant.now().toString();

        PostsClient.updatePost(
                        Map.of("userId", updatedUserId, "title", updatedTitle, "body", updatedBody),
                        postId
                )
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(updatedBody)
                );

        PostsClient.getPostById(postId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(updatedBody)
                );
    }

    @Test
    @DisplayName("Обновление созданного поста")
    @Description("Без корректного создания поста мы не можем провалидировать работу метода обновления.")
    public void updateCreatedPost() {
        var createdPostId = PostService.createPostAndGetPostId(postDto);
        var updatedPostId = 2;
        var updatedUserId = 5;

        PostsClient.updatePost(Map.of("id", updatedPostId, "userId", updatedUserId), createdPostId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId)
                );

        PostsClient.getPostById(createdPostId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );
    }
}
