package ru.yandex.practicum;

public class GuessResult {

    private final String guess;
    private final String hint;

    public GuessResult(String guess, String hint) {
        this.guess = guess;
        this.hint = hint;
    }

    public String getGuess() {
        return guess;
    }

    public String getHint() {
        return hint;
    }

}

