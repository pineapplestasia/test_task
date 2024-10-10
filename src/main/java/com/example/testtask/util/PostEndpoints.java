package com.example.testtask.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostEndpoints {
    BASE_PATH("/posts");

    private final String path;
}
