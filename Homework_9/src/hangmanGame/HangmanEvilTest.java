package hangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HangmanEvilTest {

	@BeforeEach
	void setUp() throws Exception {
		Hangman hangmanEvil = new HangmanEvil(new File("words_clean.txt"));

	}
	/**
	 * Test method for
	 * {@link hangmanltjcbw.HangmanEvil#updateGuess(char, java.lang.String)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testUpdateGuess() throws FileNotFoundException {
		Hangman hangmanEvil = new HangmanEvil(new File("words_clean.txt"));

		assertTrue(hangmanEvil.guess.isEmpty());

	}
	/**
	 * Test method for {@link hangmanltjcbw.HangmanEvil#chooseStartWord()}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testChooseStartWord() throws FileNotFoundException {

		Hangman hangmanEvil = new HangmanEvil(new File("words_clean.txt"));

		assertTrue(!hangmanEvil.chooseStartWord().isEmpty());

		String regex = "([A-Z]+)";

		assertTrue(Pattern.matches(regex, hangmanEvil.chooseStartWord()));
		
		assertTrue(hangmanEvil.cleanWords.indexOf(hangmanEvil.chooseStartWord()) < hangmanEvil.cleanWords.size());
		

	}
}


