package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    private final Logger logger;

    public WordleDictionaryLoader(Logger logger) {
        this.logger = logger;
    }

    public WordleDictionary loadDictionary(String filename) {

        WordleDictionary dictionary = new WordleDictionary();

        logger.log("Чтение словаря из файла: " + filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {
                line = WordleUtils.normalize(line);
                if (line.length() == 5) {
                    dictionary.add(line);
                }
            }

        } catch (IOException e) {
            logger.log("Ошибка чтения словаря: " + e.getMessage());
        }

        logger.log("Подходящих слов найдено: " + dictionary.size());

        return dictionary;

    }
}
