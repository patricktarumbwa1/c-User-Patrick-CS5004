import model.*;

/**
 * Simple test file for TTTModelImpl.
 */
public class TTTTest {

  public static void main(String[] args) {

    TTTModel game = new TTTModelImpl();

    System.out.println("=== Test 1: First Move ===");
    game.makeMove(0, 0);
    System.out.println("Cell (0,0): " + game.getCell(0, 0)); // expect X
    System.out.println("Current player: " + game.getCurrentPlayer()); // expect O

    System.out.println("\n=== Test 2: Can't Play Same Cell ===");
    try {
      game.makeMove(0, 0);
      System.out.println("FAILED: should not allow same cell");
    } catch (Exception e) {
      System.out.println("Passed: " + e.getMessage());
    }

    System.out.println("\n=== Test 3: Winning Row ===");
    game.resetGame();
    game.makeMove(0,0); // X
    game.makeMove(1,0); // O
    game.makeMove(0,1); // X
    game.makeMove(1,1); // O
    game.makeMove(0,2); // X wins
    System.out.println("Game over? " + game.isGameOver()); // true
    System.out.println("Winner: " + game.getWinner());    // X

    System.out.println("\n=== Test 4: Draw ===");
    game.resetGame();
    game.makeMove(0,0); // X
    game.makeMove(0,1); // O
    game.makeMove(0,2); // X
    game.makeMove(1,1); // O
    game.makeMove(1,0); // X
    game.makeMove(1,2); // O
    game.makeMove(2,1); // X
    game.makeMove(2,0); // O
    game.makeMove(2,2); // X
    System.out.println("Game over? " + game.isGameOver()); // true
    System.out.println("Winner: " + game.getWinner());    // null (draw)

    System.out.println("\n=== Test 5: Reset ===");
    game.resetGame();
    System.out.println("Cell (0,0) after reset: " + game.getCell(0,0)); // null
    System.out.println("Current player: " + game.getCurrentPlayer());    // X

    System.out.println("\nAll tests done!");
  }
}
