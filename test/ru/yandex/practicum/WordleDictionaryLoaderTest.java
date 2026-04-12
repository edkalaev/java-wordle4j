package ru.yandex.practicum;
import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class WordleDictionaryLoaderTest {

    @Test
    public void loadFromFile() throws Exception {
        Path path = Path.of("words_ru.txt");

        Logger logger = new Logger("ignored", new PrintWriter(System.out));
        WordleDictionaryLoader loader = new WordleDictionaryLoader(logger);

        WordleDictionary dict = loader.loadDictionary(path.toString());

        assertTrue(dict.size() > 0);
        assertTrue(dict.contains("авеню"));
        assertFalse(dict.contains("тусовка"));
        assertTrue(dict.contains("тюбик"));

    }

}
