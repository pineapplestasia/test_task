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
@Story("Тесты на частичное обновление постов")
public class PartialUpdatePostTest {

    public static PostDto postDto;
    public static Integer postId;

    @BeforeAll
    public static void setUp() {
        postDto = PostService.getPostDto();
        postId = postDto.getId();
    }

    @Test
    @DisplayName("Обновление id пользователя")
    public void partialUpdateUserId() {
        var updatedUserId = 2;

        PostsClient.partialUpdatePost(Map.of("userId", updatedUserId), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Обновление id поста")
    public void partialUpdatePostId() {
        var updatedPostId = 2;

        PostsClient.partialUpdatePost(Map.of("id", updatedPostId), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(postDto.getUserId()),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Обновление заголовка поста")
    public void partialUpdateTitle() {
        var updatedTitle = "updated title " + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of("title", updatedTitle), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(postDto.getUserId()),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Обновление тела поста")
    public void partialUpdateBody() {
        var updatedBody = "updated body " + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of("body", updatedBody), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(postDto.getUserId()),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(updatedBody)
                );
    }

    @Test
    @DisplayName("Обновление id поста и id пользователя")
    public void partialUpdatePostIdAndUserId() {
        var updatedPostId = 2;
        var updatedUserId = 5;

        PostsClient.partialUpdatePost(Map.of("id", updatedPostId, "userId", updatedUserId), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Обновление id и заголовка поста")
    public void partialUpdatePostIdAndTitle() {
        var updatedPostId = 2;
        var updatedTitle = "updated title " + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of("id", updatedPostId, "title", updatedTitle), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(postDto.getUserId()),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Обновление id и тела поста")
    public void partialUpdatePostIdAndBody() {
        var updatedPostId = 2;
        var updatedBody = "updated body " + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of("id", updatedPostId, "body", updatedBody), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(postDto.getUserId()),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(updatedBody)
                );
    }

    @Test
    @DisplayName("Обновление id пользователя и заголовка")
    public void partialUpdateUserIdAndTitle() {
        var updatedUserId = 2;
        var updatedTitle = "updated title " + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of("userId", updatedUserId, "title", updatedTitle), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Обновление id пользователя и тела поста")
    public void partialUpdateUserIdAndBody() {
        var updatedUserId = 2;
        var updatedBody = "updated body " + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of("userId", updatedUserId, "body", updatedBody), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(updatedBody)
                );
    }

    @Test
    @DisplayName("Обновление заголовка и тела поста")
    public void partialUpdateTitleAndBody() {
        var updatedTitle = "updated title " + Instant.now().toString();
        var updatedBody = "updated body" + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of("title", updatedTitle, "body", updatedBody), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(postId),
                        "userId", Matchers.is(postDto.getUserId()),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(updatedBody)
                );
    }

    @Test
    @DisplayName("Обновление id пользователя, id и заголовка поста")
    public void partialUpdatePostIdIdAndUserIdAndTitle() {
        var updatedPostId = 2;
        var updatedUserId = 5;
        var updatedTitle = "updated title " + Instant.now().toString();

        PostsClient.partialUpdatePost(
                        Map.of("id", updatedPostId, "userId", updatedUserId, "title", updatedTitle),
                        postId
                )
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Обновление id пользователя, id и тела поста")
    public void partialUpdatePostIdIdAndUserIdAndBody() {
        var updatedPostId = 2;
        var updatedUserId = 5;
        var updatedBody = "updated body " + Instant.now().toString();

        PostsClient.partialUpdatePost(
                        Map.of("id", updatedPostId, "userId", updatedUserId, "body", updatedBody),
                        postId
                )
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(updatedBody)
                );
    }

    @Test
    @DisplayName("Обновление поста")
    @Description("""
            Без корректного получения поста по id мы не можем провалидировать работу метода частичного обновления.
            """)
    public void partialUpdatePost() {
        var updatedPostId = 2;
        var updatedUserId = 5;
        var updatedTitle = "updated title " + Instant.now().toString();
        var updatedBody = "updated body " + Instant.now().toString();

        PostsClient.partialUpdatePost(Map.of(
                        "id", updatedPostId,
                        "userId", updatedUserId,
                        "title", updatedTitle,
                        "body", updatedBody
                ), postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(updatedBody)
                );

        PostsClient.getPostById(updatedPostId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(updatedTitle),
                        "body", Matchers.is(updatedBody)
                );
    }

    @Test
    @DisplayName("Обновление созданного поста")
    @Description("Без корректного создания поста мы не можем провалидировать работу метода частичного обновления.")
    public void partialUpdateCreatedPost() {
        var createdPostId = PostService.createPostAndGetPostId(postDto);
        var updatedPostId = 2;
        var updatedUserId = 5;

        PostsClient.partialUpdatePost(Map.of("id", updatedPostId, "userId", updatedUserId), createdPostId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );

        PostsClient.getPostById(createdPostId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", Matchers.is(updatedPostId),
                        "userId", Matchers.is(updatedUserId),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );
    }
}
