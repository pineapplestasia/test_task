package com.example.testtask;

import com.example.testtask.model.PostDto;
import com.example.testtask.service.PostService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task2 {
    public static void main(String[] args) {
        List<PostDto> posts = getPostDtos();
        List<String> words = splitWords(posts);
        Map<String, Integer> wordCountMap = toWordCountMap(words);

        printTopNWords(wordCountMap, 10);
    }

    private static void printTopNWords(Map<String, Integer> wordCountMap, int n) {
        List<Map.Entry<String, Integer>> sortedWords = wordCountMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .toList();

        for (int i = 0; i < Math.min(n, sortedWords.size()); i++) {
            Map.Entry<String, Integer> entry = sortedWords.get(i);
            System.out.printf("%d. %s - %d%n", i + 1, entry.getKey(), entry.getValue());
        }
    }

    private static Map<String, Integer> toWordCountMap(List<String> words) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        words.forEach(word -> wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1));
        return wordCountMap;
    }

    private static List<String> splitWords(List<PostDto> postDtos) {
        return postDtos.stream()
                .map(PostDto::getBody)
                .map(body -> body.replace("\n", " "))
                .flatMap(body -> Arrays.stream(body.split(" ")))
                .toList();
    }

    private static List<PostDto> getPostDtos() {
        return Arrays.stream(PostService.getPosts().then().extract().as(PostDto[].class)).toList();
    }
}
