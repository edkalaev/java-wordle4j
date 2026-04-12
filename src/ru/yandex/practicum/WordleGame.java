package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private String answer;
    private WordleDictionary dictionary;

    private final List<String> guesses = new ArrayList<>();
    private final List<String> hints = new ArrayList<>();
    private final Set<String> usedHints = new HashSet<>();

    private int attemptsUsed = 0;
    private int maxAttempts = 6;

    public WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        this.answer = dictionary.getRandomWord();
    }

    public boolean isGameEnded() {
        return attemptsUsed >= maxAttempts;
    }

    public boolean isWin(String guess) {
        return answer.equals(guess);
    }

    public GuessResult checkWord(String guess) throws WordleGameException {

        guess = WordleUtils.normalize(guess);

        if (guess.length() != answer.length()) {
            throw new WordleGameException("Слово не той длины");
        }
        if (!dictionary.contains(guess)) {
            throw new WordleGameException("Слово отсутвует в словаре");
        }
        attemptsUsed++;


        char[] hint = new char[guess.length()];


        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if(c == answer.charAt(i)) {
                hint[i] = '+';
            } else if (answer.indexOf(c) >= 0) {
                hint[i] = '^';
            } else {
                hint[i] = '-';
            }
        }

        String hintStr = new String(hint);

        guesses.add(guess);
        hints.add(hintStr);

        return new GuessResult(guess, hintStr);
    }

    public String getHintWord() {
        List<String> candidates = new ArrayList<>();

        for (String word : dictionary.getWords()) {

            if (usedHints.contains(word)) continue;

            if (isWordValid(word)) {
                candidates.add(word);
            }
        }

        if (candidates.isEmpty()) {
            for (String word : dictionary.getWords()) {
                if (!usedHints.contains(word)) {
                    usedHints.add(word);
                    return word;
                }
            }
            return dictionary.getRandomWord();
        }

        String hint = candidates.get((int) (Math.random() * candidates.size()));
        usedHints.add(hint);

        return hint;
    }

    private boolean isWordValid(String word) {

        for (int i = 0; i < guesses.size(); i++) {
            String guess = guesses.get(i);
            String hint = hints.get(i);

            for (int j = 0; j < word.length(); j++) {
                char gc = guess.charAt(j);
                char hc = hint.charAt(j);

                if (hc == '+') {
                    if (word.charAt(j) != gc) return false;
                } else if (hc == '^') {
                    if (word.indexOf(gc) == -1) return false;
                    if (word.charAt(j) == gc) return false;
                } else if (hc == '-') {
                    if (word.indexOf(gc) != -1) return false;
                }
            }
        }
        return true;
    }
}
