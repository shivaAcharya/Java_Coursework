/** 
 * Class to test the ConnectFour methods. 
 * If your code does not pass these tests, it is broken.
 *
 * It is possible that your code is broken even if it passes these
 * tests, so make sure the game plays properly, too.
 */
public class ConnectFourTest {

    private static int correctTests = 0;
    private static int totalTests = 0;

    public static void testConstants() {
        // Make sure the constants in ConnectFour.java are as they
        // should be.

        // Board size should be as expected
        countTest(7 == ConnectFour.COLUMNS);
        countTest(6 == ConnectFour.ROWS);

        // Student could change player colors, I guess, but shouldn't
        // break the logic in the process.
        countTest(ConnectFour.COMPUTER != ConnectFour.HUMAN);
        countTest(ConnectFour.HUMAN != ConnectFour.NONE);
        countTest(ConnectFour.NONE != ConnectFour.COMPUTER);
    }

    /** Utility method to generate empty board. */
    public static java.awt.Color[][] emptyBoard() {
        java.awt.Color[][] result = 
            new java.awt.Color[ConnectFour.ROWS][ConnectFour.COLUMNS];
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[r].length; c++) {
                result[r][c] = ConnectFour.NONE;
            }
        }	
        return result;
    }

    public static void testOpposite() {
        countTest(ConnectFour.HUMAN == ConnectFour.opposite(ConnectFour.COMPUTER));
        countTest(ConnectFour.COMPUTER == ConnectFour.opposite(ConnectFour.HUMAN));
    }

    public static void testDrop() {
        java.awt.Color[][] board = emptyBoard();
        ConnectFour.drop(board, ConnectFour.COMPUTER, 2);
        countTest(ConnectFour.COMPUTER == board[0][2]);
        ConnectFour.drop(board, ConnectFour.HUMAN, 2);
        countTest(ConnectFour.HUMAN == board[1][2]);
        countTest(ConnectFour.COMPUTER == board[0][2]);
    }

    public static void testLegal() {
        java.awt.Color[][] board = emptyBoard();
        countTest(!ConnectFour.legal(board, -1));		
        countTest(!ConnectFour.legal(board, 7));		
        countTest(ConnectFour.legal(board, 6));		
        ConnectFour.drop(board, ConnectFour.COMPUTER, 2);
        ConnectFour.drop(board, ConnectFour.HUMAN, 2);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 2);
        ConnectFour.drop(board, ConnectFour.HUMAN, 2);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 2);
        countTest(ConnectFour.legal(board, 2));
        ConnectFour.drop(board, ConnectFour.HUMAN, 2);
        countTest(!ConnectFour.legal(board, 2));
    }

    public static void testFull() {
        java.awt.Color[][] board = emptyBoard();
        for (int r = 0; r < ConnectFour.ROWS; r++) {
            for (int c = 0; c < ConnectFour.COLUMNS; c++) {
                countTest(!ConnectFour.full(board));
                ConnectFour.drop(board, ConnectFour.COMPUTER, c);
            }
        }
        countTest(ConnectFour.full(board));
    }

    public static void testHorizontalWinner() {
        java.awt.Color[][] board = emptyBoard();
        countTest(ConnectFour.NONE == ConnectFour.winner(board));
        for (int c = 3; c < 7; c++) {
            ConnectFour.drop(board, ConnectFour.COMPUTER, c);
        }
        countTest(ConnectFour.COMPUTER == ConnectFour.winner(board));
        countTest(ConnectFour.COMPUTER == ConnectFour.winAt(board, 0, 3, 0, 1));
        countTest(ConnectFour.COMPUTER == ConnectFour.winAt(board, 0, 6, 0, -1));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 0, 2, 0, 1));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 0, 4, 0, 1));
    }
	
    public static void testVerticalWinner() {
        java.awt.Color[][] board = emptyBoard();
        for (int i = 0; i < 4; i++) {
            ConnectFour.drop(board, ConnectFour.HUMAN, 6);
        }
        countTest(ConnectFour.HUMAN == ConnectFour.winner(board));
        countTest(ConnectFour.HUMAN == ConnectFour.winAt(board, 0, 6, 1, 0));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 1, 6, 1, 0));
        countTest(ConnectFour.HUMAN == ConnectFour.winAt(board, 3, 6, -1, 0));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 4, 6, -1, 0));
    }

    public static void testVerticalWinner2() {
        java.awt.Color[][] board = emptyBoard();
        ConnectFour.drop(board, ConnectFour.COMPUTER, 3);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 3);
        for (int i = 0; i < 4; i++) {
            countTest(ConnectFour.NONE == ConnectFour.winner(board));
            ConnectFour.drop(board, ConnectFour.HUMAN, 3);
        }
        countTest(ConnectFour.HUMAN == ConnectFour.winner(board));
        countTest(ConnectFour.HUMAN == ConnectFour.winAt(board, 2, 3, 1, 0));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 1, 3, 1, 0));
        countTest(ConnectFour.HUMAN == ConnectFour.winAt(board, 5, 3, -1, 0));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 4, 3, -1, 0));
    }

	
    public static void testDiagonalWinner1() {
        java.awt.Color[][] board = emptyBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                ConnectFour.drop(board, ConnectFour.HUMAN, i);
            }
            ConnectFour.drop(board, ConnectFour.COMPUTER, i);
        }
        countTest(ConnectFour.COMPUTER == ConnectFour.winner(board));
        countTest(ConnectFour.COMPUTER == ConnectFour.winAt(board, 0, 0, 1, 1));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 1, 1, 1, 1));
        countTest(ConnectFour.COMPUTER == ConnectFour.winAt(board, 3, 3, -1, -1));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 4, 4, -1, -1));    }

    public static void testDiagonalWinner2() {
        java.awt.Color[][] board = emptyBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                ConnectFour.drop(board, ConnectFour.COMPUTER, 5 - i);
            }
            ConnectFour.drop(board, ConnectFour.HUMAN, 5 - i);
        }
        countTest(ConnectFour.HUMAN == ConnectFour.winner(board));
        countTest(ConnectFour.HUMAN == ConnectFour.winAt(board, 0, 5, 1, -1));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 1, 4, 1, -1));
        countTest(ConnectFour.HUMAN == ConnectFour.winAt(board, 3, 2, -1, 1));
        countTest(ConnectFour.NONE == ConnectFour.winAt(board, 4, 1, -1, 1));
    }
	
    public static void testUndo() {
        java.awt.Color[][] board = emptyBoard();
        ConnectFour.drop(board, ConnectFour.COMPUTER, 2);
        ConnectFour.drop(board, ConnectFour.HUMAN, 2);
        ConnectFour.undo(board, 2);
        countTest(ConnectFour.COMPUTER == board[0][2]);
        countTest(ConnectFour.NONE == board[1][2]);
    }

    public static void testMax0() {
        java.awt.Color[][] board = emptyBoard();
        for (int i = 0; i < 3; i++) {
            ConnectFour.drop(board, ConnectFour.COMPUTER, i);
            ConnectFour.drop(board, ConnectFour.HUMAN,
                             ConnectFour.COLUMNS - 1 - i);
        }
        countTest(0 == ConnectFour.max(board, 0, 0));
        ConnectFour.drop(board, ConnectFour.COMPUTER, 3);
        countTest(1 == ConnectFour.max(board, 0, 0));
        ConnectFour.undo(board, 3);
        ConnectFour.drop(board, ConnectFour.HUMAN, 3);
        countTest(-1 == ConnectFour.max(board, 0, 0));
    }
	
    public static void testMax1() {
        java.awt.Color[][] board = emptyBoard();
        // Computer cannot win in one move
        countTest(0 == ConnectFour.max(board, 1, 0));
        ConnectFour.drop(board, ConnectFour.COMPUTER, 0);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 0);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 0);
        // Now computer can win in one move
        countTest(1 == ConnectFour.max(board, 1, 0));
    }

    public static void testMax2() {
        java.awt.Color[][] board = emptyBoard();
        for (int i = 0; i < 3; i++) {
            ConnectFour.drop(board, ConnectFour.HUMAN, i + 2);
        }
        // Computer cannot prevent human from winning
        countTest(-1 == ConnectFour.max(board, 2, 0));
        ConnectFour.drop(board, ConnectFour.COMPUTER, 0);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 0);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 0);
        // Now computer can win in one move
        countTest(1 == ConnectFour.max(board, 2, 0));
    }

    public static void testMin3() {
        java.awt.Color[][] board = emptyBoard();
        for (int i = 0; i < 2; i++) {
            ConnectFour.drop(board, ConnectFour.HUMAN, i + 2);
        }
        // Human can win in three moves
        countTest(-1 == ConnectFour.min(board, 3, 0));
    }
	
    public static void testBestMoveForComputer() {
        java.awt.Color[][] board = emptyBoard();
        for (int i = 0; i < 3; i++) {
            ConnectFour.drop(board, ConnectFour.HUMAN, 5);
        }
        // Computer has to block
        countTest(5 == ConnectFour.bestMoveForComputer(board, 3));
    }
    
    public static void testDeepSearch() {
        java.awt.Color[][] board = emptyBoard();
        ConnectFour.drop(board, ConnectFour.HUMAN, 0);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 1);
        ConnectFour.drop(board, ConnectFour.HUMAN, 2);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 5);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 6);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 0);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 1);
        ConnectFour.drop(board, ConnectFour.COMPUTER, 2);
        // Computer 4 forces human to block at 3, but then computer wins at 3
        // OR Computer 3 forces human to block at 3 or 4, computer still wins
        countTest(4 == ConnectFour.bestMoveForComputer(board, 2) ||
                  3 == ConnectFour.bestMoveForComputer(board, 2));
        // This sequence can't be seen in a shallow search
        countTest(4 != ConnectFour.bestMoveForComputer(board, 1) &&
                  3 != ConnectFour.bestMoveForComputer(board, 1));		
    }

    private static void clearCounts() {
        correctTests = 0;
        totalTests = 0;
    }

    private static void countTest(boolean correct) {
        if(correct) {
            correctTests++;
        } else {
            // Uncomment next line for a trace of which test failed.
            //new Exception("Failed Test").printStackTrace();
        }
        totalTests++;
    }

    public static void main(String[] args) {
        clearCounts();
        testConstants();
        testOpposite();
        testDrop();
        testLegal();
        testFull();
        testHorizontalWinner();
        testVerticalWinner();
        testVerticalWinner2();
        testDiagonalWinner1();
        testDiagonalWinner2();
        testUndo();
        testMax0();
        testMax1();
        testMax2();
        testMin3();
        testBestMoveForComputer();
        testDeepSearch();

        System.out.println("Passed " + correctTests + " out of " + totalTests + " tests.");
    }
}