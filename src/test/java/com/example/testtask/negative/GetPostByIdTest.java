package com.example.testtask.negative;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.testtask.client.PostsClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Негативные тесты на CRUD операции с постами")
@Story("Тесты на получение постов по id")
public class GetPostByIdTest {

    @Test
    @DisplayName("Получение поста по некорректному id")
    @Description("Не должно отрабатывать из-за ошибки валидации")
    public void getPostByInvalidId() {
        var postId = RandomUtil.getPositiveInt();

        PostsClient.getPostById(postId).then().log().body().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
