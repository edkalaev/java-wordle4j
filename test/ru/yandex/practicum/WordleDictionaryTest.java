package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WordleDictionaryTest {
    @Test
    void testAddAndSize() {
        WordleDictionary dict = new WordleDictionary();

        dict.add("test");
        dict.add("test1");

        assertEquals(2, dict.size());
    }

    @Test
    void testContains() {
        WordleDictionary dict = new WordleDictionary();

        dict.add("test");

        assertTrue(dict.contains("test"));
        assertFalse(dict.contains("test1"));
    }

    @Test
    void testGetRandomWord() {
        WordleDictionary dict = new WordleDictionary();

        dict.add("test");
        dict.add("test1");

        String word = dict.getRandomWord();

        assertNotNull(word);
        assertTrue(dict.contains(word));
    }

    @Test
    void testGetWords() {
        WordleDictionary dict = new WordleDictionary();

        dict.add("test");

        List<String> words = dict.getWords();

        assertEquals(1, words.size());
        assertEquals("test", words.getFirst());
    }

}
