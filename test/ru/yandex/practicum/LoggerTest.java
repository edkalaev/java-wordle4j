package ru.yandex.practicum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    private Path tempFile;

    @AfterEach
    void clean() throws IOException {
        if (tempFile != null && Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    void writesContentToFile() throws IOException {
        tempFile = Files.createTempFile("log", "txt.");

        Logger logger = new Logger(tempFile.toString());
        logger.log("test");

        String content = Files.readString(tempFile);

        assertTrue(content.contains("test"));
    }

    @Test
    void appendsContentToFile() throws IOException {
        tempFile = Files.createTempFile("log", "txt.");

        Logger logger = new Logger(tempFile.toString());
        logger.log("test1");
        logger.log("test2");

        String content = Files.readString(tempFile);

        assertTrue(content.indexOf("test1") < content.indexOf("test2"));

    }

}