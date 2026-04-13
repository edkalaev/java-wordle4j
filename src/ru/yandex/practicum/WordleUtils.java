package ru.yandex.practicum;

public class WordleUtils {
    public static String normalize(String word) {
        return word.trim().toLowerCase().replace('ё', 'е');
    }
}
