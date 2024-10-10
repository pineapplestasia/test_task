package com.example.testtask.positive;

import com.example.testtask.util.builder.CreatePostDtoBuilder;
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
@Story("Тесты на создание постов")
public class CreatePostTest {

    @Test
    @DisplayName("Создание поста")
    public void createPost() {
        int postsAmount = PostService.getPostsAmount();
        var postDto = CreatePostDtoBuilder.getCreatePostDto().build();

        PostsClient.createPost(postDto)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .body(
                        "userId", Matchers.is(postDto.getUserId()),
                        "id", Matchers.is(postsAmount + 1),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Создание поста с дальнейшим получением по id")
    @Description("После создания поста, пост должен быть доступен для получения по id")
    public void createPostWithGetById() {
        int postsAmount = PostService.getPostsAmount();
        var postDto = CreatePostDtoBuilder.getCreatePostDto().build();

        var response = PostsClient.createPost(postDto);
        response
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .body(
                        "userId", Matchers.is(postDto.getUserId()),
                        "id", Matchers.is(postsAmount + 1),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );

        var postId = response.jsonPath().getInt("id");
        PostsClient.getPostById(postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "userId", Matchers.is(postDto.getUserId()),
                        "id", Matchers.is(postsAmount + 1),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );
    }

    @Test
    @DisplayName("Создание поста с дальнейшим получением поста из списка всех постов")
    @Description("После создания поста, пост должен быть получен из списка всех постов")
    public void createPostWithGetPosts() {
        int postsAmount = PostService.getPostsAmount();
        var postDto = CreatePostDtoBuilder.getCreatePostDto().build();

        var response = PostsClient.createPost(postDto);
        response
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .body(
                        "userId", Matchers.is(postDto.getUserId()),
                        "id", Matchers.is(postsAmount + 1),
                        "title", Matchers.is(postDto.getTitle()),
                        "body", Matchers.is(postDto.getBody())
                );

        var postId = response.jsonPath().getInt("id");
        PostsClient.getPosts()
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "userId", Matchers.hasItem(postDto.getUserId()),
                        "id", Matchers.hasItem(postId),
                        "title", Matchers.hasItem(postDto.getTitle()),
                        "body", Matchers.hasItem(postDto.getBody()),
                        "size()", Matchers.is(101)
                );
    }
}
