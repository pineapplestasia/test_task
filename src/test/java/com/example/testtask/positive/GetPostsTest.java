package com.example.testtask.positive;

import com.example.testtask.client.PostsClient;
import com.example.testtask.service.PostService;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Позитивные тесты на CRUD операции с постами")
@Story("Тесты на получение постов")
public class GetPostsTest {

    @Test
    @DisplayName("Получение всех постов")
    public void getPosts() {
        PostsClient.getPosts()
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", Matchers.is(100));
    }

    @Test
    @DisplayName("Получение поста по id")
    public void getPostById() {
        var postId = PostService.getPostDto().getId();

        PostsClient.getPostById(postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.is(postId),
                        "userId", Matchers.is(1),
                        "title", Matchers.is(
                                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
                        ),
                        "body", Matchers.is("""
                            quia et suscipit
                            suscipit recusandae consequuntur expedita et cum
                            reprehenderit molestiae ut ut quas totam
                            nostrum rerum est autem sunt rem eveniet architecto
                        """)
                );
    }
}
