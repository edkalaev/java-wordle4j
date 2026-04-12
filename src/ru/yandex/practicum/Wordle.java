package ru.yandex.practicum;

import java.nio.file.Path;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) {
        Logger logger = null;

            try {
                logger = new Logger("log.txt");
                logger.log("программа запущена");

                Path path = Path.of("words_ru.txt");

                WordleDictionaryLoader loader = new WordleDictionaryLoader(logger);
                WordleDictionary dictionary = loader.loadDictionary(path.toString());

                WordleGame game = new WordleGame(dictionary);
                Scanner scanner = new Scanner(System.in);

                while (!game.isGameEnded()) {
                    System.out.println("Введите слово или Enter для подсказки");
                    String input = scanner.nextLine();
                    input = WordleUtils.normalize(input);


                    try {
                        if (input.isEmpty()) {
                            String hint = game.getHintWord();
                            System.out.println("Подсказка: " + hint);
                            logger.log("Выдана подсказка: " + hint);
                            continue;
                        }

                        GuessResult result = game.checkWord(input);
                        System.out.println("Результат: " + result.getHint());
                        logger.log("Ход: " + input + " | " + result.getHint());

                        if (game.isWin(input)) {
                            System.out.println("Вы победили");
                            logger.log("Игрок победил");
                            break;
                        }
                    } catch (WordleGameException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                        logger.log("Игровая ошибка: " + e.getMessage());
                    }
                }

                logger.log("Игра завершена");

            } catch (Exception e) {
                System.out.println("Критическая ошибка: " + e.getMessage());

                if (logger != null) {
                    logger.log("Системная ошибка: " + e.getMessage());
                }
            }

    }












}
