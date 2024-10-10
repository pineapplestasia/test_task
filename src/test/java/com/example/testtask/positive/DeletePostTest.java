package com.example.testtask.positive;

import com.example.testtask.client.PostsClient;
import com.example.testtask.service.PostService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Позитивные тесты на CRUD операции с постами")
@Story("Тесты на удаление постов")
public class DeletePostTest {

    @Test
    @DisplayName("Удаление созданного поста и получение его по id")
    @Description("Без корректного создания поста мы не можем провалидировать работу DELETE метода.")
    public void deleteCreatedPost() {
        var postDto = PostService.getPostDto();
        var postId = PostService.createPostAndGetPostId(postDto);

        PostsClient.deletePost(postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.not(Matchers.is(postId)));

        PostsClient.getPostById(postId).then().log().body().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Удаление существующего поста и получение его по id")
    @Description("Отрабатывает некорректно, т.к. не происходит реального удаления")
    public void deletePost() {
        var postId = PostService.getPostDto().getId();

        PostsClient.deletePost(postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.not(Matchers.is(postId)));

        PostsClient.getPostById(postId).then().log().body().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Удаление созданного поста и получение его из списка всех постов")
    @Description("Без корректного создания поста мы не можем провалидировать работу DELETE метода.")
    public void deleteCreatedPostAndGetPostByGetPosts() {
        var postDto = PostService.getPostDto();
        var postId = PostService.createPostAndGetPostId(postDto);

        PostsClient.deletePost(postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.not(Matchers.is(postId)));

        PostsClient.getPosts()
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.not(Matchers.hasItem(postId)));
    }

    @Test
    @DisplayName("Удаление существующего поста и получение его из списка всех постов")
    @Description("Отрабатывает некорректно, т.к. не происходит реального удаления")
    public void deletePostAndGetPostByGetPosts() {
        var postId = PostService.getPostDto().getId();

        PostsClient.deletePost(postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.not(Matchers.is(postId)));

        PostsClient.getPosts()
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.not(Matchers.hasItem(postId)));
    }
}
