package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordleGameTest {
    private WordleDictionary createDictionary() {
        WordleDictionary dict = new WordleDictionary();
        dict.add("слон");
        return dict;
    }

    @Test
    void gameNotEndedInitially() {
        WordleGame game = new WordleGame(createDictionary());

        assertFalse(game.isGameEnded());
    }

    @Test
    void winCondition() {
        WordleDictionary dict = createDictionary();
        WordleGame game = new WordleGame(dict);

        String answer = dict.getWords().getFirst();
        boolean win = game.isWin(answer);

        assertTrue(win);
    }

    @Test
    void wrongLength() {
        WordleGame game = new WordleGame(createDictionary());

        try {
            game.checkWord("кот");
        } catch (WordleGameException e) {
            assertEquals("Слово не той длины", e.getMessage());
            return;
        }

        assertTrue(false);
    }

    @Test
    void wordNotInDictionary() {
        WordleGame game = new WordleGame(createDictionary());

        try {
            game.checkWord("паро");
        } catch (WordleGameException e) {
            assertEquals("Слово отсутвует в словаре", e.getMessage());
            return;
        }

        assertTrue(false);
    }

    @Test
    void checkWordSuccess() throws Exception {
        WordleDictionary dict = createDictionary();
        WordleGame game = new WordleGame(dict);

        String guess = dict.getWords().getFirst();
        GuessResult result = game.checkWord(guess);

        assertNotNull(result);
        assertEquals(guess, result.getGuess());
        assertEquals(4, result.getHint().length());
    }

    @Test
    void testGetHintWord() {
        WordleGame game = new WordleGame(createDictionary());
        String hint = game.getHintWord();

        assertNotNull(hint);
    }

}
