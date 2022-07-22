
/**
 * CS 152 Lab 5 - Hangman
 *
 * Implement the methods needed to play a game of hangman.
 *
 * Student name: Shi
 */
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	/** We'll use underscores for unknown letters. */
	public static final char BLANK = '_';

	/**
	 * Have all the letters in the guess been filled in?
	 * 
	 * @param knownLetters
	 *            Array of letters that player knows.
	 * @return True if knownLetters has no blanks (that is, no underscores)
	 */
	public static boolean isComplete(char[] knownLetters) {
		// REPLACE THE METHOD BODY
		/*
		 * Scanner sc = new Scanner(System.in); char letter =
		 * sc.next().trim().charAt(0); char knownLetters1 = letter; string
		 * knownletters1 =
		 */
		for (int i = 0; i < knownLetters.length; i++) {
			if (knownLetters[i] == '_')
				return false;
		}

		return true;
	}

	/**
	 * Checks to see if letter occurs at least once in word. If so, sets the
	 * corresponding elements of knownLetters to letter and returns true. If
	 * not, returns false.
	 * 
	 * @param letter
	 *            Letter that player has guessed.
	 * @param word
	 *            The word we are checking.
	 * @param knownLetters
	 *            Array of letters that player knows.
	 * @return True if letter was found in word.
	 */
	public static boolean foundGuessedLetter(char letter, String word, char[] knownLetters) {
		boolean flag = false;
		for (int i = 0; i < knownLetters.length; i++) {
			if (word.charAt(i) == letter) {
				knownLetters[i] = letter;
				flag = true;
			}
		}
		// REPLACE THE METHOD BODY
		return flag;
	}

	/**
	 * Selects a random word from the dictionary.
	 * 
	 * @param dictionary
	 *            An array of words.
	 * @return Randomly chosen word from dictionary.
	 */
	public static String pickRandomWord(String[] dictionary) {
		// REPLACE THE METHOD BODY
		// I suggest you implement this method first, since
		// the program will crash if it returns null.
		String[] words = { "aardvark", "alligator", "alpaca", "anteater", "antelope", "ape", "armadillo", "baboon",
				"badger", "bat", "bear", "beaver", "bison", "boar", "buffalo", "bull", "bunny", "burro", "camel",
				"canary", "capybara", "cat", "chameleon", "cheetah", "chimpanzee", "chinchilla", "chipmunk", "colt",
				"cougar", "cow", "coyote", "crocodile", "crow", "deer", "dingo", "doe", "dog", "donkey", "dormouse",
				"elephant", "elk", "ewe", "fawn", "ferret", "finch", "fish", "fox", "frog", "gazelle", "giraffe", "gnu",
				"goat", "gopher", "gorilla", "hamster", "hare", "hedgehog", "hippopotamus", "hog", "horse", "hyena",
				"ibex", "iguana", "impala", "jackal", "jaguar", "kangaroo", "kid", "kitten", "koala", "lamb", "lemur",
				"leopard", "lion", "lizard", "llama", "lynx", "mare", "marmoset", "marten", "mink", "mole", "mongoose",
				"monkey", "moose", "mouse", "mule", "muskrat", "mustang", "newt", "ocelot", "opossum", "orangutan",
				"oryx", "otter", "ox", "panda", "panther", "parakeet", "parrot", "pig", "platypus", "pony", "porcupine",
				"porpoise", "puma", "puppy", "rabbit", "raccoon", "ram", "rat", "reindeer", "reptile", "rhinoceros",
				"salamander", "seal", "sheep", "shrew", "skunk", "sloth", "snake", "squirrel", "stallion", "steer",
				"tapir", "tiger", "toad", "turtle", "vicuna", "walrus", "warthog", "weasel", "whale", "wildcat", "wolf",
				"wolverine", "wombat", "woodchuck", "yak", "zebra" };
		int index = new Random().nextInt(dictionary.length);
		String pickRandomWord = dictionary[index];

		return pickRandomWord;
	}

	/**
	 * This method prints ASCII art of the hanged man. You don't need to change
	 * this method.
	 * 
	 * @param guesses
	 *            The number of guesses remaining.
	 */
	public static void drawAsciiMan(int guesses) {

		// top of gallows
		System.out.println(" _______");

		// head (or not)
		if (guesses < 6) {
			System.out.println(" |     O");
		} else {
			System.out.println(" |");
		}

		// Body and arms
		switch (guesses) {

		case 6:
		case 5:
			// no body
			System.out.println(" |");
			break;

		case 4:
			// body, no arms
			System.out.println(" |     |");
			break;

		case 3:
			// body and left arm
			System.out.println(" |    /|");
			break;

		default:
			// body and both arms
			System.out.println(" |    /|\\");
			break;

		}

		// Legs
		switch (guesses) {

		case 1:
			// left leg
			System.out.println(" |    /");
			break;

		case 0:
			// both legs
			System.out.println(" |    / \\");
			break;

		default:
			// no legs
			System.out.println(" |");
			break;

		}

		// bottom of gallows
		System.out.println("_|_");
	}

	/**
	 * Plays a text-based game of hangman. You don't need to change this method.
	 */
	public static void main(String[] args) {
		// Let's use some animals for our words in this game.
		String[] words = { "aardvark", "alligator", "alpaca", "anteater", "antelope", "ape", "armadillo", "baboon",
				"badger", "bat", "bear", "beaver", "bison", "boar", "buffalo", "bull", "bunny", "burro", "camel",
				"canary", "capybara", "cat", "chameleon", "cheetah", "chimpanzee", "chinchilla", "chipmunk", "colt",
				"cougar", "cow", "coyote", "crocodile", "crow", "deer", "dingo", "doe", "dog", "donkey", "dormouse",
				"elephant", "elk", "ewe", "fawn", "ferret", "finch", "fish", "fox", "frog", "gazelle", "giraffe", "gnu",
				"goat", "gopher", "gorilla", "hamster", "hare", "hedgehog", "hippopotamus", "hog", "horse", "hyena",
				"ibex", "iguana", "impala", "jackal", "jaguar", "kangaroo", "kid", "kitten", "koala", "lamb", "lemur",
				"leopard", "lion", "lizard", "llama", "lynx", "mare", "marmoset", "marten", "mink", "mole", "mongoose",
				"monkey", "moose", "mouse", "mule", "muskrat", "mustang", "newt", "ocelot", "opossum", "orangutan",
				"oryx", "otter", "ox", "panda", "panther", "parakeet", "parrot", "pig", "platypus", "pony", "porcupine",
				"porpoise", "puma", "puppy", "rabbit", "raccoon", "ram", "rat", "reindeer", "reptile", "rhinoceros",
				"salamander", "seal", "sheep", "shrew", "skunk", "sloth", "snake", "squirrel", "stallion", "steer",
				"tapir", "tiger", "toad", "turtle", "vicuna", "walrus", "warthog", "weasel", "whale", "wildcat", "wolf",
				"wolverine", "wombat", "woodchuck", "yak", "zebra" };

		System.out.println("Welcome to Hangman! Try to guess my word.");

		String word = pickRandomWord(words);

		char[] known = new char[word.length()];
		for (int i = 0; i < known.length; i++) {
			known[i] = BLANK;
		}

		Scanner sc = new Scanner(System.in);
		int guesses = 6;
		while (guesses > 0 && !isComplete(known)) {
			System.out.println(); // blank line between guesses
			System.out.println("Guesses remaining: " + guesses);
			drawAsciiMan(guesses);
			System.out.println("Word: " + new String(known));

			System.out.print("Enter your guess: ");
			// grabbing the entire next token and then taking only 1st char
			char letter = sc.next().trim().charAt(0);

			if (!foundGuessedLetter(letter, word, known)) {
				System.out.println("Sorry, there is no " + letter);
				guesses--;
			}
		}

		drawAsciiMan(guesses);
		System.out.println("Word: " + new String(known));
		if (isComplete(known)) {
			System.out.println("Hooray! You win!");
		} else {
			System.out.println("You lose. The word was: " + word);
		}
	}

}