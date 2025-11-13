package model;

// Interface for Tic Tac Toe model
public interface TTTModel {

  // Make a move at row, col
  void makeMove(int row, int col);

  // Get the player at a cell
  Player getCell(int row, int col);

  // Get who is the current player
  Player getCurrentPlayer();

  // Check if the game is over
  boolean isGameOver();

  // Get winner (null if no winner yet or draw)
  Player getWinner();

  // Reset the game to empty board
  void resetGame();
}
