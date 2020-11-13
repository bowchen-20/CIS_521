package hangmanGame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Evil subclass class
 * @Bowen Chen & Tangji Li
 *
 */
public class HangmanEvil extends Hangman {

    private ArrayList<String> pool;

    public HangmanEvil(File file) throws FileNotFoundException {
        super(file);
        pool = new ArrayList<String>();
    }

    private String setPool(char c) {

        //use a hashmap to store potential pools against the BASE
        HashMap<String, ArrayList<String>> possible_pools = new HashMap<String, ArrayList<String>>();
        
        //get the word length from the first item in pool
        int word_length = pool.get(0).length();

        //go through all the words in the pool
        for(int i = 0; i<pool.size(); i++){
            String word = pool.get(i);

            //build up the base string containing _ and the char passed in
            String base = "";

            //go through each char in word
            for(int chr = 0; chr<word_length; chr++){
                if(word.charAt(chr) == c){
                    base += c;
                }else{
                    base += '_';
                }
            }

            //if the base does not exist in possible pools add it with empty arraylist
            if(!possible_pools.containsKey(base)){
                possible_pools.put(base, new ArrayList<String>());
            }

            //add the word to the pool w/ base
            possible_pools.get(base).add(word);

        }

        //get the biggest base
        int max = 0;
        String max_key = null;
        for(String key: possible_pools.keySet()){

            //get the size for this key's possible pool
            int size = possible_pools.get(key).size();

            //update max and key if this one is bigger
            if(size > max){
                max = size;
                max_key = key;
            }

        }

        //replace the pool
        pool = possible_pools.get(max_key);

        //randomly select a word from the pool
        Random r = new Random();
        int index = r.nextInt(pool.size());
        String word = pool.get(index);
        return word;
    }

    @Override
    public String chooseStartWord() {
        //empty the pool
        pool = new ArrayList<String>();

        //new random selector
        Random r = new Random();

        //select a random word from the cleanWords
        int index = r.nextInt(cleanWords.size());

        //select our word to play the game with
        String game_word = cleanWords.get(index);

        int wordLength = game_word.length();


        //go through clean words and add to the pool words with the same length
        for(int i = 0; i<cleanWords.size();i++){
            String word = cleanWords.get(i);

            //if word length matches, add it to pool
            if(word.length() == wordLength){
                pool.add(word);
            }
        }

        //return our game word
        return game_word;
    }

    @Override
    public String updateGuess(char c, String word){
        super.updateGuess(c, word);

        //call setPool to get a new random word
        return setPool(c);

    }

}
