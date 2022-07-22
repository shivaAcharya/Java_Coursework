import java.util.Arrays;

public class HangmanTester {

    private static int correctTests = 0;
    private static int totalTests = 0;

    private static void clearCounts() {
        correctTests = 0;
        totalTests = 0;
    }

    private static void countTest(boolean correct) {
        if(correct) {
            correctTests++;
        }
        totalTests++;
    }

    private static void testIsComplete() {
        // isComplete() should only return true if the char[] passed to it
        // contains no underscores

        String[] incompleteStrs = { "_oobar", "foo_ar", "fooba_",};
        for(String str : incompleteStrs) {
            char[] arr = str.toCharArray();
            countTest(!Hangman.isComplete(arr));
            countTest(Arrays.equals(arr, str.toCharArray()));
        }
        
        String str = "foobar";
        char[] arr = str.toCharArray();
        countTest(Hangman.isComplete(arr));
        countTest(Arrays.equals(arr, str.toCharArray()));
    }

    private static void testFoundGuessedLetter() {
        char[] known = "______".toCharArray();
        countTest(!Hangman.foundGuessedLetter('e', "foobar", known));
        countTest(Arrays.equals("______".toCharArray(), known));
        // When the letter is present, foundGuessedLetter() should return true
        countTest(Hangman.foundGuessedLetter('o', "foobar", known));
        // foundGuessedLetter() should also modify the char[] that was passed in
        countTest(Arrays.equals("_oo___".toCharArray(), known));
        countTest(Hangman.foundGuessedLetter('f', "foobar", known));
        countTest(Arrays.equals("foo___".toCharArray(), known));
        countTest(!Hangman.foundGuessedLetter('z', "foobar", known));
        countTest(Arrays.equals("foo___".toCharArray(), known));
    }

    private static void testPickRandomWord() {
        // Make a tiny dictionary
        String[] dictionary = { "foo", "bar", "baz", "quux" };
        int[] counts = new int[dictionary.length];
        // Many times, choose a random word from this dictionary
        for (int i = 0; i < 5000; i++) {
            String word = Hangman.pickRandomWord(dictionary);
            for (int j = 0; j < dictionary.length; j++) {
                // Update a counter for each word when it appears
                if (dictionary[j].equals(word)) {
                    counts[j]++;
                }
            }
        }
        // All words in the dictionary should appear with similar frequency
        for (int i = 0; i < counts.length; i++) {
            countTest(counts[i] > (5000.0 / dictionary.length) * 0.5);
            countTest(counts[i] < (5000.0 / dictionary.length) * 1.5);
        }
    }

    public static void main(String[] args) {
        clearCounts();
        testIsComplete();
        System.out.println("testing isComplete: " +
                           correctTests + "/" + totalTests);

        clearCounts();
        testFoundGuessedLetter();
        System.out.println("testing foundGuessedLetter: " +
                           correctTests + "/" + totalTests);

        clearCounts();
        testPickRandomWord();
        System.out.println("testing pickRandomWord: " +
                           correctTests + "/" + totalTests);

    }
}