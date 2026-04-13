package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordleUtilsTest {

    @Test
    public void testTrim() {
        String result = WordleUtils.normalize("  слон  ");
        assertEquals("слон", result);
    }

    @Test
    public void testToLowerCase() {
        String result = WordleUtils.normalize("ПрИвЕт");
        assertEquals("привет", result);
    }

    @Test
    public void testReplace() {
        String result = WordleUtils.normalize("ёлка");
        assertEquals("елка", result);
    }

    @Test
    public void testNormalizeAllMethods() {
        String result = WordleUtils.normalize("  ЁЛкА  ");
        assertEquals("елка", result);
    }

}
