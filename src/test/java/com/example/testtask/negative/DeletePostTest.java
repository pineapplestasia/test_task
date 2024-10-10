package com.example.testtask.negative;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.testtask.client.PostsClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Негативные тесты на CRUD операции с постами")
@Story("Тесты на удаление постов")
public class DeletePostTest {

    @Test
    @DisplayName("Удаление поста c некорректным id поста")
    @Description("Не должно отрабатывать из-за ошибки валидации")
    public void deletePostWithInvalidId() {
        var postId = RandomUtil.getPositiveInt();

        PostsClient.deletePost(postId)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.not(Matchers.is(postId)));

        PostsClient.getPostById(postId).then().log().body().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
