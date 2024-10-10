package com.example.testtask.util.builder;

import com.example.testtask.model.PostDto;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(staticName = "getCreatePostDto")
public class CreatePostDtoBuilder implements TestBuilder<PostDto> {
    @Override
    public PostDto build() {
        return PostDto.builder()
                .userId(1)
                .title("title " + Instant.now().toString())
                .body("body " + Instant.now().toString())
                .build();
    }
}
