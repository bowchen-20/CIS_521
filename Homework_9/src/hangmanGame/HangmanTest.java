/**
 * 
 */
package hangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author bowenchen&tangjili
 *
 */
class HangmanTest {

	/**
	 * void
	 * 
	 * @throws java.lang.Exception TODO
	 */
	@BeforeEach
	void setUp() throws Exception {
		Hangman hangman = new Hangman(new File("words_clean.txt"));

	}

	/**
	 * Test method for {@link hangmanltjcbw.Hangman#Hangman(java.io.File)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testHangman() throws FileNotFoundException {
		Hangman hangman = new Hangman(new File("words_clean.txt"));
		assertEquals(0, hangman.guess.size());

		assertEquals(0, hangman.mistakes.size());
		String regex = "([A-Z]+)";

		for (String word : hangman.cleanWords) {
			assertTrue(Pattern.matches(regex, word));

		}
	}

	/**
	 * Test method for {@link hangmanltjcbw.Hangman#isGameOver(java.lang.String)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsGameOver() throws FileNotFoundException {
		Hangman hangman = new Hangman(new File("words_clean.txt"));

		assertFalse(hangman.isGameOver("aa"));

		assertTrue(hangman.isGameOver(""));

		hangman.guess.add('a');

		assertTrue(hangman.isGameOver("aaa"));

	}

	/**
	 * Test method for
	 * {@link hangmanltjcbw.Hangman#updateMistakes(char, java.lang.String)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testUpdateMistakes() throws FileNotFoundException {

		Hangman hangman = new Hangman(new File("words_clean.txt"));

		assertFalse(hangman.mistakes.contains('a'));
		;

		hangman.updateMistakes('a', "csde");
		assertTrue(hangman.mistakes.contains('a'));

		hangman.updateMistakes('c', "csde");
		assertFalse(hangman.mistakes.contains('c'));

		hangman.updateMistakes('s', "cde");

		assertTrue(hangman.mistakes.contains('s'));

	}	

	/**
	 * Test method for
	 * {@link hangmanltjcbw.Hangman#updateGuess(char, java.lang.String)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testUpdateGuess() throws FileNotFoundException {
		Hangman hangman = new Hangman(new File("words_clean.txt"));

		assertTrue(hangman.guess.isEmpty());

		hangman.updateGuess('a', "");
		assertTrue(hangman.guess.contains('a'));

		hangman.updateGuess('@', "");
		assertTrue(hangman.guess.contains('@'));

		hangman.updateGuess('b', "");
		assertTrue(hangman.guess.contains('b'));

	}

	/**
	 * Test method for {@link hangmanltjcbw.Hangman#chooseStartWord()}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testChooseStartWord() throws FileNotFoundException {

		Hangman hangman = new Hangman(new File("words_clean.txt"));

		assertTrue(!hangman.chooseStartWord().isEmpty());

		String regex = "([A-Z]+)";

		assertTrue(Pattern.matches(regex, hangman.chooseStartWord()));
		
		assertTrue(hangman.cleanWords.indexOf(hangman.chooseStartWord()) < hangman.cleanWords.size());
		

	}

}
