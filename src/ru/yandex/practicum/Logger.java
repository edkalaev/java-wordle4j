package ru.yandex.practicum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private final String logFileName;
    private PrintWriter writer;

    public Logger(String logFileName) {
        this.logFileName = logFileName;
    }

    public Logger(String logFileName, PrintWriter writer) {
        this.logFileName = logFileName;
        this.writer = writer;
    }

    public void log(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
            return;
        }
        try (FileWriter f = new FileWriter(logFileName, true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b)) {
            writer.println(message);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи лога", e);
        }
    }
}
