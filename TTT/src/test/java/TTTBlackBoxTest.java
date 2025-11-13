import model.*;

public class TTTBlackBoxTest {

  public static void main(String[] args) {
    TTTModel game = new TTTModelImpl(); // assume TTTModelImpl exists

    // --- makeMove tests ---
    game.resetGame();
    game.makeMove(0,0); // X
    assert game.getCell(0,0) == Player.X; // 1st assertion
    boolean error = false;
    try { game.makeMove(0,0); } catch (Exception e) { error = true; }
    assert error; // 2nd assertion

    // --- getCell tests ---
    assert game.getCell(0,0) == Player.X; // 1st
    assert game.getCell(1,1) == null;     // 2nd

    // --- getCurrentPlayer tests ---
    assert game.getCurrentPlayer() == Player.O; // after first move
    game.makeMove(1,1); // O moves
    assert game.getCurrentPlayer() == Player.X; // after O move

    // --- isGameOver tests ---
    assert !game.isGameOver(); // not over yet
    game.makeMove(0,1); // X
    game.makeMove(1,0); // O
    game.makeMove(0,2); // X wins
    assert game.isGameOver(); // game should be over
    assert game.getWinner() == Player.X; // winner is X

    // --- resetGame tests ---
    game.resetGame();
    assert game.getCell(0,0) == null;  // board reset
    assert game.getCurrentPlayer() == Player.X; // player reset

    System.out.println("All simple black box tests done!");
  }
}
